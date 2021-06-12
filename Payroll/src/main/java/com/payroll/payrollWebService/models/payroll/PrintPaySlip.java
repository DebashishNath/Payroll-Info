package com.payroll.payrollWebService.models.payroll;

import com.payroll.payrollWebService.payload.response.MessageResponse;

import java.util.List;
import java.util.Optional;

public class PrintPaySlip {
    private Optional<trn_monthly_emp_salary_summary> empSalSummary;
    private List<trn_monthly_emp_salary_details> lstEmpSalDetails;
    private MessageResponse returnMessage;

    public PrintPaySlip(){}

    public PrintPaySlip(Optional<trn_monthly_emp_salary_summary> empSalSummary,
                         List<trn_monthly_emp_salary_details> lstEmpSalDetails){
        this.empSalSummary=empSalSummary;
        this.lstEmpSalDetails=lstEmpSalDetails;
    }

    public Optional<trn_monthly_emp_salary_summary> getEmpSalSummary() {
        return empSalSummary;
    }

    public void setEmpSalSummary(Optional<trn_monthly_emp_salary_summary> empSalSummary) {
        this.empSalSummary = empSalSummary;
    }

    public List<trn_monthly_emp_salary_details> getLstEmpSalDetails() {
        return lstEmpSalDetails;
    }

    public void setLstEmpSalDetails(List<trn_monthly_emp_salary_details> lstEmpSalDetails) {
        this.lstEmpSalDetails = lstEmpSalDetails;
    }

    public MessageResponse getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(MessageResponse returnMessage) {
        this.returnMessage = returnMessage;
    }
}
