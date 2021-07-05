package com.payroll.payrollWebService.repository.payroll;

import org.springframework.data.repository.CrudRepository;
import com.payroll.payrollWebService.models.payroll.mst_leave_type;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveTypeRepository extends CrudRepository
        <mst_leave_type, String> {
}
