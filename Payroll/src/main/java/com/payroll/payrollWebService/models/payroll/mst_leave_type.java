package com.payroll.payrollWebService.models.payroll;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mst_leave_type")
public class mst_leave_type
{
    @Id
    private String leave_type_code;
    private String leave_type_name;

    public mst_leave_type(){}

    public mst_leave_type(String leave_type_code,String leave_type_name)
    {
        this.leave_type_code=leave_type_code;
        this.leave_type_name=leave_type_name;
    }

    public String getLeave_type_code() {
        return leave_type_code;
    }

    public void setLeave_type_code(String leave_type_code) {
        this.leave_type_code = leave_type_code;
    }

    public String getLeave_type_name() {
        return leave_type_name;
    }

    public void setLeave_type_name(String leave_type_name) {
        this.leave_type_name = leave_type_name;
    }
}
