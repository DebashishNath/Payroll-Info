package com.payroll.payrollWebService.service.Category;

import com.payroll.payrollWebService.models.payroll.mst_category;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import com.payroll.payrollWebService.service.Category.CategoryService;

import java.util.List;
import java.util.Optional;

abstract class CategoryServiceImpl implements CategoryService{

    @Override
    public mst_category save(mst_category category) {
        return new CategoryServiceDAL().save(category);
    }

    @Override
    public mst_category modify(mst_category category) {
        return new CategoryServiceDAL().modify(category);
    }

    @Override
    public List<mst_category> findAll(){
        return new CategoryServiceDAL().findAll();
    }

    @Override
    public Optional<mst_category> findById(Long id){
        return new CategoryServiceDAL().findById(id);
    }

    @Override
    public MessageResponse removeOne(Long id){
        return new CategoryServiceDAL().removeOne(id);
    }
}
