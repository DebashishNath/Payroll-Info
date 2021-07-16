package com.payroll.payrollWebService.repository.payroll;

import com.payroll.payrollWebService.models.payroll.mst_earn_ded_components;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EarnDeductionRepository extends CrudRepository
        <mst_earn_ded_components,Long> {
        List<mst_earn_ded_components> findAll(Sort sort);
}