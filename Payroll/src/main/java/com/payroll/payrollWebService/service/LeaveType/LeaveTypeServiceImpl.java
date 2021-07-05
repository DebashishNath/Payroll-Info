package com.payroll.payrollWebService.service.LeaveType;

import com.payroll.payrollWebService.models.payroll.mst_leave_type;
import java.util.List;

abstract class LeaveTypeServiceImpl implements LeaveTypeService
{
    @Override
    public List<mst_leave_type> findAll()
    {
        return new LeaveTypeServiceDAL().findAll();
    }
}
