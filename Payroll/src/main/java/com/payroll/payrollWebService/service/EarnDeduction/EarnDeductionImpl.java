package com.payroll.payrollWebService.service.EarnDeduction;

import com.payroll.payrollWebService.models.payroll.mst_earn_ded_components;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import com.payroll.payrollWebService.service.EarnDeduction.EarnDeductionService;

import java.util.List;
import java.util.Optional;

abstract class EarnDeductionImpl implements EarnDeductionService{
    @Override
    public mst_earn_ded_components save(mst_earn_ded_components earnDedComponents) {
        return new EarnDeductionDAL().save(earnDedComponents);
    }

    @Override
    public mst_earn_ded_components modify(mst_earn_ded_components earnDedComponents) {
        return new EarnDeductionDAL().modify(earnDedComponents);
    }

    @Override
    public List<mst_earn_ded_components> findAll(){
        return new EarnDeductionDAL().findAll();
    }

    @Override
    public Optional<mst_earn_ded_components> findById(Long id){
        return new EarnDeductionDAL().findById(id);
    }

    @Override
    public MessageResponse removeOne(Long id){
        return new EarnDeductionDAL().removeOne(id);
    }
}
