package com.payroll.payrollWebService.models.payroll;

import com.payroll.payrollWebService.payload.response.MessageResponse;
import javax.persistence.*;
import java.util.Date;


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
    private String pay_sheet_values;
    private String pay_sheet_columns;
    private String emp_detail_values;
    private String remarks;

    @Transient
    private MessageResponse returnMessage;

    public trn_monthly_emp_salary_summary(){}

    public  trn_monthly_emp_salary_summary(MonthlyEmpSalSummaryIdentity monEmpSalSummaryIdentity,
            mst_company company,Date salary_date,Double total_earn_amount,Double total_ded_amount,
            Double net_amount,String pay_sheet_values,String pay_sheet_columns,String emp_detail_values,
            String remarks)
    {
        this.monEmpSalSummaryIdentity =monEmpSalSummaryIdentity;
        this.company=company;
        this.salary_date=salary_date;
        this.total_earn_amount=total_earn_amount;
        this.total_ded_amount=total_ded_amount;
        this.net_amount=net_amount;
        this.pay_sheet_values=pay_sheet_values;
        this.pay_sheet_columns=pay_sheet_columns;
        this.emp_detail_values=emp_detail_values;
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

    public String getPay_sheet_columns() {
        return pay_sheet_columns;
    }

    public void setPay_sheet_columns(String pay_sheet_columns) {
        this.pay_sheet_columns = pay_sheet_columns;
    }

    public String getPay_sheet_values() {
        return pay_sheet_values;
    }

    public void setPay_sheet_values(String pay_sheet_values) {
        this.pay_sheet_values = pay_sheet_values;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getEmp_detail_values() {
        return emp_detail_values;
    }

    public void setEmp_detail_values(String emp_detail_values) {
        this.emp_detail_values = emp_detail_values;
    }

    public MessageResponse getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(MessageResponse returnMessage) {
        this.returnMessage = returnMessage;
    }

}
