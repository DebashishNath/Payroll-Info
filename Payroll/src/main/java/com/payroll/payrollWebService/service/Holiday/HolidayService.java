package com.payroll.payrollWebService.service.Holiday;

import com.payroll.payrollWebService.models.payroll.mst_holiday;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import java.util.List;
import java.util.Optional;

public interface HolidayService {
    mst_holiday save(mst_holiday holiday);
    List<mst_holiday> findAll(Integer month,Integer year);
    Optional<mst_holiday> findById(Long id);
    MessageResponse removeOne(Long id);
}
