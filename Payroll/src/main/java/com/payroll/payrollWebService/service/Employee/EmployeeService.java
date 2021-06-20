package com.payroll.payrollWebService.service.Employee;

import com.payroll.payrollWebService.models.payroll.mst_employee;
import com.payroll.payrollWebService.payload.response.MessageResponse;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    mst_employee save(mst_employee employee);

    mst_employee modify(mst_employee employee);

    mst_employee modifyEmployeeOfficial(mst_employee employee);

    List<mst_employee> findAll();

    Optional<mst_employee> findById(Long id);

    MessageResponse removeOne(Long id);
}

