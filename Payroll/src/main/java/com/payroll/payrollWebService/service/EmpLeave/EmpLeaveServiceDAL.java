package com.payroll.payrollWebService.service.EmpLeave;

import com.payroll.payrollWebService.models.common.CodeConstants;
import com.payroll.payrollWebService.models.common.ErrorHandling;
import com.payroll.payrollWebService.models.payroll.mst_location;
import com.payroll.payrollWebService.models.payroll.trn_emp_leave;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import com.payroll.payrollWebService.repository.payroll.EmpLeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmpLeaveServiceDAL extends EmpLeaveServiceImpl {
    @Autowired
    private EmpLeaveRepository empLeaveRep;

    public EmpLeaveServiceDAL() {}

    @Override
    public trn_emp_leave save(trn_emp_leave empLeave)
    {
        MessageResponse msgResp =new MessageResponse();
        try
        {
            trn_emp_leave empLeaveToUpdate = empLeaveRep.save(empLeave);
            msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(),
                    "Leave details updated successfully!");
            empLeaveToUpdate.setReturnMessage(msgResp);
            return empLeaveToUpdate;
        }catch(Exception ex)
        {
            System.out.println("Error Is: " + ex.getMessage());
            empLeave.setReturnMessage(ErrorHandling.GetErrorMessage(ex.getMessage()));
            return empLeave;
        }
    }

    @Override
    public List<trn_emp_leave> findAll(Long empId)
    {
        MessageResponse msgResp =new MessageResponse();
        try
        {
            return ((List<trn_emp_leave>) empLeaveRep.findAll()).stream()
                    .filter(c->c.getEmp().getEmp_id() == empId).collect(Collectors.toList());
        }catch(Exception ex)
        {
            System.out.println("Error Is: " + ex.getMessage());
            return null;
        }
    }

    @Override
    public Optional<trn_emp_leave> findById(Long id){
        return empLeaveRep.findById(id);
    }
}
