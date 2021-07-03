package com.payroll.payrollWebService.controllers.payroll;

import com.payroll.payrollWebService.models.common.CodeConstants;
import com.payroll.payrollWebService.models.payroll.trn_emp_leave;
import com.payroll.payrollWebService.models.common.CodeConstants;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import com.payroll.payrollWebService.service.EmpLeave.EmpLeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class EmpLeaveController {

    @Autowired
    private EmpLeaveService empLeaveService;

    @GetMapping("/emp_leave/{id}")
    public List<trn_emp_leave> listEmpLeave(@PathVariable(value = "id") Long empId) {
        return empLeaveService.findAll(empId);
    }

    @PostMapping("/save_emp_leave")
    public ResponseEntity<?> saveEmpLeave(@Valid @RequestBody trn_emp_leave emp_leave) {
        try {
            trn_emp_leave empLeaveToUpdate = empLeaveService.save(emp_leave);
            return ResponseEntity.ok(new MessageResponse(empLeaveToUpdate.getReturnMessage().getCode(),
                    empLeaveToUpdate.getReturnMessage().getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.ok(new MessageResponse(CodeConstants.FAILURE.getID(), ex.getMessage()));
        }
    }

}





