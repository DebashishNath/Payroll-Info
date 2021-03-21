package com.payroll.payrollWebService.models.payroll;

import com.payroll.payrollWebService.payload.response.MessageResponse;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="mst_category")
public class mst_category {
    @Id
    private Long category_id;
    private String category_code;
    private String category_name;

    @Transient
    private MessageResponse returnMessage;

    public mst_category() {}

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public String getCategory_code() {
        return category_code;
    }

    public void setCategory_code(String category_code) {
        this.category_code = category_code;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public MessageResponse getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(MessageResponse returnMessage) {
        this.returnMessage = returnMessage;
    }
}