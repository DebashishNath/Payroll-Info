package com.payroll.payrollWebService.service.Employee;

import com.payroll.payrollWebService.models.common.CodeConstants;
import com.payroll.payrollWebService.models.common.ErrorHandling;
import com.payroll.payrollWebService.models.payroll.mst_employee;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import com.payroll.payrollWebService.repository.payroll.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class EmployeeServiceDAL extends EmployeeServiceImpl{

    @Autowired
    private EmployeeRepository empRep;

    public EmployeeServiceDAL() {}

    @Override
    public mst_employee save(mst_employee employee)
    {
        MessageResponse msgResp =new MessageResponse();
        try{
            mst_employee employeeToAdd = empRep.save(employee);
            msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(),
                    "New Employee details added successfully!");
            employeeToAdd.setReturnMessage(msgResp);
            return employeeToAdd;
        }catch(Exception ex)
        {
            System.out.println("Error Is: " + ex.getMessage() + "," + ex.getStackTrace());
            employee.setReturnMessage(ErrorHandling.GetErrorMessage(ex.getMessage()));
            return employee;
        }
    }

    @Override
    public mst_employee modify(mst_employee employee)
    {
        MessageResponse msgResp = new MessageResponse();
        try
        {
            Optional<mst_employee> isEmpPresent = empRep.findById(employee.getEmp_id());
            if(isEmpPresent.isPresent()) {
                mst_employee employeeToModify = empRep.save(employee);
                msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(),
                        "Employee details modified successfully!");
                employeeToModify.setReturnMessage(msgResp);
                return employeeToModify;
            }
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),
                    "Employee details not found to modify");
            employee.setReturnMessage(msgResp);
            return employee;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),

                    "Failed to modify employee");
            employee.setReturnMessage(msgResp);
            return employee;
        }
    }

    @Override
    public List<mst_employee> findAll() {
        return (List<mst_employee>) empRep.findAll();
    }

    @Override
    public Optional<mst_employee> findById(Long id) {return empRep.findById(id); }

    @Override
    public MessageResponse removeOne(Long id)
    {
        MessageResponse msgResp = new MessageResponse();
        try
        {
            empRep.deleteById(id);
            msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(), "Employee details deleted successfully!");
            return msgResp;
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),"Failed to delete employee");
            return msgResp;
        }
    }
}

