package com.payroll.payrollWebService.repository.payroll;

import com.payroll.payrollWebService.models.payroll.trn_emp_attendance;
import com.payroll.payrollWebService.models.payroll.AttendanceIdentity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpAttendanceRepository extends CrudRepository<trn_emp_attendance, AttendanceIdentity>
{}