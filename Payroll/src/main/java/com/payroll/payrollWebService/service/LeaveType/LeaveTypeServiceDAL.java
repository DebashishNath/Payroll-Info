package com.payroll.payrollWebService.service.LeaveType;

import com.payroll.payrollWebService.models.payroll.mst_leave_type;
import com.payroll.payrollWebService.repository.payroll.LeaveTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class LeaveTypeServiceDAL extends LeaveTypeServiceImpl
{
    @Autowired
    private LeaveTypeRepository leaveTypeRep;

    public LeaveTypeServiceDAL() {}

    @Override
     public List<mst_leave_type> findAll(){
        return (List<mst_leave_type>)leaveTypeRep.findAll();
    }

}
