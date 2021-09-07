package com.payroll.payrollWebService.models.payroll;

import com.payroll.payrollWebService.payload.response.MessageResponse;

import javax.persistence.*;

@Entity
@Table(name="trn_emp_leave_entitlement")
public class trn_emp_leave_entitlement {
    @Id
    private Long emp_leave_entitlement_id;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "emp_id", referencedColumnName = "emp_id")
    private mst_employee emp;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "fin_year_id", referencedColumnName = "fin_year_id")
    private mst_fin_year finYear;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "leave_type_code", referencedColumnName = "leave_type_code")
    private mst_leave_type leaveType;

    private double no_leaves;
    private double carry_forward_leaves;
    private String remarks;

    @Transient
    private MessageResponse returnMessage;

    public trn_emp_leave_entitlement(){}

    public trn_emp_leave_entitlement(Long emp_leave_entitlement_id,mst_employee emp,mst_fin_year finYear,
                                     mst_leave_type leaveType,double no_leaves,double carry_forward_leaves,String remarks)
    {
        this.emp_leave_entitlement_id=emp_leave_entitlement_id;
        this.emp=emp;
        this.finYear=finYear;
        this.leaveType=leaveType;
        this.no_leaves=no_leaves;
        this.carry_forward_leaves=carry_forward_leaves;
        this.remarks=remarks;
    }

    public Long getEmp_leave_entitlement_id() {
        return emp_leave_entitlement_id;
    }

    public void setEmp_leave_entitlement_id(Long emp_leave_entitlement_id) {
        this.emp_leave_entitlement_id = emp_leave_entitlement_id;
    }

    public mst_employee getEmp() {
        return emp;
    }

    public void setEmp(mst_employee emp) {
        this.emp = emp;
    }

    public mst_fin_year getFinYear() {
        return finYear;
    }

    public void setFinYear(mst_fin_year finYear) {
        this.finYear = finYear;
    }

    public mst_leave_type getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(mst_leave_type leaveType) {
        this.leaveType = leaveType;
    }

    public double getNo_leaves() {
        return no_leaves;
    }

    public void setNo_leaves(double no_leaves) {
        this.no_leaves = no_leaves;
    }

    public double getCarry_forward_leaves() {
        return carry_forward_leaves;
    }

    public void setCarry_forward_leaves(double carry_forward_leaves) {
        this.carry_forward_leaves = carry_forward_leaves;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public MessageResponse getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(MessageResponse returnMessage) {
        this.returnMessage = returnMessage;
    }
}
