package com.payroll.payrollWebService.models.payroll;

import java.io.Serializable;

public class PrintPaySlipIdentity implements Serializable {
    private Long user_id;
    private Long company_id;
    private Long emp_id;
    private Long pay_month;
    private Long pay_year;
    private Long earn_ded_id;

    public PrintPaySlipIdentity(){}

    public PrintPaySlipIdentity(Long user_id, Long company_id, Long pay_month,
                                Long pay_year,Long emp_id,Long earn_ded_id)
    {
        this.user_id=user_id;
        this.company_id=company_id;
        this.pay_month=pay_month;
        this.pay_year=pay_year;
        this.emp_id = emp_id;
        this.earn_ded_id = earn_ded_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Long company_id) {
        this.company_id = company_id;
    }

    public Long getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(Long emp_id) {
        this.emp_id = emp_id;
    }

    public Long getPay_month() {
        return pay_month;
    }

    public void setPay_month(Long pay_month) {
        this.pay_month = pay_month;
    }

    public Long getPay_year() {
        return pay_year;
    }

    public void setPay_year(Long pay_year) {
        this.pay_year = pay_year;
    }

    public Long getEarn_ded_id() {
        return earn_ded_id;
    }

    public void setEarn_ded_id(Long earn_ded_id) {
        this.earn_ded_id = earn_ded_id;
    }
}
