package com.payroll.payrollWebService.models.payroll;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PTaxMonthlyIdentity implements Serializable {

    private int month;
    private int year;
    private Long ptax_slab_id;

    private PTaxMonthlyIdentity() {}

    public PTaxMonthlyIdentity(int month, int year, Long ptax_slab_id) {
        this.month = month;
        this.year = year;
        this.ptax_slab_id = ptax_slab_id;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Long getPtax_slab_id() {
        return ptax_slab_id;
    }

    public void setPtax_slab_id(Long ptax_slab_id) {
        this.ptax_slab_id = ptax_slab_id;
    }
}
