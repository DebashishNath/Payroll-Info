package com.payroll.payrollWebService.service.PrintPaySlip;

import com.payroll.payrollWebService.models.payroll.PrintPaySlipIdentity;
import com.payroll.payrollWebService.models.payroll.print_single_pay_slip;

import java.util.List;

abstract class PrintPaySlipServiceImpl implements PrintPaySlipService {

    @Override
    public print_single_pay_slip PrintSinglePaySlip(PrintPaySlipIdentity monthlyEmpSalarySummaryIdentity) {
        return new PrintPaySlipServiceDAL().PrintSinglePaySlip(monthlyEmpSalarySummaryIdentity);
    }
}
