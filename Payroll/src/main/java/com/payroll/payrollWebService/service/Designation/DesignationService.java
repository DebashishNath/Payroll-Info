package com.payroll.payrollWebService.service.Designation;

import com.payroll.payrollWebService.models.payroll.mst_designation;
import com.payroll.payrollWebService.payload.response.MessageResponse;

import java.util.List;
import java.util.Optional;

public interface DesignationService {
    mst_designation save(mst_designation designation);

    mst_designation modify(mst_designation designation);

    List<mst_designation> findAll();

    Optional<mst_designation> findById(Long id);

    MessageResponse removeOne(Long id);

}
