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
    private String attendance_type_code;
    private String remarks;

    @Transient
    private MessageResponse returnMessage;

    public trn_emp_attendance(){}

    public trn_emp_attendance(AttendanceIdentity attendanceIdentity,
                              String attendance_type_code,String remarks)
    {
        this.attendanceIdentity =attendanceIdentity;
        this.attendance_type_code=attendance_type_code;
        this.remarks=remarks;
    }

    public AttendanceIdentity getAttendanceIdentity() {
        return attendanceIdentity;
    }

    public void setAttendanceIdentity(AttendanceIdentity attendanceIdentity) {
        this.attendanceIdentity = attendanceIdentity;
    }

    public String getAttendance_type_code() {
        return attendance_type_code;
    }

    public void setAttendance_type_code(String attendance_type_code) {
        this.attendance_type_code = attendance_type_code;
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
