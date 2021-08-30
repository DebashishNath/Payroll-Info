package com.payroll.payrollWebService.service.MenuItem;

import com.payroll.payrollWebService.models.payroll.mst_menu_item;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import java.util.List;
import java.util.Optional;

abstract class MenuItemServiceImpl implements MenuItemService {

    @Override
    public mst_menu_item save(mst_menu_item menuItem) {
        return new MenuItemServiceDAL().save(menuItem);
    }

    @Override
    public List<mst_menu_item> findAll(){
        return new MenuItemServiceDAL().findAll();
    }

    @Override
    public Optional<mst_menu_item> findById(Long id){
        return new MenuItemServiceDAL().findById(id);
    }

    @Override
    public MessageResponse removeOne(Long id){
        return new MenuItemServiceDAL().removeOne(id);
    }
}

