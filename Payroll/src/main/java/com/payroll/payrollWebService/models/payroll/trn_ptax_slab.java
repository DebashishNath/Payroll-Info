package com.payroll.payrollWebService.models.payroll;

import com.payroll.payrollWebService.payload.response.MessageResponse;
import javax.persistence.*;

@Entity
@Table(name="trn_ptax_slab")
public class trn_ptax_slab
{
    @Id
    private Long ptax_slab_id;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "fin_year_id", referencedColumnName = "fin_year_id")
    private mst_fin_year finYear;

    private Long ptax_start_range;
    private Long ptax_end_range;
    private Long ptax_rate;
    private String ptax_slab_desc;

    @Transient
    private MessageResponse returnMessage;

    public trn_ptax_slab(){}

    public Long getPtax_slab_id() {
        return ptax_slab_id;
    }

    public void setPtax_slab_id(Long ptax_slab_id) {
        this.ptax_slab_id = ptax_slab_id;
    }

    public mst_fin_year getFinYear() {
        return finYear;
    }

    public void setFinYear(mst_fin_year finYear) {
        this.finYear = finYear;
    }

    public Long getPtax_start_range() {
        return ptax_start_range;
    }

    public void setPtax_start_range(Long ptax_start_range) {
        this.ptax_start_range = ptax_start_range;
    }

    public Long getPtax_end_range() {
        return ptax_end_range;
    }

    public void setPtax_end_range(Long ptax_end_range) {
        this.ptax_end_range = ptax_end_range;
    }

    public Long getPtax_rate() {
        return ptax_rate;
    }

    public void setPtax_rate(Long ptax_rate) {
        this.ptax_rate = ptax_rate;
    }

    public String getPtax_slab_desc() {
        return ptax_slab_desc;
    }

    public void setPtax_slab_desc(String ptax_slab_desc) {
        this.ptax_slab_desc = ptax_slab_desc;
    }

    public MessageResponse getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(MessageResponse returnMessage) {
        this.returnMessage = returnMessage;
    }
}

