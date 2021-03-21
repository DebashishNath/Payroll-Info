package com.payroll.payrollWebService.models.payroll;

import com.payroll.payrollWebService.payload.response.MessageResponse;

import javax.persistence.*;

@Entity
@Table(name="mst_state")
public class mst_state {
    @Id
    private Long state_id;
    private String state_code;
    private String state_name;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "country_id", referencedColumnName = "country_id")
    private mst_country country;

    @Transient
    private MessageResponse returnMessage;

    public mst_state(){}

    public Long getState_id() {
        return state_id;
    }

    public void setState_id(Long state_id) {
        this.state_id = state_id;
    }

    public String getState_code() {
        return state_code;
    }

    public void setState_code(String state_code) {
        this.state_code = state_code;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public mst_country getCountry() {
        return country;
    }

    public void setCountry(mst_country country) {
        this.country = country;
    }

    public MessageResponse getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(MessageResponse returnMessage) {
        this.returnMessage = returnMessage;
    }
}
