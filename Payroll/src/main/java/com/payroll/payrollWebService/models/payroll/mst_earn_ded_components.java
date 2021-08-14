package com.payroll.payrollWebService.models.payroll;

import com.payroll.payrollWebService.payload.response.MessageResponse;

import javax.persistence.*;

@Entity
@Table(name="mst_earn_ded_components")
public class mst_earn_ded_components {
    @Id
    private Long earn_ded_id;
    private String earn_ded_code;
    private String earn_ded_name;
    private String earn_ded_type;
    private Long earn_ded_priority;
    private String is_taxable;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "earn_ded_tag_id", referencedColumnName = "earn_ded_tag_id")
    private mst_earn_ded_tag earnDedTag;

    @Transient
    private MessageResponse returnMessage;

    public mst_earn_ded_components(){}

    public Long getEarn_ded_id() {
        return earn_ded_id;
    }

    public void setEarn_ded_id(Long earn_ded_id) {
        this.earn_ded_id = earn_ded_id;
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

    public String getIs_taxable() {
        return is_taxable;
    }

    public void setIs_taxable(String is_taxable) {
        this.is_taxable = is_taxable;
    }

    public mst_earn_ded_tag getEarnDedTag() {
        return earnDedTag;
    }

    public void setEarnDedTag(mst_earn_ded_tag earnDedTag) {
        this.earnDedTag = earnDedTag;
    }

    public MessageResponse getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(MessageResponse returnMessage) {
        this.returnMessage = returnMessage;
    }
}
