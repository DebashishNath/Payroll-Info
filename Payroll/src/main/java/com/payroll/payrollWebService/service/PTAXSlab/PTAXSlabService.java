package com.payroll.payrollWebService.service.PTAXSlab;

import com.payroll.payrollWebService.models.payroll.trn_ptax_slab;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import java.util.List;
import java.util.Optional;

public interface PTAXSlabService {
    trn_ptax_slab save(trn_ptax_slab ptaxSlab);
    List<trn_ptax_slab> findAll(Long finYearId);
    Optional<trn_ptax_slab> findById(Long id);
    MessageResponse removeOne(Long id);
}

