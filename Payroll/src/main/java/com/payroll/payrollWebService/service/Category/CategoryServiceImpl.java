package com.payroll.payrollWebService.service.Category;

import com.payroll.payrollWebService.models.payroll.mst_category;

abstract class CategoryServiceImpl implements CategoryService{

    @Override
    public mst_category save(mst_category category) {
        return new CategoryServiceDAL().save(category);
    }
}
