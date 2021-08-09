package com.payroll.payrollWebService.service.Company;

import com.payroll.payrollWebService.models.payroll.mst_company;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import java.util.List;
import java.util.Optional;

public interface CompanyService {
    mst_company save(mst_company company);
    List<mst_company> findAll();
    Optional<mst_company> findById(Long id);
    MessageResponse removeOne(Long id);
}
