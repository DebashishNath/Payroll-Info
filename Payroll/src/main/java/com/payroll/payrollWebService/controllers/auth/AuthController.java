package com.payroll.payrollWebService.controllers.auth;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;

import com.payroll.payrollWebService.models.auth.ERole;
import com.payroll.payrollWebService.models.auth.Role;
import com.payroll.payrollWebService.models.auth.User;
import com.payroll.payrollWebService.models.common.CodeConstants;
import com.payroll.payrollWebService.payload.request.LoginRequest;
import com.payroll.payrollWebService.payload.response.JwtResponse;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import com.payroll.payrollWebService.repository.auth.RoleRepository;
import com.payroll.payrollWebService.repository.auth.UserRepository;
import com.payroll.payrollWebService.security.jwt.JwtUtils;
import com.payroll.payrollWebService.security.authservices.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
    UserRepository userRepository;

	@Autowired
    RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
    JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest)
			throws InvalidKeySpecException, NoSuchAlgorithmException {
		try {
			System.out.println("Inside Sign In");
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
							loginRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);

			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

			List<String> roles = userDetails.getAuthorities().stream()
					.map(item -> item.getAuthority())
					.collect(Collectors.toList());
			Set<Role> userRoles = new HashSet<>();
			if (roles != null && roles.size() > 0) {
				for (int i = 0; i < roles.size(); i++) {
					Role role = new Role();
					if (roles.get(i) == "ROLE_ADMIN") {
						role.setId(1);
						role.setName(ERole.ROLE_ADMIN);
					}
					if (roles.get(i) == "ROLE_USER") {
						role.setId(2);
						role.setName(ERole.ROLE_USER);
					}
					userRoles.add(role);
				}
			}
			String jwt = jwtUtils.generateJwtToken(authentication);
			return ResponseEntity.ok(new JwtResponse(jwt,
					userDetails.getId(),
					userDetails.getUsername(),
					userDetails.getEmail(),
					userRoles, new MessageResponse(CodeConstants.SUCCESS.getID(),
					"Successful Sign in.")));
		}catch(Exception ex){
			return ResponseEntity.ok(new JwtResponse("",new Long(0),
					"","",null,
					new MessageResponse(CodeConstants.UNAUTHORIZED.getID(),
					"Invalid login/Password")));
		}
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody User signUpRequest)
	{
		try {
			System.out.println("Inside registerUser() " + signUpRequest.getUsername());
			if (userRepository.existsByUsername(signUpRequest.getUsername())) {
				return ResponseEntity
						.badRequest()
						.body(new MessageResponse(CodeConstants.DUPLICATE.getID(),
								"Error: Username is already taken!"));
			}

			User user = new User(signUpRequest.getUsername(),
					encoder.encode(signUpRequest.getPassword()),
					signUpRequest.getEmail());

			if (signUpRequest.getRoles() != null) {
				System.out.println("Inside getRoles() values");
				Set<Role> roles = signUpRequest.getRoles();
				user.setRoles(roles);
			}

			userRepository.save(user);
			return ResponseEntity.ok(new MessageResponse(CodeConstants.SUCCESS.getID(),
							"User registered successfully!"));
		}catch(Exception ex){
			System.out.println("The exception is: " + ex.getMessage());
			return ResponseEntity.ok(new MessageResponse(CodeConstants.FAILURE.getID(),ex.getMessage()));
		}
	}
}
