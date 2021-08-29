package com.payroll.payrollWebService.service.Menu;

import com.payroll.payrollWebService.models.payroll.mst_menu;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import java.util.List;
import java.util.Optional;

public interface MenuService {
    mst_menu save(mst_menu menu);
    List<mst_menu> findAll();
    Optional<mst_menu> findById(Long id);
    MessageResponse removeOne(Long id);
}
