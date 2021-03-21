package com.payroll.payrollWebService.models.payroll;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class EmpEarnDedIdentity implements Serializable {
    private Long emp_id;
    private Long earn_ded_id;

    private EmpEarnDedIdentity(){}

    public EmpEarnDedIdentity(Long emp_id,Long earn_ded_id) {
        this.emp_id = emp_id;
        this.earn_ded_id = earn_ded_id;
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
