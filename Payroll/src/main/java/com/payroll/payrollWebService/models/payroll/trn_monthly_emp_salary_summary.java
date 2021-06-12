package com.payroll.payrollWebService.models.payroll;

import com.payroll.payrollWebService.payload.response.MessageResponse;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="trn_monthly_emp_salary_summary")
public class trn_monthly_emp_salary_summary {

    @EmbeddedId
    private MonthlyEmpSalSummaryIdentity monEmpSalSummaryIdentity;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "company_id", referencedColumnName = "company_id")
    private mst_company company;

    private Date salary_date;
    private Double total_earn_amount;
    private Double total_ded_amount;
    private Double net_amount;
    private String remarks;

    @Transient
    private MessageResponse returnMessage;

    public trn_monthly_emp_salary_summary(){}

    public trn_monthly_emp_salary_summary(MonthlyEmpSalSummaryIdentity monEmpSalSummaryIdentity,
           mst_company company,Date salary_date,Double total_earn_amount,Double total_ded_amount,
           Double net_amount,String remarks)
    {
        this.monEmpSalSummaryIdentity =monEmpSalSummaryIdentity;
        this.company=company;
        this.salary_date=salary_date;
        this.total_earn_amount=total_earn_amount;
        this.total_ded_amount=total_ded_amount;
        this.net_amount=net_amount;
        this.remarks=remarks;
    }

    public MonthlyEmpSalSummaryIdentity getMonEmpSalSummaryIdentity() {
        return monEmpSalSummaryIdentity;
    }

    public void setMonEmpSalSummaryIdentity(MonthlyEmpSalSummaryIdentity monEmpSalSummaryIdentity) {
        this.monEmpSalSummaryIdentity = monEmpSalSummaryIdentity;
    }

    public mst_company getCompany() {
        return company;
    }

    public void setCompany(mst_company company) {
        this.company = company;
    }

    public Date getSalary_date() {
        return salary_date;
    }

    public void setSalary_date(Date salary_date) {
        this.salary_date = salary_date;
    }

    public Double getTotal_earn_amount() {
        return total_earn_amount;
    }

    public void setTotal_earn_amount(Double total_earn_amount) {
        this.total_earn_amount = total_earn_amount;
    }

    public Double getTotal_ded_amount() {
        return total_ded_amount;
    }

    public void setTotal_ded_amount(Double total_ded_amount) {
        this.total_ded_amount = total_ded_amount;
    }

    public Double getNet_amount() {
        return net_amount;
    }

    public void setNet_amount(Double net_amount) {
        this.net_amount = net_amount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public MessageResponse getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(MessageResponse returnMessage) {
        this.returnMessage = returnMessage;
    }

}
