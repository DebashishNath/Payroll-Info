package com.payroll.payrollWebService.models.payroll;

import com.payroll.payrollWebService.payload.response.MessageResponse;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="mst_employee")
public class mst_employee implements Serializable {
    @Id
    private Long emp_id;
    private String emp_code;
    private String emp_first_name;
    private String emp_middle_name;
    private String emp_last_name;
    private String emp_image_path;
    private String gender;
    private Date dob;
    private String address1;
    private String address2;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "location_id", referencedColumnName = "location_id")
    private mst_location location;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "district_id", referencedColumnName = "district_id")
    private mst_district district;

    private String pin;
    private String contact_number;
    private String email;

    private String aadhar_no;
    private String pan_no;
    private String pf_no;
    private String esi_no;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private mst_category category;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "department_id", referencedColumnName = "department_id")
    private mst_department department;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "designation_id", referencedColumnName = "designation_id")
    private mst_designation designation;

    @Transient
    private MessageResponse returnMessage;

    public mst_employee(){}

    public Long getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(Long emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_code() {
        return emp_code;
    }

    public void setEmp_code(String emp_code) {
        this.emp_code = emp_code;
    }

    public String getEmp_first_name() {
        return emp_first_name;
    }

    public void setEmp_first_name(String emp_first_name) {
        this.emp_first_name = emp_first_name;
    }

    public String getEmp_middle_name() {
        return emp_middle_name;
    }

    public void setEmp_middle_name(String emp_middle_name) {
        this.emp_middle_name = emp_middle_name;
    }

    public String getEmp_last_name() {
        return emp_last_name;
    }

    public void setEmp_last_name(String emp_last_name) {
        this.emp_last_name = emp_last_name;
    }

    public String getEmp_image_path() {
        return emp_image_path;
    }

    public void setEmp_image_path(String emp_image_path) {
        this.emp_image_path = emp_image_path;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public mst_location getLocation() {
        return location;
    }

    public void setLocation(mst_location location) {
        this.location = location;
    }

    public mst_district getDistrict() {
        return district;
    }

    public void setDistrict(mst_district district) {
        this.district = district;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAadhar_no() {
        return aadhar_no;
    }

    public void setAadhar_no(String aadhar_no) {
        this.aadhar_no = aadhar_no;
    }

    public String getPan_no() {
        return pan_no;
    }

    public void setPan_no(String pan_no) {
        this.pan_no = pan_no;
    }

    public String getPf_no() {
        return pf_no;
    }

    public void setPf_no(String pf_no) {
        this.pf_no = pf_no;
    }

    public String getEsi_no() {
        return esi_no;
    }

    public void setEsi_no(String esi_no) {
        this.esi_no = esi_no;
    }

    public mst_category getCategory() {
        return category;
    }

    public void setCategory(mst_category category) {
        this.category = category;
    }

    public mst_department getDepartment() {
        return department;
    }

    public void setDepartment(mst_department department) {
        this.department = department;
    }

    public mst_designation getDesignation() {
        return designation;
    }

    public void setDesignation(mst_designation designation) {
        this.designation = designation;
    }

    public MessageResponse getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(MessageResponse returnMessage) {
        this.returnMessage = returnMessage;
    }
}
