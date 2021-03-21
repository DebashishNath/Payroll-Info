package com.payroll.payrollWebService.service.MonthlyEmpSal;

import com.payroll.payrollWebService.models.common.CodeConstants;
import com.payroll.payrollWebService.models.payroll.MonthlyEmpSalaryIdentity;
import com.payroll.payrollWebService.models.payroll.trn_emp_salary_structure;
import com.payroll.payrollWebService.models.payroll.trn_monthly_emp_salary;
import com.payroll.payrollWebService.payload.response.MessageResponse;

import com.payroll.payrollWebService.repository.payroll.MonthlyEmpSalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MonthlyEmpSalServiceDAL extends MonthlyEmpSalServiceImpl{

    @Autowired
    private MonthlyEmpSalaryRepository monthlyEmpSalaryRepository;

    public MonthlyEmpSalServiceDAL() {}

    @Override
    public trn_monthly_emp_salary save(trn_monthly_emp_salary monthlyEmpSalary)
    {
        MessageResponse msgResp =new MessageResponse();
        try{
            System.out.println("Inside save() of monthly employee salary");
            MonthlyEmpSalaryIdentity monEmpSalaryIdentity= monthlyEmpSalary.getMonEmpSalIdentity();
            Optional<trn_monthly_emp_salary> optMonEmpSal=findById(monEmpSalaryIdentity);

            if(optMonEmpSal.isPresent())
            {
                msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),
                        "Duplicate monthly salary component entry for this employee");
                monthlyEmpSalary.setReturnMessage(msgResp);
                return monthlyEmpSalary;
            }
            trn_monthly_emp_salary monthlyEmpSalaryToAdd = monthlyEmpSalaryRepository.save(monthlyEmpSalary);
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
    public Optional<trn_monthly_emp_salary> findById(MonthlyEmpSalaryIdentity monthlyEmpSalaryIdentity) {
        return monthlyEmpSalaryRepository.findById(monthlyEmpSalaryIdentity);
    }

}

