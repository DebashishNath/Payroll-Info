package com.payroll.payrollWebService.service.EmpSalStruct;

import com.payroll.payrollWebService.models.common.CodeConstants;
import com.payroll.payrollWebService.models.common.ErrorHandling;
import com.payroll.payrollWebService.models.payroll.EmpEarnDedIdentity;
import com.payroll.payrollWebService.models.payroll.trn_emp_salary_structure;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import com.payroll.payrollWebService.repository.payroll.EmpSalStructRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class EmpSalStructServiceDAL extends EmpSalStructServiceImpl{

    @Autowired
    private EmpSalStructRepository empSalStructRep;

    public EmpSalStructServiceDAL(){}

    @Override
    public trn_emp_salary_structure save(trn_emp_salary_structure empSalaryStructure)
    {
        MessageResponse msgResp =new MessageResponse();
        try{
            System.out.println("Inside save() of employee salary structure");
            EmpEarnDedIdentity empEarnDedIdentity= empSalaryStructure.getEmpEmpEarnDedIdentity();
            Optional<trn_emp_salary_structure> optEmpSal=findById(empEarnDedIdentity);

            if(optEmpSal.isPresent())
            {
                msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),
                        "Duplicate salary structure entry for this employee");
                empSalaryStructure.setReturnMessage(msgResp);
                return empSalaryStructure;
            }
            trn_emp_salary_structure empSalStructToAdd = empSalStructRep.save(empSalaryStructure);
            msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(),
                    "Salary Structure added successfully!");
            empSalStructToAdd.setReturnMessage(msgResp);
            return empSalStructToAdd;
        }catch(Exception ex)
        {
            System.out.println("Error Is: " + ex.getMessage());
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),
                    "Failed to add salary Structure");
            empSalaryStructure.setReturnMessage(msgResp);
            return empSalaryStructure;
        }
    }

    @Override
    public trn_emp_salary_structure modify(trn_emp_salary_structure empSalaryStructure)
    {
        MessageResponse msgResp = new MessageResponse();
        try
        {
            Optional<trn_emp_salary_structure> isEmpSalStructPresent = empSalStructRep.findById(empSalaryStructure.getEmpEmpEarnDedIdentity());
            if(isEmpSalStructPresent.isPresent())
            {
                trn_emp_salary_structure empSalStructToModify = empSalStructRep.save(empSalaryStructure);
                msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(),
                        "Salary structure modified successfully!");
                empSalStructToModify.setReturnMessage(msgResp);
                return empSalStructToModify;
            }
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),
                    "Salary structure not found to modify");
            empSalaryStructure.setReturnMessage(msgResp);
            return empSalaryStructure;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            empSalaryStructure.setReturnMessage(ErrorHandling.GetErrorMessage(ex.getMessage()));
            return empSalaryStructure;
        }
    }

    @Override
    public Optional<trn_emp_salary_structure> findById(EmpEarnDedIdentity empEarnDedIdentity) {
        return empSalStructRep.findById(empEarnDedIdentity); }
}
