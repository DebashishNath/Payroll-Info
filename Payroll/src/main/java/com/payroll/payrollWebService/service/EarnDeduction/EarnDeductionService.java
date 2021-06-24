package com.payroll.payrollWebService.service.EarnDeduction;

import com.payroll.payrollWebService.models.payroll.mst_earn_ded_components;
import com.payroll.payrollWebService.payload.response.MessageResponse;

import java.util.List;
import java.util.Optional;

public interface EarnDeductionService {
    mst_earn_ded_components update(mst_earn_ded_components earnDedComponents);

    mst_earn_ded_components modify(mst_earn_ded_components earnDedComponents);

    List<mst_earn_ded_components> findAll();

    Optional<mst_earn_ded_components> findById(Long id);

    MessageResponse removeOne(Long id);
}
