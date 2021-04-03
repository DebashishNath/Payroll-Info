package com.payroll.payrollWebService.controllers.payroll;

import com.payroll.payrollWebService.models.common.CodeConstants;
import com.payroll.payrollWebService.models.payroll.MonthlyEmpSalaryIdentity;
import com.payroll.payrollWebService.models.payroll.trn_monthly_emp_salary_details;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import com.payroll.payrollWebService.service.MonthlyEmpSal.MonthlyEmpSalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class MonthlyEmpSalController {

    @Autowired
    private MonthlyEmpSalService empMonthlySalService;

    @GetMapping("/monthlyempsal/{month}/{year}/{empid}/{earndedid}")
    public Optional<trn_monthly_emp_salary_details> getOne(@PathVariable(value = "month") Long month,
                                                           @PathVariable(value = "year") Long year, @PathVariable(value = "empid") Long empId,
                                                           @PathVariable(value = "earndedid") Long earnDedId){
        MonthlyEmpSalaryIdentity monthlyEmpSalIdentity=new MonthlyEmpSalaryIdentity(month,year,empId,earnDedId);
        return empMonthlySalService.findById(monthlyEmpSalIdentity);
    }

    @PostMapping("/addmonthlysal")
    public ResponseEntity<?> AddEmpSal(@Valid @RequestBody trn_monthly_emp_salary_details monthlyEmpSal) {
        try
        {
            trn_monthly_emp_salary_details monthlyEmpSalToAdd=empMonthlySalService.save(monthlyEmpSal);
            return ResponseEntity.ok(new MessageResponse(monthlyEmpSalToAdd.getReturnMessage().getCode(),
                    monthlyEmpSalToAdd.getReturnMessage().getMessage()));
        }catch(Exception ex){
            return ResponseEntity.ok(new MessageResponse(CodeConstants.FAILURE.getID(),ex.getMessage()));
        }
    }

    @PostMapping("/modifymonthlysal")
    public ResponseEntity<?> ModifyEmpSal(@Valid @RequestBody trn_monthly_emp_salary_details monthlyEmpSal) {
        try
        {
            trn_monthly_emp_salary_details monthlyEmpSalToModify =empMonthlySalService.modify(monthlyEmpSal);
            return ResponseEntity.ok(new MessageResponse(monthlyEmpSalToModify.getReturnMessage().getCode(),
                    monthlyEmpSalToModify.getReturnMessage().getMessage()));
        }catch(Exception ex){
            return ResponseEntity.ok(new MessageResponse(CodeConstants.FAILURE.getID(),ex.getMessage()));
        }
    }

    @PostMapping("/payslip/{month}/{year}/{salarydate}")
    public MessageResponse generatePaySlip(@PathVariable(value = "month") Integer month,
                                           @PathVariable(value = "year") Integer year,
                                           @PathVariable(value = "salarydate") String salarydate)
            throws Exception
    {
        Date payDate=new SimpleDateFormat("yyyy-MM-dd").parse(salarydate);
        return empMonthlySalService.GeneratePaySlip(month,year,payDate);
    }
}




