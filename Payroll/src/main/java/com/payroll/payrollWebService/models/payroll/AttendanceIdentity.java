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
}
