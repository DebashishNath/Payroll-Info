package com.payroll.payrollWebService.service.Location;

import com.payroll.payrollWebService.models.payroll.mst_location;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import java.util.List;
import java.util.Optional;

public interface LocationService {
    mst_location save(mst_location location);
    mst_location modify(mst_location location);
    List<mst_location> findAll();
    Optional<mst_location> findById(Long id);
    MessageResponse removeOne(Long id);
}
