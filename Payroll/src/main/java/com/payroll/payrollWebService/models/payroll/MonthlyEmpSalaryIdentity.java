package com.payroll.payrollWebService.models.payroll;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class MonthlyEmpSalaryIdentity implements Serializable {
    private Long month;
    private Long year;
    private Long emp_id;
    private Long earn_ded_id;

    public MonthlyEmpSalaryIdentity(){}

    public MonthlyEmpSalaryIdentity(Long month,Long year,Long emp_id,Long earn_ded_id)
    {
        this.month=month;
        this.year=year;
        this.emp_id = emp_id;
        this.earn_ded_id = earn_ded_id;
    }

    public Long getMonth() {
        return month;
    }

    public void setMonth(Long month) {
        this.month = month;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public Long getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(Long emp_id) {
        this.emp_id = emp_id;
    }

    public Long getEarn_ded_id() {
        return earn_ded_id;
    }

    public void setEarn_ded_id(Long earn_ded_id) {
        this.earn_ded_id = earn_ded_id;
    }
}
