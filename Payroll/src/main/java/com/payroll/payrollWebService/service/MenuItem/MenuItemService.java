package com.payroll.payrollWebService.service.MenuItem;

import com.payroll.payrollWebService.models.payroll.mst_menu_item;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import java.util.List;
import java.util.Optional;

public interface MenuItemService {
    mst_menu_item save(mst_menu_item menuItem);
    List<mst_menu_item> findAll();
    Optional<mst_menu_item> findById(Long id);
    MessageResponse removeOne(Long id);
}