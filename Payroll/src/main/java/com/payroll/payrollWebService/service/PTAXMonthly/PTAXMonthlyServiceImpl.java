package com.payroll.payrollWebService.service.PTAXMonthly;

import com.payroll.payrollWebService.models.payroll.PTaxMonthlyIdentity;
import com.payroll.payrollWebService.models.payroll.trn_ptax_monthly;
import com.payroll.payrollWebService.payload.response.MessageResponse;

import java.util.List;
import java.util.Optional;

abstract class PTAXMonthlyServiceImpl implements PTAXMonthlyService {
    @Override
    public trn_ptax_monthly save(trn_ptax_monthly ptaxMonthly) {
        return new PTAXMonthlyServiceDAL().save(ptaxMonthly);
    }

    @Override
    public List<trn_ptax_monthly> findAll(Long month,Long year){
        return new PTAXMonthlyServiceDAL().findAll(month,year);
    }

    @Override
    public Optional<trn_ptax_monthly> findById(PTaxMonthlyIdentity ptaxMonthlyIdentity){
        return new PTAXMonthlyServiceDAL().findById(ptaxMonthlyIdentity);
    }

    @Override
    public MessageResponse removeOne(PTaxMonthlyIdentity ptaxMonthlyIdentity){
        return new PTAXMonthlyServiceDAL().removeOne(ptaxMonthlyIdentity);
    }
}
