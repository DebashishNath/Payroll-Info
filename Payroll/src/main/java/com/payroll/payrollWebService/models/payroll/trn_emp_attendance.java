package com.payroll.payrollWebService.models.payroll;

import com.payroll.payrollWebService.payload.response.MessageResponse;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="trn_emp_attendance")
@Data
@NamedStoredProcedureQuery(
        name="GenerateMonthlyAttendance",
        procedureName="GenerateMonthlyAttendance",
        resultClasses = { trn_emp_attendance.class }
)
public class trn_emp_attendance {

    @EmbeddedId
    private AttendanceIdentity attendanceIdentity;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "attendance_type_code", referencedColumnName = "attendance_type_code")
    private mst_attendance_type attendanceType;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "leave_type_code", referencedColumnName = "leave_type_code")
    private mst_leave_type leaveType;

    private String remarks;

    @Transient
    private MessageResponse returnMessage;

    public trn_emp_attendance(){}

    public trn_emp_attendance(AttendanceIdentity attendanceIdentity,
                              mst_leave_type leaveType,mst_attendance_type attendanceType,
                              String remarks)
    {
        this.attendanceIdentity =attendanceIdentity;
        this.attendanceType=attendanceType;
        this.leaveType=leaveType;
        this.remarks=remarks;
    }

    public AttendanceIdentity getAttendanceIdentity() {
        return attendanceIdentity;
    }

    public void setAttendanceIdentity(AttendanceIdentity attendanceIdentity) {
        this.attendanceIdentity = attendanceIdentity;
    }

    public mst_attendance_type getAttendanceType() {
        return attendanceType;
    }

    public void setAttendanceType(mst_attendance_type attendanceType) {
        this.attendanceType = attendanceType;
    }

    public mst_leave_type getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(mst_leave_type leaveType) {
        this.leaveType = leaveType;
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
