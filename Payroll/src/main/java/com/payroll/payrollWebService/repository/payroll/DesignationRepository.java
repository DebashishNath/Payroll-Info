package com.payroll.payrollWebService.repository.payroll;

import com.payroll.payrollWebService.models.payroll.mst_designation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignationRepository extends CrudRepository <mst_designation, Long> {
}

