package com.payroll.payrollWebService.controllers.payroll;

import com.payroll.payrollWebService.models.payroll.MonthlyEmpSalSummaryIdentity;
import com.payroll.payrollWebService.models.payroll.trn_monthly_emp_salary_summary;
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
    public Optional<trn_monthly_emp_salary_summary> PrintSinglePaySlip(@PathVariable(value = "month") Long month,
                                                           @PathVariable(value = "year") Long year,
                                                           @PathVariable(value = "empid") Long empId){
        MonthlyEmpSalSummaryIdentity empSalSummaryIdentity =new MonthlyEmpSalSummaryIdentity(month,year,empId);
        System.out.println("Inside Controller");
        return empSalSummaryService.PrintSinglePaySlip(empSalSummaryIdentity);
    }
}




