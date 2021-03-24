package com.payroll.payrollWebService.controllers.payroll;

import com.payroll.payrollWebService.models.common.CodeConstants;
import com.payroll.payrollWebService.models.payroll.mst_department;
import com.payroll.payrollWebService.models.payroll.mst_designation;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import com.payroll.payrollWebService.service.Department.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class DepartmentController {

    @Autowired
    private DepartmentService deptService;

    @GetMapping("/departments")
    public List<mst_department> listDepartment(){
        return deptService.findAll();
    }

    @GetMapping("/department/{id}")
    public Optional<mst_department> getOneDepartment(@PathVariable(value = "id") Long id){
        return deptService.findById(id);
    }

    @PostMapping("/adddepartment")
    public ResponseEntity<?> AddEmpSalStruct(@Valid @RequestBody mst_department dept) {
        try
        {
            mst_department deptToAdd= deptService.save(dept);
            return ResponseEntity.ok(new MessageResponse(deptToAdd.getReturnMessage().getCode(),
                    deptToAdd.getReturnMessage().getMessage()));
        }catch(Exception ex){
            return ResponseEntity.ok(new MessageResponse(CodeConstants.FAILURE.getID(),ex.getMessage()));
        }
    }

    @PostMapping("/modifydepartment")
    public ResponseEntity<?> modifyDepartment(@Valid @RequestBody mst_department dept) {
        try
        {
            mst_department deptToModify = deptService.modify(dept);
            return ResponseEntity.ok(new MessageResponse(deptToModify.getReturnMessage().getCode(),
                    deptToModify.getReturnMessage().getMessage()));
        }catch(Exception ex){
            return ResponseEntity.ok(new MessageResponse(CodeConstants.FAILURE.getID(),
                    ex.getMessage()));
        }
    }

    @PostMapping("/deletedepartment/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable(value = "id") Long id)
    {
        try
        {
            MessageResponse msgResp= deptService.removeOne(id);
            return ResponseEntity.ok(msgResp);
        }catch(Exception ex){
            return ResponseEntity.ok(new MessageResponse(CodeConstants.FAILURE.getID(),
                    ex.getMessage()));
        }
    }
}




