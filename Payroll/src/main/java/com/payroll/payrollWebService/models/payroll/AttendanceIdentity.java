package com.payroll.payrollWebService.models.payroll;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

@Embeddable
public class AttendanceIdentity implements Serializable {

    private int month;
    private int year;
    private Long emp_id;
    private Date attendance_date;

    private AttendanceIdentity() {
    }

    public AttendanceIdentity(int month, int year, Long emp_id, Date attendance_date) {
        this.month = month;
        this.year = year;
        this.emp_id = emp_id;
        this.attendance_date = attendance_date;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Long getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(Long emp_id) {
        this.emp_id = emp_id;
    }

    public Date getAttendance_date() {
        return attendance_date;
    }

    public void setAttendance_date(Date attendance_date) {
        this.attendance_date = attendance_date;
    }
}
