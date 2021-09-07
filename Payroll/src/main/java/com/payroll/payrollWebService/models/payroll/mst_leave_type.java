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
    private String is_leave_without_pay;
    private String carry_forward;

    public mst_leave_type(){}

    public mst_leave_type(String leave_type_code,String leave_type_name,String is_leave_without_pay,
                          String carry_forward)
    {
        this.leave_type_code=leave_type_code;
        this.leave_type_name=leave_type_name;
        this.is_leave_without_pay=is_leave_without_pay;
        this.carry_forward=carry_forward;
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

    public String getIs_leave_without_pay() {
        return is_leave_without_pay;
    }

    public void setIs_leave_without_pay(String is_leave_without_pay) {
        this.is_leave_without_pay = is_leave_without_pay;
    }

    public String getCarry_forward() {
        return carry_forward;
    }

    public void setCarry_forward(String carry_forward) {
        this.carry_forward = carry_forward;
    }
}
