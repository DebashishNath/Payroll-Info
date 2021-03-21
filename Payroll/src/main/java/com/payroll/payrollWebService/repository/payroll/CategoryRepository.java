package com.payroll.payrollWebService.repository.payroll;

import com.payroll.payrollWebService.models.payroll.mst_category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<mst_category, Long> {
}