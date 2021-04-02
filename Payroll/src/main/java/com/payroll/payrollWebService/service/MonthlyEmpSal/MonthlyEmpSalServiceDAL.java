package com.payroll.payrollWebService.service.MonthlyEmpSal;

import com.payroll.payrollWebService.models.common.CodeConstants;
import com.payroll.payrollWebService.models.common.ErrorHandling;
import com.payroll.payrollWebService.models.payroll.MonthlyEmpSalaryIdentity;
import com.payroll.payrollWebService.models.payroll.trn_monthly_emp_salary_details;
import com.payroll.payrollWebService.payload.response.MessageResponse;

import com.payroll.payrollWebService.repository.payroll.MonthlyEmpSalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class MonthlyEmpSalServiceDAL extends MonthlyEmpSalServiceImpl{

    @Autowired
    private MonthlyEmpSalaryRepository monthlyEmpSalRep;

    public MonthlyEmpSalServiceDAL() {}

    @Override
    public trn_monthly_emp_salary_details save(trn_monthly_emp_salary_details monthlyEmpSalary)
    {
        MessageResponse msgResp =new MessageResponse();
        try{
            System.out.println("Inside save() of monthly employee salary");
            MonthlyEmpSalaryIdentity monEmpSalaryIdentity= monthlyEmpSalary.getMonEmpSalIdentity();
            Optional<trn_monthly_emp_salary_details> optMonEmpSal=findById(monEmpSalaryIdentity);

            if(optMonEmpSal.isPresent())
            {
                msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),
                        "Duplicate monthly salary component entry for this employee");
                monthlyEmpSalary.setReturnMessage(msgResp);
                return monthlyEmpSalary;
            }
            trn_monthly_emp_salary_details monthlyEmpSalaryToAdd = monthlyEmpSalRep.save(monthlyEmpSalary);
            msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(),
                    "Monthly Employee Salary added successfully!");
            System.out.println("Inside save() of monthly employee salary");
            monthlyEmpSalaryToAdd.setReturnMessage(msgResp);
            return monthlyEmpSalaryToAdd;
        }catch(Exception ex)
        {
            System.out.println("Error Is: " + ex.getMessage());
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),
                    "Failed to add monthly employee Salary");
            monthlyEmpSalary.setReturnMessage(msgResp);
            return monthlyEmpSalary;
        }
    }

    @Override
    public trn_monthly_emp_salary_details modify(trn_monthly_emp_salary_details monthlyEmpSalary)
    {
        MessageResponse msgResp = new MessageResponse();
        try
        {
            Optional<trn_monthly_emp_salary_details> isMonEmpSalPresent = monthlyEmpSalRep.findById(monthlyEmpSalary.getMonEmpSalIdentity());
            if(isMonEmpSalPresent.isPresent())
            {
                trn_monthly_emp_salary_details montlyEmpSalaryToModify = monthlyEmpSalRep.save(monthlyEmpSalary);
                msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(),
                        "Monthly Employee Salary modified successfully!");
                montlyEmpSalaryToModify.setReturnMessage(msgResp);
                return montlyEmpSalaryToModify;
            }
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),
                    "Monthly Employee Salary not found to modify");
            monthlyEmpSalary.setReturnMessage(msgResp);
            return monthlyEmpSalary;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            monthlyEmpSalary.setReturnMessage(ErrorHandling.GetErrorMessage(ex.getMessage()));
            return monthlyEmpSalary;
        }
    }

    @Override
    public Optional<trn_monthly_emp_salary_details> findById(MonthlyEmpSalaryIdentity monthlyEmpSalaryIdentity) {
        return monthlyEmpSalRep.findById(monthlyEmpSalaryIdentity);
    }

}

