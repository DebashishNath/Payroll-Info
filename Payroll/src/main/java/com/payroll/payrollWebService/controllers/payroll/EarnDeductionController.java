package com.payroll.payrollWebService.controllers.payroll;

import com.payroll.payrollWebService.models.common.CodeConstants;
import com.payroll.payrollWebService.models.payroll.mst_earn_ded_components;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import com.payroll.payrollWebService.service.EarnDeduction.EarnDeductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class EarnDeductionController {

    @Autowired
    private EarnDeductionService earnDeductionService;

    @GetMapping("/earn_deductions")
    public List<mst_earn_ded_components> listEarnDeduction(){
        return earnDeductionService.findAll();
    }

    @GetMapping("/earn_deduction/{id}")
    public Optional<mst_earn_ded_components> getOneEarnDeduction(@PathVariable(value = "id") Long id){
        return earnDeductionService.findById(id);
    }

    @PostMapping("/add_earn_deduction")
    public ResponseEntity<?> AddEarnDeduction(@Valid @RequestBody mst_earn_ded_components earnDedComponents) {
        try
        {
            mst_earn_ded_components earn_ded_componentsToAdd= earnDeductionService.save(earnDedComponents);
            return ResponseEntity.ok(new MessageResponse(earn_ded_componentsToAdd.getReturnMessage().getCode(),
                    earn_ded_componentsToAdd.getReturnMessage().getMessage()));
        }catch(Exception ex){
            return ResponseEntity.ok(new MessageResponse(CodeConstants.FAILURE.getID(),ex.getMessage()));
        }
    }

    @PostMapping("/modify_earn_deduction")
    public ResponseEntity<?> modifyEarnDeduction(@Valid @RequestBody mst_earn_ded_components earnDedComponents) {
        try
        {
            mst_earn_ded_components earn_ded_componentsToModify=earnDeductionService.modify(earnDedComponents);
            return ResponseEntity.ok(new MessageResponse(earn_ded_componentsToModify.getReturnMessage().getCode(),
                    earn_ded_componentsToModify.getReturnMessage().getMessage()));
        }catch(Exception ex){
            return ResponseEntity.ok(new MessageResponse(CodeConstants.FAILURE.getID(),
                    ex.getMessage()));
        }
    }

    @PostMapping("/delete_earn_ded_components/{id}")
    public ResponseEntity<?> deleteEarnDeduction(@PathVariable(value = "id") Long id)
    {
        try
        {
            MessageResponse msgResp=earnDeductionService.removeOne(id);
            return ResponseEntity.ok(msgResp);
        }catch(Exception ex){
            return ResponseEntity.ok(new MessageResponse(CodeConstants.FAILURE.getID(),
                    ex.getMessage()));
        }
    }
}




