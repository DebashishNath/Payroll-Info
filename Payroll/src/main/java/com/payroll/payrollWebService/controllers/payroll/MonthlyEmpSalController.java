package com.payroll.payrollWebService.controllers.payroll;

import com.payroll.payrollWebService.models.common.CodeConstants;
import com.payroll.payrollWebService.models.payroll.trn_monthly_emp_salary;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import com.payroll.payrollWebService.service.MonthlyEmpSal.MonthlyEmpSalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class MonthlyEmpSalController {

    @Autowired
    private MonthlyEmpSalService empMonthlySalService;

    @PostMapping("/addmonthlysal")
    public ResponseEntity<?> AddEmpSal(@Valid @RequestBody trn_monthly_emp_salary monthlyEmpSal) {
        try
        {
            trn_monthly_emp_salary monthlyEmpSalToAdd=empMonthlySalService.save(monthlyEmpSal);
            return ResponseEntity.ok(new MessageResponse(monthlyEmpSalToAdd.getReturnMessage().getCode(),
                    monthlyEmpSalToAdd.getReturnMessage().getMessage()));
        }catch(Exception ex){
            return ResponseEntity.ok(new MessageResponse(CodeConstants.FAILURE.getID(),ex.getMessage()));
        }
    }

    @PostMapping("/modifymonthlysal")
    public ResponseEntity<?> ModifyEmpSal(@Valid @RequestBody trn_monthly_emp_salary monthlyEmpSal) {
        try
        {
            trn_monthly_emp_salary monthlyEmpSalToModify =empMonthlySalService.modify(monthlyEmpSal);
            return ResponseEntity.ok(new MessageResponse(monthlyEmpSalToModify.getReturnMessage().getCode(),
                    monthlyEmpSalToModify.getReturnMessage().getMessage()));
        }catch(Exception ex){
            return ResponseEntity.ok(new MessageResponse(CodeConstants.FAILURE.getID(),ex.getMessage()));
        }
    }
}




