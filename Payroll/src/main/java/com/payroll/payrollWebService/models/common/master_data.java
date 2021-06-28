package com.payroll.payrollWebService.models.common;

import com.payroll.payrollWebService.payload.response.MessageResponse;

public class master_data {
    private long masterId;
    private String masterCode;
    private String masterName;

    private MessageResponse returnMessage;

    public master_data(){}

    public master_data(long masterId,String masterCode,String masterName)
    {
        this.masterId=masterId;
        this.masterCode=masterCode;
        this.masterName=masterName;
    }

    public long getMasterId() {
        return masterId;
    }

    public void setMasterId(long masterId) {
        this.masterId = masterId;
    }

    public String getMasterCode() {
        return masterCode;
    }

    public void setMasterCode(String masterCode) {
        this.masterCode = masterCode;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public MessageResponse getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(MessageResponse returnMessage) {
        this.returnMessage = returnMessage;
    }
}
