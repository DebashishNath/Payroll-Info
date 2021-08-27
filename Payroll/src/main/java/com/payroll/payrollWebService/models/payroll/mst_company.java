package com.payroll.payrollWebService.models.payroll;

import com.payroll.payrollWebService.payload.response.MessageResponse;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="mst_company")
public class mst_company {
    @Id
    private Long company_id;
    private String company_code;
    private String company_name;
    private String company_address1;
    private String company_address2;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "company_district_id", referencedColumnName = "district_id")
    private mst_district district;

    private String company_pin;
    private String company_contact_number;
    private String company_email;
    private String company_tan_no;
    private Date project_start_date;

    @Transient
    private MessageResponse returnMessage;

    public mst_company(){}

    public Long getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Long company_id) {
        this.company_id = company_id;
    }

    public String getCompany_code() {
        return company_code;
    }

    public void setCompany_code(String company_code) {
        this.company_code = company_code;
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

    public mst_district getDistrict() {
        return district;
    }

    public void setDistrict(mst_district district) {
        this.district = district;
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

    public String getCompany_tan_no() {
        return company_tan_no;
    }

    public void setCompany_tan_no(String company_tan_no) {
        this.company_tan_no = company_tan_no;
    }

    public Date getProject_start_date() {
        return project_start_date;
    }

    public void setProject_start_date(Date project_start_date) {
        this.project_start_date = project_start_date;
    }

    public MessageResponse getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(MessageResponse returnMessage) {
        this.returnMessage = returnMessage;
    }
}
