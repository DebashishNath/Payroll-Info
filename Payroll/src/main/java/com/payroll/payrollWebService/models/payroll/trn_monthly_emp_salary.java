package com.payroll.payrollWebService.models.payroll;

import com.payroll.payrollWebService.payload.response.MessageResponse;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="trn_monthly_emp_salary")
public class trn_monthly_emp_salary {

    @EmbeddedId
    private MonthlyEmpSalaryIdentity monEmpSalIdentity;

    private Double earn_ded_amount;

    @Transient
    private MessageResponse returnMessage;

    public trn_monthly_emp_salary(){}

    public trn_monthly_emp_salary(MonthlyEmpSalaryIdentity monEmpSalIdentity,
                                  Double earn_ded_amount)
    {
        this.monEmpSalIdentity =monEmpSalIdentity;
        this.earn_ded_amount=earn_ded_amount;
    }

    public MonthlyEmpSalaryIdentity getMonEmpSalIdentity() {
        return monEmpSalIdentity;
    }

    public void setMonEmpSalIdentity(MonthlyEmpSalaryIdentity monEmpSalIdentity) {
        this.monEmpSalIdentity = monEmpSalIdentity;
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
