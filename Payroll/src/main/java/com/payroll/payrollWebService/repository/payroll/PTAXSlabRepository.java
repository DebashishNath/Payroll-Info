package com.payroll.payrollWebService.repository.payroll;

import com.payroll.payrollWebService.models.payroll.trn_ptax_slab;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PTAXSlabRepository extends CrudRepository<trn_ptax_slab, Long> {
}