package com.payroll.payrollWebService.models.payroll;

import com.payroll.payrollWebService.payload.response.MessageResponse;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="mst_department")
public class mst_department {
    @Id
    private Long department_id;
    private String department_code;
    private String department_name;

    @Transient
    private MessageResponse returnMessage;

    public mst_department() {}

    public Long getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Long department_id) {
        this.department_id = department_id;
    }

    public String getDepartment_code() {
        return department_code;
    }

    public void setDepartment_code(String department_code) {
        this.department_code = department_code;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public MessageResponse getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(MessageResponse returnMessage) {
        this.returnMessage = returnMessage;
    }
}