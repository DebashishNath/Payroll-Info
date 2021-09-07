package com.payroll.payrollWebService.service.EmpLeaveEntitle;

import com.payroll.payrollWebService.models.common.CodeConstants;
import com.payroll.payrollWebService.models.common.ErrorHandling;
import com.payroll.payrollWebService.models.payroll.trn_emp_leave_entitlement;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import com.payroll.payrollWebService.repository.payroll.EmpLeaveEntitlementRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EmpLeaveEntitlementServiceDAL extends EmpLeaveEntitleServiceImpl {
    @Autowired
    private EmpLeaveEntitlementRepository empLeaveEntitlementRep;

    public EmpLeaveEntitlementServiceDAL() {}

    @Override
    public trn_emp_leave_entitlement save(trn_emp_leave_entitlement empLeaveEntitlement)
    {
        MessageResponse msgResp =new MessageResponse();
        try
        {
            trn_emp_leave_entitlement empLeaveEntitlementToUpdate = empLeaveEntitlementRep.save(empLeaveEntitlement);
            msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(),
                    "Leave details updated successfully!");
            empLeaveEntitlementToUpdate.setReturnMessage(msgResp);
            return empLeaveEntitlementToUpdate;
        }catch(Exception ex)
        {
            System.out.println("Error Is: " + ex.getMessage());
            empLeaveEntitlement.setReturnMessage(ErrorHandling.GetErrorMessage(ex.getMessage()));
            return empLeaveEntitlement;
        }
    }

    @Override
    public List<trn_emp_leave_entitlement> findAll(Long empId,Long finYearId)
    {
        MessageResponse msgResp =new MessageResponse();
        try
        {
            return ((List<trn_emp_leave_entitlement>) empLeaveEntitlementRep.findAll()).stream()
                    .filter(c->c.getEmp().getEmp_id() == empId &&
                            c.getFinYear().getFin_year_id() == finYearId)
                    .collect(Collectors.toList());
        }catch(Exception ex)
        {
            System.out.println("Error Is: " + ex.getMessage());
            return null;
        }
    }

    @Override
    public Optional<trn_emp_leave_entitlement> findById(Long id){
        return empLeaveEntitlementRep.findById(id);
    }
}