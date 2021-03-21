package com.payroll.payrollWebService.models.payroll;

import com.payroll.payrollWebService.payload.response.MessageResponse;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="mst_country")
public class mst_country {
    @Id
    private Long country_id;
    private String country_code;
    private String country_name;

    @Transient
    private MessageResponse returnMessage;

    public mst_country() {}

    public Long getCountry_id() {
        return country_id;
    }

    public void setCountry_id(Long country_id) {
        this.country_id = country_id;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public MessageResponse getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(MessageResponse returnMessage) {
        this.returnMessage = returnMessage;

    }
}
