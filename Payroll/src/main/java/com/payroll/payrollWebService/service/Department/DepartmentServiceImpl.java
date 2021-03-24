package com.payroll.payrollWebService.service.Department;

import com.payroll.payrollWebService.models.payroll.mst_department;
import com.payroll.payrollWebService.payload.response.MessageResponse;

import java.util.List;
import java.util.Optional;

abstract class DepartmentServiceImpl implements DepartmentService {

    @Override
    public mst_department save(mst_department department) {
        return new DepartmentServiceDAL().save(department);
    }

    @Override
    public mst_department modify(mst_department department) {
        return new DepartmentServiceDAL().modify(department);
    }

    @Override
    public List<mst_department> findAll(){
        return new DepartmentServiceDAL().findAll();
    }

    @Override
    public Optional<mst_department> findById(Long id){
        return new DepartmentServiceDAL().findById(id);
    }

    @Override
    public MessageResponse removeOne(Long id){
        return new DepartmentServiceDAL().removeOne(id);
    }
}
