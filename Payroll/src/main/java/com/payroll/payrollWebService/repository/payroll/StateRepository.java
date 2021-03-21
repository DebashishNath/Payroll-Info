package com.payroll.payrollWebService.repository.payroll;

import com.payroll.payrollWebService.models.payroll.mst_state;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends CrudRepository <mst_state, Long> {}
