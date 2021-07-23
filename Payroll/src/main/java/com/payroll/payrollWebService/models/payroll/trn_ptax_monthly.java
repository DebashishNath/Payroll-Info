package com.payroll.payrollWebService.models.payroll;

import com.payroll.payrollWebService.payload.response.MessageResponse;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="trn_ptax_monthly")
@Data
@NamedStoredProcedureQuery(
        name="ManagePTAXMonthly",
        procedureName="ManagePTAXMonthly",
        resultClasses = { trn_ptax_monthly.class }
)
public class trn_ptax_monthly {

    @EmbeddedId
    private PTaxMonthlyIdentity ptaxMonthlyIdentity;

    private Long no_employees;
    private Long ptax_amount;
    @Transient
    private MessageResponse returnMessage;

    private trn_ptax_monthly(){}

    private trn_ptax_monthly(PTaxMonthlyIdentity ptaxMonthlyIdentity,Long no_employees,
                             Long ptax_amount)
    {
        this.ptaxMonthlyIdentity=ptaxMonthlyIdentity;
        this.no_employees=no_employees;
        this.ptax_amount=ptax_amount;
    }

    public PTaxMonthlyIdentity getPtaxMonthlyIdentity() {
        return ptaxMonthlyIdentity;
    }

    public void setPtaxMonthlyIdentity(PTaxMonthlyIdentity ptaxMonthlyIdentity) {
        this.ptaxMonthlyIdentity = ptaxMonthlyIdentity;
    }

    public Long getNo_employees() {
        return no_employees;
    }

    public void setNo_employees(Long no_employees) {
        this.no_employees = no_employees;
    }

    public Long getPtax_amount() {
        return ptax_amount;
    }

    public void setPtax_amount(Long ptax_amount) {
        this.ptax_amount = ptax_amount;
    }

    public MessageResponse getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(MessageResponse returnMessage) {
        this.returnMessage = returnMessage;
    }
}
