package com.payroll.payrollWebService.models.payroll;

import com.payroll.payrollWebService.payload.response.MessageResponse;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="trn_emp_leave")
public class trn_emp_leave
{
    @Id
    private Long leave_application_id;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "emp_id", referencedColumnName = "emp_id")
    private mst_employee emp;

    private String leave_application_no;
    private Date leave_application_date;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "leave_type_code", referencedColumnName = "leave_type_code")
    private mst_leave_type leaveType;

    private Date from_date;
    private Date to_date;
    private String leave_application_details;
    private String is_approved;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "approved_by", referencedColumnName = "emp_id")
    private mst_employee approvedBy;

    private String remarks;

    @Transient
    private MessageResponse returnMessage;

    public trn_emp_leave(){}

    public trn_emp_leave(Long leave_application_id,mst_employee emp,String leave_application_no,
                         Date leave_application_date,mst_leave_type leaveType,Date from_date,
                         Date to_date,String leave_application_details,String is_approved,
                         mst_employee approvedBy,String remarks)
    {
        this.leave_application_id=leave_application_id;
        this.emp=emp;
        this.leave_application_no=leave_application_no;
        this.leave_application_date=leave_application_date;
        this.leaveType=leaveType;
        this.from_date=from_date;
        this.to_date=to_date;
        this.leave_application_details=leave_application_details;
        this.is_approved=is_approved;
        this.approvedBy=approvedBy;
        this.remarks=remarks;
    }
    public Long getLeave_application_id() {
        return leave_application_id;
    }

    public void setLeave_application_id(Long leave_application_id) {
        this.leave_application_id = leave_application_id;
    }

    public mst_employee getEmp() {
        return emp;
    }

    public void setEmp(mst_employee emp) {
        this.emp = emp;
    }

    public String getLeave_application_no() {
        return leave_application_no;
    }

    public void setLeave_application_no(String leave_application_no) {
        this.leave_application_no = leave_application_no;
    }

    public Date getLeave_application_date() {
        return leave_application_date;
    }

    public void setLeave_application_date(Date leave_application_date) {
        this.leave_application_date = leave_application_date;
    }

    public mst_leave_type getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(mst_leave_type leaveType) {
        this.leaveType = leaveType;
    }

    public Date getFrom_date() {
        return from_date;
    }

    public void setFrom_date(Date from_date) {
        this.from_date = from_date;
    }

    public Date getTo_date() {
        return to_date;
    }

    public void setTo_date(Date to_date) {
        this.to_date = to_date;
    }

    public String getLeave_application_details() {
        return leave_application_details;
    }

    public void setLeave_application_details(String leave_application_details) {
        this.leave_application_details = leave_application_details;
    }

    public String getIs_approved() {
        return is_approved;
    }

    public void setIs_approved(String is_approved) {
        this.is_approved = is_approved;
    }

    public mst_employee getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(mst_employee approvedBy) {
        this.approvedBy = approvedBy;
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
