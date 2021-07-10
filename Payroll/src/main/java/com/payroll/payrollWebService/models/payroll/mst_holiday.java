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
    private Long Holiday_Id;
    private String Holiday_Code;
    private String Holiday_Name;
    private Date Holiday_Date;

    @Transient
    private MessageResponse returnMessage;

    public mst_holiday(){}

    public Long getHoliday_Id() {
        return Holiday_Id;
    }

    public void setHoliday_Id(Long holiday_Id) {
        Holiday_Id = holiday_Id;
    }

    public String getHoliday_Code() {
        return Holiday_Code;
    }

    public void setHoliday_Code(String holiday_Code) {
        Holiday_Code = holiday_Code;
    }

    public String getHoliday_Name() {
        return Holiday_Name;
    }

    public void setHoliday_Name(String holiday_Name) {
        Holiday_Name = holiday_Name;
    }

    public Date getHoliday_Date() {
        return Holiday_Date;
    }

    public void setHoliday_Date(Date holiday_Date) {
        Holiday_Date = holiday_Date;
    }

    public MessageResponse getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(MessageResponse returnMessage) {
        this.returnMessage = returnMessage;
    }
}
