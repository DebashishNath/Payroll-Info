package com.payroll.payrollWebService.controllers.payroll;

import com.payroll.payrollWebService.models.payroll.PrintPaySlipIdentity;
import com.payroll.payrollWebService.models.payroll.print_single_pay_slip;
import com.payroll.payrollWebService.service.PrintPaySlip.PrintPaySlipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class PrintPaySlipController {

    @Autowired
    private PrintPaySlipService empSalSummaryService;

    @GetMapping("/printpayslip/{userId}/{month}/{year}/{empid}")
    public print_single_pay_slip PrintSinglePaySlip(@PathVariable(value = "userId") Long userId,
                                                       @PathVariable(value = "month") Long month,
                                                       @PathVariable(value = "year") Long year,
                                                       @PathVariable(value = "empid") Long empId){
        PrintPaySlipIdentity empSalSummaryIdentity = new PrintPaySlipIdentity(userId,0L,month,year,empId,0L);
        System.out.println("Inside Controller");
        return empSalSummaryService.PrintSinglePaySlip(empSalSummaryIdentity);
    }
}




