package com.payroll.payrollWebService.service.Menu;

import com.payroll.payrollWebService.models.payroll.mst_menu;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import java.util.List;
import java.util.Optional;

abstract class MenuServiceImpl implements MenuService {

    @Override
    public mst_menu save(mst_menu menu) {
        return new MenuServiceDAL().save(menu);
    }

    @Override
    public List<mst_menu> findAll(){
        return new MenuServiceDAL().findAll();
    }

    @Override
    public Optional<mst_menu> findById(Long id){
        return new MenuServiceDAL().findById(id);
    }

    @Override
    public MessageResponse removeOne(Long id){
        return new MenuServiceDAL().removeOne(id);
    }
}
