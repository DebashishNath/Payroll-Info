package com.payroll.payrollWebService.models.payroll;

import com.payroll.payrollWebService.payload.response.MessageResponse;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Entity
@Table(name="mst_fin_year")
public class mst_fin_year {
    @Id
    private Long fin_year_id;
    private String fin_year_desc;
    private Date fin_year_start;
    private Date fin_year_end;

    @Transient
    private MessageResponse returnMessage;

    public mst_fin_year() {}

    public Long getFin_year_id() {
        return fin_year_id;
    }

    public void setFin_year_id(Long fin_year_id) {
        this.fin_year_id = fin_year_id;
    }

    public String getFin_year_desc() {
        return fin_year_desc;
    }

    public void setFin_year_desc(String fin_year_desc) {
        this.fin_year_desc = fin_year_desc;
    }

    public Date getFin_year_start() {
        return fin_year_start;
    }

    public void setFin_year_start(Date fin_year_start) {
        this.fin_year_start = fin_year_start;
    }

    public Date getFin_year_end() {
        return fin_year_end;
    }

    public void setFin_year_end(Date fin_year_end) {
        this.fin_year_end = fin_year_end;
    }

    public MessageResponse getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(MessageResponse returnMessage) {
        this.returnMessage = returnMessage;
    }
}