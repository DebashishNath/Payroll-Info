package com.payroll.payrollWebService.repository.auth;

import java.util.Optional;

import com.payroll.payrollWebService.models.auth.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.payroll.payrollWebService.models.auth.ERole;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
