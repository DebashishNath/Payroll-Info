package com.payroll.payrollWebService.controllers.payroll;

import com.payroll.payrollWebService.models.common.CodeConstants;
import com.payroll.payrollWebService.models.payroll.trn_ptax_monthly;
import com.payroll.payrollWebService.models.payroll.PTaxMonthlyIdentity;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import com.payroll.payrollWebService.service.PTAXMonthly.PTAXMonthlyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class PTAXMonthlyController {

    @Autowired
    private PTAXMonthlyService ptaxMonthlyService;

    @GetMapping("/ptaxmonthly/{month}/{year}")
    public List<trn_ptax_monthly> listPTAXMonthly(@PathVariable(value = "month") Long month,
                                               @PathVariable(value = "year") Long year){
        return ptaxMonthlyService.findAll(month,year);
    }

    @GetMapping("/ptaxmonthlybyid/{id}")
    public Optional<trn_ptax_monthly> getOneCategory(@PathVariable(value = "id") PTaxMonthlyIdentity id){
        return ptaxMonthlyService.findById(id);
    }

    @PostMapping("/saveptaxmonthly")
    public ResponseEntity<?> savePTAXMonthly(@Valid @RequestBody trn_ptax_monthly ptaxmonthly) {
        try
        {
            trn_ptax_monthly ptaxToSave= ptaxMonthlyService.save(ptaxmonthly);
            return ResponseEntity.ok(new MessageResponse(ptaxToSave.getReturnMessage().getCode(),
                    ptaxToSave.getReturnMessage().getMessage()));
        }catch(Exception ex){
            return ResponseEntity.ok(new MessageResponse(CodeConstants.FAILURE.getID(),ex.getMessage()));
        }
    }

    @PostMapping("/deleteptaxmonthly/{id}")
    public ResponseEntity<?> deletePTAXSlab(@PathVariable(value = "id") PTaxMonthlyIdentity id)
    {
        try
        {
            MessageResponse msgResp=ptaxMonthlyService.removeOne(id);
            return ResponseEntity.ok(msgResp);
        }catch(Exception ex){
            return ResponseEntity.ok(new MessageResponse(CodeConstants.FAILURE.getID(),
                    ex.getMessage()));
        }
    }
}




