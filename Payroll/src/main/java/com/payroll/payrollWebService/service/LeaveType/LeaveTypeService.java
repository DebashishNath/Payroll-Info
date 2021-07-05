package com.payroll.payrollWebService.service.LeaveType;

import com.payroll.payrollWebService.models.payroll.mst_leave_type;
import java.util.List;

public interface LeaveTypeService
{
    List<mst_leave_type> findAll();
}

