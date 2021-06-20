package com.payroll.payrollWebService.service.Employee;

import com.payroll.payrollWebService.models.payroll.mst_employee;
import com.payroll.payrollWebService.payload.response.MessageResponse;

import java.util.List;
import java.util.Optional;

abstract class EmployeeServiceImpl implements EmployeeService {

    @Override
    public mst_employee save(mst_employee employee) {
        return new EmployeeServiceDAL().save(employee);
    }

    @Override
    public mst_employee modify(mst_employee employee) {
        return new EmployeeServiceDAL().modify(employee);
    }

    @Override
    public mst_employee modifyEmployeeOfficial(mst_employee employee){ return new EmployeeServiceDAL().modifyEmployeeOfficial(employee);}

    @Override
    public List<mst_employee> findAll(){
        return new EmployeeServiceDAL().findAll();
    }

    @Override
    public Optional<mst_employee> findById(Long id){
        return new EmployeeServiceDAL().findById(id);
    }

    @Override
    public MessageResponse removeOne(Long id){
        return new EmployeeServiceDAL().removeOne(id);
    }
}
