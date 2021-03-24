package com.payroll.payrollWebService.models.payroll;

import com.payroll.payrollWebService.payload.response.MessageResponse;

import javax.persistence.*;

@Entity
@Table(name="mst_district")
public class mst_district {
    @Id
    private Long district_id;
    private String district_code;
    private String district_name;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "state_id", referencedColumnName = "state_id")
    private mst_state state;

    @Transient
    private MessageResponse returnMessage;

    public mst_district() {}

    public Long getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(Long district_id) {
        this.district_id = district_id;
    }

    public String getDistrict_code() {
        return district_code;
    }

    public void setDistrict_code(String district_code) {
        this.district_code = district_code;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public mst_state getState() {
        return state;
    }

    public void setState(mst_state state) {
        this.state = state;
    }

    public MessageResponse getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(MessageResponse returnMessage) {
        this.returnMessage = returnMessage;
    }
}
