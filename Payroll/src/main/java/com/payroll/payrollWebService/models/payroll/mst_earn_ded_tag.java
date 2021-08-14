package com.payroll.payrollWebService.models.payroll;

import com.payroll.payrollWebService.payload.response.MessageResponse;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="mst_earn_ded_tag")
public class mst_earn_ded_tag {
    @Id
    private Long earn_ded_tag_id;
    private String earn_ded_tag_name;

    @Transient
    private MessageResponse returnMessage;

    public mst_earn_ded_tag(){}

    public Long getEarn_ded_tag_id() {
        return earn_ded_tag_id;
    }

    public void setEarn_ded_tag_id(Long earn_ded_tag_id) {
        this.earn_ded_tag_id = earn_ded_tag_id;
    }

    public String getEarn_ded_tag_name() {
        return earn_ded_tag_name;
    }

    public void setEarn_ded_tag_name(String earn_ded_tag_name) {
        this.earn_ded_tag_name = earn_ded_tag_name;
    }

    public MessageResponse getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(MessageResponse returnMessage) {
        this.returnMessage = returnMessage;
    }
}
