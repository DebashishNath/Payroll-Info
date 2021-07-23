package com.payroll.payrollWebService.service.PTAXMonthly;

import com.payroll.payrollWebService.models.payroll.trn_ptax_monthly;
import com.payroll.payrollWebService.models.payroll.PTaxMonthlyIdentity;
import com.payroll.payrollWebService.payload.response.MessageResponse;

import java.util.List;
import java.util.Optional;

public interface PTAXMonthlyService
{
    trn_ptax_monthly save(trn_ptax_monthly ptaxMonthly);
    List<trn_ptax_monthly> findAll(Long month, Long year);
    Optional<trn_ptax_monthly> findById(PTaxMonthlyIdentity pTaxMonthlyIdentity);
    MessageResponse removeOne(PTaxMonthlyIdentity pTaxMonthlyIdentity);
}

