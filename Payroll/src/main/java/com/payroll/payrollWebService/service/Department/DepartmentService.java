package com.payroll.payrollWebService.service.Department;

import com.payroll.payrollWebService.models.payroll.mst_department;
import com.payroll.payrollWebService.payload.response.MessageResponse;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    mst_department save(mst_department department);
    mst_department modify(mst_department department);
    List<mst_department> findAll();
    Optional<mst_department> findById(Long id);
    MessageResponse removeOne(Long id);
}
