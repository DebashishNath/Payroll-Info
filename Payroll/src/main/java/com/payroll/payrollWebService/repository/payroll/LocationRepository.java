package com.payroll.payrollWebService.repository.payroll;

import com.payroll.payrollWebService.models.payroll.mst_location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends CrudRepository<mst_location, Long> {
}