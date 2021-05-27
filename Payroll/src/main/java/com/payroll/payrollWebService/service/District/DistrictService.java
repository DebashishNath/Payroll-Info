package com.payroll.payrollWebService.service.District;

import com.payroll.payrollWebService.models.payroll.mst_district;
import com.payroll.payrollWebService.payload.response.MessageResponse;

import java.util.List;
import java.util.Optional;

public interface DistrictService {
    mst_district save(mst_district district);

    mst_district modify(mst_district district);

    List<mst_district> findAll(Long stateid);

    Optional<mst_district> findById(Long id);

    MessageResponse removeOne(Long id);

}