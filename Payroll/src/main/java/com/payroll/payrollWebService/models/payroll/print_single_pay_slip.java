package com.payroll.payrollWebService.models.payroll;

import com.payroll.payrollWebService.payload.response.MessageResponse;
import java.util.List;

public class print_single_pay_slip {
    private List<trn_print_pay_slip> lstPrintPaySlip;
    private MessageResponse returnMessage;

    public print_single_pay_slip(){}

    public print_single_pay_slip(List<trn_print_pay_slip> lstPrintPaySlip){
        this.lstPrintPaySlip=lstPrintPaySlip;
    }

    public List<trn_print_pay_slip> getLstPrintPaySlip() {
        return lstPrintPaySlip;
    }

    public void setLstPrintPaySlip(List<trn_print_pay_slip> lstPrintPaySlip) {
        this.lstPrintPaySlip = lstPrintPaySlip;
    }

    public MessageResponse getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(MessageResponse returnMessage) {
        this.returnMessage = returnMessage;
    }
}
