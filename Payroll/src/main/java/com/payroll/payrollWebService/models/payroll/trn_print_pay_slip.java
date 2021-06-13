package com.payroll.payrollWebService.models.payroll;

import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="trn_print_pay_slip")
@Data
@NamedStoredProcedureQuery(
        name="PrintSinglePaySlip",
        procedureName="PrintSinglePaySlip",
        resultClasses = { trn_print_pay_slip.class }
)
public class trn_print_pay_slip {

    @EmbeddedId
    private PrintPaySlipIdentity printPaySlipIdentity;

    private String company_name;
    private String company_address1;
    private String company_address2;
    private String district_name;
    private String company_pin;
    private String company_contact_number;
    private String company_email;
    private String company_TAN_No;

    private String emp_code;
    private String emp_name;
    private String earn_ded_code;
    private String earn_ded_name;
    private String earn_ded_type;
    private Long earn_ded_priority;
    private Double earn_ded_amount;
    private Date salary_date;
    private Double total_earn_amount;
    private Double total_ded_amount;
    private Double net_amount;
    private String remarks;

    public trn_print_pay_slip(){}

    public trn_print_pay_slip(PrintPaySlipIdentity printPaySlipIdentity,
                              String company_name, String company_address1,
                              String company_address2, String district_name, String company_pin,
                              String company_contact_number, String company_email, String company_TAN_No,
                              String emp_code, String emp_name,
                              String earn_ded_code, String earn_ded_name,
                              String earn_ded_type, Long earn_ded_priority, Double earn_ded_amount, Date salary_date,
                              Double total_earn_amount, Double total_ded_amount, Double net_amount, String remarks)
    {
        this.printPaySlipIdentity=printPaySlipIdentity;
        this.company_name=company_name;
        this.company_address1=company_address1;
        this.company_address2=company_address2;
        this.district_name=district_name;
        this.company_pin=company_pin;
        this.company_contact_number=company_contact_number;
        this.company_email=company_email;
        this.company_TAN_No=company_TAN_No;
        this.emp_code=emp_code;
        this.emp_name=emp_name;
        this.earn_ded_code=earn_ded_code;
        this.earn_ded_name=earn_ded_name;
        this.earn_ded_type=earn_ded_type;
        this.earn_ded_priority=earn_ded_priority;
        this.earn_ded_amount=earn_ded_amount;
        this.salary_date=salary_date;
        this.total_earn_amount=total_earn_amount;
        this.total_ded_amount=total_ded_amount;
        this.net_amount=net_amount;
        this.remarks=remarks;
    }

    public PrintPaySlipIdentity getPrintPaySlipIdentity() {
        return printPaySlipIdentity;
    }

    public void setPrintPaySlipIdentity(PrintPaySlipIdentity printPaySlipIdentity) {
        this.printPaySlipIdentity = printPaySlipIdentity;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_address1() {
        return company_address1;
    }

    public void setCompany_address1(String company_address1) {
        this.company_address1 = company_address1;
    }

    public String getCompany_address2() {
        return company_address2;
    }

    public void setCompany_address2(String company_address2) {
        this.company_address2 = company_address2;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public String getCompany_pin() {
        return company_pin;
    }

    public void setCompany_pin(String company_pin) {
        this.company_pin = company_pin;
    }

    public String getCompany_contact_number() {
        return company_contact_number;
    }

    public void setCompany_contact_number(String company_contact_number) {
        this.company_contact_number = company_contact_number;
    }

    public String getCompany_email() {
        return company_email;
    }

    public void setCompany_email(String company_email) {
        this.company_email = company_email;
    }

    public String getCompany_TAN_No() {
        return company_TAN_No;
    }

    public void setCompany_TAN_No(String company_TAN_No) {
        this.company_TAN_No = company_TAN_No;
    }

    public String getEmp_code() {
        return emp_code;
    }

    public void setEmp_code(String emp_code) {
        this.emp_code = emp_code;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getEarn_ded_code() {
        return earn_ded_code;
    }

    public void setEarn_ded_code(String earn_ded_code) {
        this.earn_ded_code = earn_ded_code;
    }

    public String getEarn_ded_name() {
        return earn_ded_name;
    }

    public void setEarn_ded_name(String earn_ded_name) {
        this.earn_ded_name = earn_ded_name;
    }

    public String getEarn_ded_type() {
        return earn_ded_type;
    }

    public void setEarn_ded_type(String earn_ded_type) {
        this.earn_ded_type = earn_ded_type;
    }

    public Long getEarn_ded_priority() {
        return earn_ded_priority;
    }

    public void setEarn_ded_priority(Long earn_ded_priority) {
        this.earn_ded_priority = earn_ded_priority;
    }

    public Double getEarn_ded_amount() {
        return earn_ded_amount;
    }

    public void setEarn_ded_amount(Double earn_ded_amount) {
        this.earn_ded_amount = earn_ded_amount;
    }

    public Date getSalary_date() { return salary_date; }

    public void setSalary_date(Date salary_date) { this.salary_date = salary_date; }

    public Double getNet_amount() { return net_amount; }

    public Double getTotal_earn_amount() { return total_earn_amount; }

    public void setTotal_earn_amount(Double total_earn_amount) { this.total_earn_amount = total_earn_amount; }

    public Double getTotal_ded_amount() {
        return total_ded_amount;
    }

    public void setTotal_ded_amount(Double total_ded_amount) {
        this.total_ded_amount = total_ded_amount;
    }

    public void setNet_amount(Double net_amount) { this.net_amount = net_amount; }

    public String getRemarks() { return remarks; }

    public void setRemarks(String remarks) { this.remarks = remarks; }
}
