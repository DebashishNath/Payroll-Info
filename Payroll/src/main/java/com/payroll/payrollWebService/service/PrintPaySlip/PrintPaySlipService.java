package com.payroll.payrollWebService.service.PrintPaySlip;

import com.payroll.payrollWebService.models.payroll.PrintPaySlipIdentity;
import com.payroll.payrollWebService.models.payroll.print_single_pay_slip;

import java.util.List;

public interface PrintPaySlipService {
    print_single_pay_slip PrintSinglePaySlip(PrintPaySlipIdentity printPaySlipIdentity);
}
