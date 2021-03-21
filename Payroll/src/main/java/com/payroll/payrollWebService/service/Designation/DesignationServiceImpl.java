package com.payroll.payrollWebService.service.Designation;

import com.payroll.payrollWebService.models.payroll.mst_designation;
import com.payroll.payrollWebService.payload.response.MessageResponse;

import java.util.List;
import java.util.Optional;

abstract class DesignationServiceImpl implements DesignationService{

    @Override
    public mst_designation save(mst_designation designation) {
        return new DesignationServiceDAL().save(designation);
    }

    @Override
    public mst_designation modify(mst_designation designation) {
        return new DesignationServiceDAL().modify(designation);
    }

    @Override
    public List<mst_designation> findAll(){
        return new DesignationServiceDAL().findAll();
    }

    @Override
    public Optional<mst_designation> findById(Long id){
        return new DesignationServiceDAL().findById(id);
    }

    @Override
    public MessageResponse removeOne(Long id){
        return new DesignationServiceDAL().removeOne(id);
    }
}
