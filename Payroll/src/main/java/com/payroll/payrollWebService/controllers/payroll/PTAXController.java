package com.payroll.payrollWebService.controllers.payroll;

import com.payroll.payrollWebService.models.common.CodeConstants;
import com.payroll.payrollWebService.models.payroll.trn_ptax_slab;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import com.payroll.payrollWebService.service.PTAXSlab.PTAXSlabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class PTAXController {

    @Autowired
    private PTAXSlabService ptaxSlabService;

    @GetMapping("/PTAXSlabs/{finYearId}")
    public List<trn_ptax_slab> listPTAXSlabs(@PathVariable(value = "finYearId") Long finYearId){
        return ptaxSlabService.findAll(finYearId);
    }

    @GetMapping("/ptaxslab/{id}")
    public Optional<trn_ptax_slab> getOneCategory(@PathVariable(value = "id") Long id){
        return ptaxSlabService.findById(id);
    }

    @PostMapping("/saveptaxslab")
    public ResponseEntity<?> saveCategory(@Valid @RequestBody trn_ptax_slab ptaxslab) {
        try
        {
            trn_ptax_slab ptaxToSave= ptaxSlabService.save(ptaxslab);
            return ResponseEntity.ok(new MessageResponse(ptaxToSave.getReturnMessage().getCode(),
                    ptaxToSave.getReturnMessage().getMessage()));
        }catch(Exception ex){
            return ResponseEntity.ok(new MessageResponse(CodeConstants.FAILURE.getID(),ex.getMessage()));
        }
    }

    @PostMapping("/deleteptaxslab/{id}")
    public ResponseEntity<?> deletePTAXSlab(@PathVariable(value = "id") Long id)
    {
        try
        {
            MessageResponse msgResp=ptaxSlabService.removeOne(id);
            return ResponseEntity.ok(msgResp);
        }catch(Exception ex){
            return ResponseEntity.ok(new MessageResponse(CodeConstants.FAILURE.getID(),
                    ex.getMessage()));
        }
    }
}




