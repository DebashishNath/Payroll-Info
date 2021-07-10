package com.payroll.payrollWebService.service.Holiday;

import com.payroll.payrollWebService.models.payroll.mst_holiday;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import java.util.List;
import java.util.Optional;

abstract class HolidayServiceImpl implements HolidayService {

    @Override
    public mst_holiday save(mst_holiday holiday) {
        return new HolidayServiceDAL().save(holiday);
    }

    @Override
    public List<mst_holiday> findAll(Integer month,Integer year){
        return new HolidayServiceDAL().findAll(month,year);
    }

    @Override
    public Optional<mst_holiday> findById(Long id){
        return new HolidayServiceDAL().findById(id);
    }

    @Override
    public MessageResponse removeOne(Long id){
        return new HolidayServiceDAL().removeOne(id);
    }
}

