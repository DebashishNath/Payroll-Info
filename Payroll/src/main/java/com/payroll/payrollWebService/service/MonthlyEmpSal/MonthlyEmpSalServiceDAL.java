package com.payroll.payrollWebService.service.MonthlyEmpSal;

import com.payroll.payrollWebService.models.common.CodeConstants;
import com.payroll.payrollWebService.models.common.ErrorHandling;
import com.payroll.payrollWebService.models.payroll.MonthlyEmpSalaryIdentity;
import com.payroll.payrollWebService.models.payroll.trn_monthly_emp_salary_details;
import com.payroll.payrollWebService.payload.response.MessageResponse;

import com.payroll.payrollWebService.repository.payroll.MonthlyEmpSalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.time.Month;
import java.util.Date;
import java.util.Optional;

@Service
class MonthlyEmpSalServiceDAL extends MonthlyEmpSalServiceImpl{

    @Autowired
    private MonthlyEmpSalaryRepository monthlyEmpSalRep;

    @PersistenceContext
    private EntityManager em;

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

    @Override
    public MessageResponse GeneratePaySlip(Integer p_month, Integer p_year, Date p_salary_date) {
        MessageResponse msp = new MessageResponse();
        try
        {
            String returnMessage=ExecutePaySlip(p_month,p_year,p_salary_date);
            String returnCode=returnMessage.substring(0,1);
            msp.setCode(Integer.parseInt(returnCode));
            msp.setMessage(returnMessage.substring(2));
        } catch (Exception ex)
        {
            msp.setCode(CodeConstants.FAILURE.getID());
            msp.setMessage(ex.getMessage());
        }
        return msp;
    }

    private String ExecutePaySlip(Integer p_month,Integer p_year,Date p_salary_date){
        StoredProcedureQuery generateAttendance =
                em.createNamedStoredProcedureQuery("GeneratePaySlip")
                        .registerStoredProcedureParameter("p_month", Integer.class, ParameterMode.IN)
                        .setParameter("p_month", p_month)
                        .registerStoredProcedureParameter("p_year", Integer.class, ParameterMode.IN)
                        .setParameter("p_year", p_year)
                        .registerStoredProcedureParameter("p_salary_date", Date.class, ParameterMode.IN)
                        .setParameter("p_salary_date", p_salary_date)
                        .registerStoredProcedureParameter("p_return_message", String.class, ParameterMode.OUT);
        generateAttendance.execute();
        return (String)generateAttendance.getOutputParameterValue("p_return_message");
    }

}

