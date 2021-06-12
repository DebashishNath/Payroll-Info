package com.payroll.payrollWebService.controllers.payroll;

import com.payroll.payrollWebService.models.payroll.MonthlyEmpSalSummaryIdentity;
import com.payroll.payrollWebService.models.payroll.PrintPaySlip;
import com.payroll.payrollWebService.service.MonthlyEmpSalSummary.MonthlyEmpSalSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class MonthlyEmpSalSummaryController {

    @Autowired
    private MonthlyEmpSalSummaryService empSalSummaryService;

    @GetMapping("/empsalsummary/{month}/{year}/{empid}")
    public PrintPaySlip PrintSinglePaySlip(@PathVariable(value = "month") Long month,
                                           @PathVariable(value = "year") Long year,
                                           @PathVariable(value = "empid") Long empId){
        MonthlyEmpSalSummaryIdentity empSalSummaryIdentity =new MonthlyEmpSalSummaryIdentity(month,year,empId);
        System.out.println("Inside Controller");
        return empSalSummaryService.PrintSinglePaySlip(empSalSummaryIdentity);
    }
}




