package com.payroll.payrollWebService.models.auth;

import com.payroll.payrollWebService.models.payroll.mst_employee;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "mst_users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 20)
	@Column(updatable = false)
	private String username;

	@NotBlank
	@Size(max = 120)
	private String password;

	@NotBlank
	@Size(max = 120)
	private String email;

	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "emp_id", referencedColumnName = "emp_id")
	private mst_employee employee;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "trn_user_roles",
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	@Transient
	private String accessToken;

	public User() {}

	public User(String username, String password,String email,mst_employee employee) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.employee=employee;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public mst_employee getEmployee() {
		return employee;
	}

	public void setEmployee(mst_employee employee) {
		this.employee = employee;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
}
