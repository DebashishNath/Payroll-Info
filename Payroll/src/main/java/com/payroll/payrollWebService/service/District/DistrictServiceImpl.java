package com.payroll.payrollWebService.service.District;

import com.payroll.payrollWebService.models.payroll.mst_district;
import com.payroll.payrollWebService.payload.response.MessageResponse;

import java.util.List;
import java.util.Optional;

abstract class DistrictServiceImpl implements DistrictService {

    @Override
    public mst_district save(mst_district district) {
        return new DistrictServiceDAL().save(district);
    }

    @Override
    public mst_district modify(mst_district district) {
        return new DistrictServiceDAL().modify(district);
    }

    @Override
    public List<mst_district> findAll(Long stateid){
        return new DistrictServiceDAL().findAll(stateid);
    }

    @Override
    public Optional<mst_district> findById(Long id){
        return new DistrictServiceDAL().findById(id);
    }

    @Override
    public MessageResponse removeOne(Long id){
        return new DistrictServiceDAL().removeOne(id);
    }
}
