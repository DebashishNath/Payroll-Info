package com.payroll.payrollWebService.models.payroll;

import com.payroll.payrollWebService.payload.response.MessageResponse;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="mst_holiday")
@Data
@NamedStoredProcedureQuery(
        name="GetHolidayMonthWise",
        procedureName="GetHolidayMonthWise",
        resultClasses = { mst_holiday.class }
)
public class mst_holiday
{
    @Id
    private Long holiday_id;
    private String holiday_code;
    private String holiday_name;
    private Date holiday_date;

    @Transient
    private MessageResponse returnMessage;

    public mst_holiday(){}

    public Long getHoliday_id() {
        return holiday_id;
    }

    public void setHoliday_id(Long holiday_id) {
        this.holiday_id = holiday_id;
    }

    public String getHoliday_code() {
        return holiday_code;
    }

    public void setHoliday_code(String holiday_code) {
        this.holiday_code = holiday_code;
    }

    public String getHoliday_name() {
        return holiday_name;
    }

    public void setHoliday_name(String holiday_name) {
        this.holiday_name = holiday_name;
    }

    public Date getHoliday_date() {
        return holiday_date;
    }

    public void setHoliday_date(Date holiday_date) {
        this.holiday_date = holiday_date;
    }

    public MessageResponse getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(MessageResponse returnMessage) {
        this.returnMessage = returnMessage;
    }
}
