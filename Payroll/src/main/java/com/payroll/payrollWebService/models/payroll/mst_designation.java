package com.payroll.payrollWebService.models.payroll;

import com.payroll.payrollWebService.payload.response.MessageResponse;
import javax.persistence.*;

@Entity
@Table(name="mst_designation")
public class mst_designation {
    @Id
    private Long designation_id;
    private String designation_code;
    private String designation_name;

    @Transient
    private MessageResponse returnMessage;

    public mst_designation(){}

    public Long getDesignation_id() {
        return designation_id;
    }

    public void setDesignation_id(Long designation_id) {
        this.designation_id = designation_id;
    }

    public String getDesignation_code() {
        return designation_code;
    }

    public void setDesignation_code(String designation_code) {
        this.designation_code = designation_code;
    }

    public String getDesignation_name() {
        return designation_name;
    }

    public void setDesignation_name(String designation_name) {
        this.designation_name = designation_name;
    }

    public MessageResponse getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(MessageResponse returnMessage) {
        this.returnMessage = returnMessage;
    }
}
