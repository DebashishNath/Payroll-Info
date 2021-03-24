package com.payroll.payrollWebService.controllers.payroll;

import com.payroll.payrollWebService.models.common.CodeConstants;
import com.payroll.payrollWebService.models.payroll.mst_designation;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import com.payroll.payrollWebService.service.Designation.DesignationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class DesignationController {

    @Autowired
    private DesignationService designationService;

    @GetMapping("/designations")
    public List<mst_designation> listDesignation(){
        return designationService.findAll();
    }

    @GetMapping("/designation/{id}")
    public Optional<mst_designation> getOneDesignation(@PathVariable(value = "id") Long id){
        return designationService.findById(id);
    }

    @PostMapping("/newdesignation")
    public ResponseEntity<?> addDesignation(@Valid @RequestBody mst_designation designation) {
        try
        {
            mst_designation designationToAdd=designationService.save(designation);
            return ResponseEntity.ok(new MessageResponse(designationToAdd.getReturnMessage().getCode(),
                    designationToAdd.getReturnMessage().getMessage()));
        }catch(Exception ex){
           return ResponseEntity.ok(new MessageResponse(CodeConstants.FAILURE.getID(),ex.getMessage()));
        }
    }

    @PostMapping("/modifydesignation")
    public ResponseEntity<?> modifyDesignation(@Valid @RequestBody mst_designation designation) {
        try
        {
            mst_designation designationToModify=designationService.modify(designation);
            return ResponseEntity.ok(new MessageResponse(designationToModify.getReturnMessage().getCode(),
                    designationToModify.getReturnMessage().getMessage()));
        }catch(Exception ex){
            return ResponseEntity.ok(new MessageResponse(CodeConstants.FAILURE.getID(),
                                                    ex.getMessage()));
        }
    }

    @PostMapping("/deletedesignation/{id}")
    public ResponseEntity<?> deleteDesignation(@PathVariable(value = "id") Long id)
    {
        try
        {
            MessageResponse msgResp=designationService.removeOne(id);
            return ResponseEntity.ok(msgResp);
        }catch(Exception ex){
            return ResponseEntity.ok(new MessageResponse(CodeConstants.FAILURE.getID(),
                ex.getMessage()));
        }
    }
}



