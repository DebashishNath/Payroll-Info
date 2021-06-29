package com.payroll.payrollWebService.models.payroll;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mst_attendance_type")
public class mst_attendance_type
{
    @Id
    private String attendance_type_code;
    private String attendance_type_name;

    public mst_attendance_type(){}

    public mst_attendance_type(String attendance_type_code,String attendance_type_name)
    {
        this.attendance_type_code=attendance_type_code;
        this.attendance_type_name=attendance_type_name;
    }

    public String getAttendance_type_code() {
        return attendance_type_code;
    }

    public void setAttendance_type_code(String attendance_type_code) {
        this.attendance_type_code = attendance_type_code;
    }

    public String getAttendance_type_name() {
        return attendance_type_name;
    }

    public void setAttendance_type_name(String attendance_type_name) {
        this.attendance_type_name = attendance_type_name;
    }
}
