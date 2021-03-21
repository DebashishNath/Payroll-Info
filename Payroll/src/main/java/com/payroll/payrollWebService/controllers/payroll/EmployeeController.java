package com.payroll.payrollWebService.controllers.payroll;

import com.payroll.payrollWebService.models.common.CodeConstants;
import com.payroll.payrollWebService.models.payroll.mst_employee;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import com.payroll.payrollWebService.service.Employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<mst_employee> listState(){
        return employeeService.findAll();
    }

    @GetMapping("/employee/{id}")
    public Optional<mst_employee> getOne(@PathVariable(value = "id") Long id){
        return employeeService.findById(id);
    }

    @PostMapping("/newemployee")
    public ResponseEntity<?> addEmployee(@Valid @RequestBody mst_employee employee) {
        try
        {
            System.out.println(employee.getAadhar_no());
            mst_employee employeeToAdd=employeeService.save(employee);
            return ResponseEntity.ok(new MessageResponse(employeeToAdd.getReturnMessage().getCode(),
                    employeeToAdd.getReturnMessage().getMessage()));
        }catch(Exception ex){
            return ResponseEntity.ok(new MessageResponse(CodeConstants.FAILURE.getID(),ex.getMessage()));
        }
    }

    @PostMapping("/modifyemployee")
    public ResponseEntity<?> modifyemployee(@Valid @RequestBody mst_employee employee) {
        try
        {
            mst_employee employeeToModify=employeeService.modify(employee);
            return ResponseEntity.ok(new MessageResponse(employeeToModify.getReturnMessage().getCode(),
                    employeeToModify.getReturnMessage().getMessage()));
        }catch(Exception ex){
            return ResponseEntity.ok(new MessageResponse(CodeConstants.FAILURE.getID(),
                    ex.getMessage()));
        }
    }

    @PostMapping("/deleteemployee/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable(value = "id") Long id)
    {
        try
        {
            MessageResponse msgResp=employeeService.removeOne(id);
            return ResponseEntity.ok(msgResp);
        }catch(Exception ex){
            return ResponseEntity.ok(new MessageResponse(CodeConstants.FAILURE.getID(),
                    ex.getMessage()));
        }
    }
}




