package com.payroll.payrollWebService.service.Company;

import com.payroll.payrollWebService.models.payroll.mst_company;
import com.payroll.payrollWebService.payload.response.MessageResponse;

import java.util.List;
import java.util.Optional;

abstract class CompanyServiceImpl implements CompanyService {

    @Override
    public mst_company save(mst_company company) {
        return new CompanyServiceDAL().save(company);
    }

    @Override
    public List<mst_company> findAll(){
        return new CompanyServiceDAL().findAll();
    }

    @Override
    public Optional<mst_company> findById(Long id){
        return new CompanyServiceDAL().findById(id);
    }

    @Override
    public MessageResponse removeOne(Long id){
        return new CompanyServiceDAL().removeOne(id);
    }
}
