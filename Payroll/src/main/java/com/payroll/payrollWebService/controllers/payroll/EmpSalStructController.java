package com.payroll.payrollWebService.controllers.payroll;

import com.payroll.payrollWebService.models.common.CodeConstants;
import com.payroll.payrollWebService.models.payroll.EmpEarnDedIdentity;
import com.payroll.payrollWebService.models.payroll.trn_emp_salary_structure;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import com.payroll.payrollWebService.service.EmpSalStruct.EmpSalStructService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class EmpSalStructController {

    @Autowired
    private EmpSalStructService empSalStructService;

    @GetMapping("/empsalstruct/{empid}/{earndedid}")
    public Optional<trn_emp_salary_structure> getOne(@PathVariable(value = "empid") Long empId,
                                                     @PathVariable(value = "earndedid") Long earnDedId){
        EmpEarnDedIdentity empEarnDedIdentity=new EmpEarnDedIdentity(empId,earnDedId);
        return empSalStructService.findById(empEarnDedIdentity);
    }

    @GetMapping("/empsalstruct_findbyempid/{empid}")
    public List<trn_emp_salary_structure> getOne(@PathVariable(value = "empid") Long empId){
        return empSalStructService.findAll(empId);
    }

    @PostMapping("/addEmpSalStruct")
    public ResponseEntity<?> AddEmpSalStruct(@Valid @RequestBody trn_emp_salary_structure empSalStruct) {
        try
        {
            trn_emp_salary_structure empSalStructToAdd=empSalStructService.save(empSalStruct);
            return ResponseEntity.ok(new MessageResponse(empSalStructToAdd.getReturnMessage().getCode(),
                    empSalStructToAdd.getReturnMessage().getMessage()));
        }catch(Exception ex){
            return ResponseEntity.ok(new MessageResponse(CodeConstants.FAILURE.getID(),ex.getMessage()));
        }
    }

    @PostMapping("/modifyEmpSalStruct")
    public ResponseEntity<?> ModifyEmpSalStruct(@Valid @RequestBody trn_emp_salary_structure empSalStruct) {
        try
        {
            trn_emp_salary_structure empSalStructToModify =empSalStructService.modify(empSalStruct);
            return ResponseEntity.ok(new MessageResponse(empSalStructToModify.getReturnMessage().getCode(),
                    empSalStructToModify.getReturnMessage().getMessage()));
        }catch(Exception ex){
            return ResponseEntity.ok(new MessageResponse(CodeConstants.FAILURE.getID(),ex.getMessage()));
        }
    }

    @PostMapping("/updateEmpSalStruct")
    public ResponseEntity<?> UpdateEmpSalStruct(@Valid @RequestBody trn_emp_salary_structure empSalStruct) {
        try
        {
            System.out.println("EmpId: " + empSalStruct.getEmpEmpEarnDedIdentity().getEmp_id()
                    + ",Earn Ded Id: " + empSalStruct.getEmpEmpEarnDedIdentity().getEarn_ded_id()
                    + ",Amount: " + empSalStruct.getEarn_ded_amount());

            trn_emp_salary_structure empSalStructToUpdate=empSalStructService.updateEmpSalStruct(empSalStruct);
            return ResponseEntity.ok(new MessageResponse(empSalStructToUpdate.getReturnMessage().getCode(),
                    empSalStructToUpdate.getReturnMessage().getMessage()));
        }catch(Exception ex){
            return ResponseEntity.ok(new MessageResponse(CodeConstants.FAILURE.getID(),ex.getMessage()));
        }
    }
}