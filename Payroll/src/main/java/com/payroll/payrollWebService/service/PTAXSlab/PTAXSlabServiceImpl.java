package com.payroll.payrollWebService.service.PTAXSlab;

import com.payroll.payrollWebService.models.payroll.trn_ptax_slab;
import com.payroll.payrollWebService.payload.response.MessageResponse;

import java.util.List;
import java.util.Optional;

abstract class PTAXSlabServiceImpl implements PTAXSlabService {

    @Override
    public trn_ptax_slab save(trn_ptax_slab ptaxSlab) {
        return new PTAXSlabServiceDAL().save(ptaxSlab);
    }

    @Override
    public List<trn_ptax_slab> findAll(Long finYearId){
        return new PTAXSlabServiceDAL().findAll(finYearId);
    }

    @Override
    public Optional<trn_ptax_slab> findById(Long id){
        return new PTAXSlabServiceDAL().findById(id);
    }

    @Override
    public MessageResponse removeOne(Long id){
        return new PTAXSlabServiceDAL().removeOne(id);
    }
}

