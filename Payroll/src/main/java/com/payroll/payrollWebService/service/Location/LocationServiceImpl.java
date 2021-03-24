package com.payroll.payrollWebService.service.Location;

import com.payroll.payrollWebService.models.payroll.mst_location;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import java.util.List;
import java.util.Optional;

abstract class LocationServiceImpl implements LocationService {

    @Override
    public mst_location save(mst_location location) {
        return new LocationServiceDAL().save(location);
    }

    @Override
    public mst_location modify(mst_location location) {
        return new LocationServiceDAL().modify(location);
    }

    @Override
    public List<mst_location> findAll(){
        return new LocationServiceDAL().findAll();
    }

    @Override
    public Optional<mst_location> findById(Long id){
        return new LocationServiceDAL().findById(id);
    }

    @Override
    public MessageResponse removeOne(Long id){
        return new LocationServiceDAL().removeOne(id);
    }
}
