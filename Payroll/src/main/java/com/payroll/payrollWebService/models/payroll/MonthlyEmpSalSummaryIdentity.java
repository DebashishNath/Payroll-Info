package com.payroll.payrollWebService.models.payroll;

import java.io.Serializable;

public class MonthlyEmpSalSummaryIdentity implements Serializable {

    private Long month;
    private Long year;
    private Long emp_id;

    public MonthlyEmpSalSummaryIdentity(){}

    public MonthlyEmpSalSummaryIdentity(Long month,Long year,Long emp_id)
    {
        this.month=month;
        this.year=year;
        this.emp_id = emp_id;
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
}
