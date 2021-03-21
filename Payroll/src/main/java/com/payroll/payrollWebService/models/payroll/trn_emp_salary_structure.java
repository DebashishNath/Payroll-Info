package com.payroll.payrollWebService.models.payroll;

import com.payroll.payrollWebService.payload.response.MessageResponse;
import javax.persistence.*;

@Entity
@Table(name="trn_emp_salary_structure")
public class trn_emp_salary_structure {

    @EmbeddedId
    private EmpEarnDedIdentity empEmpEarnDedIdentity;

    private Double earn_ded_amount;

    @Transient
    private MessageResponse returnMessage;

    public trn_emp_salary_structure(){}

    public trn_emp_salary_structure(EmpEarnDedIdentity employeeIdentity, Double earn_ded_amount)
    {
        this.empEmpEarnDedIdentity =employeeIdentity;
        this.earn_ded_amount=earn_ded_amount;
    }

    public EmpEarnDedIdentity getEmpEmpEarnDedIdentity() {
        return empEmpEarnDedIdentity;
    }

    public void setEmpEmpEarnDedIdentity(EmpEarnDedIdentity empEmpEarnDedIdentity) {
        this.empEmpEarnDedIdentity = empEmpEarnDedIdentity;
    }

    public Double getEarn_ded_amount() {
        return earn_ded_amount;
    }

    public void setEarn_ded_amount(Double earn_ded_amount) {
        this.earn_ded_amount = earn_ded_amount;
    }

    public MessageResponse getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(MessageResponse returnMessage) {
        this.returnMessage = returnMessage;
    }

}
