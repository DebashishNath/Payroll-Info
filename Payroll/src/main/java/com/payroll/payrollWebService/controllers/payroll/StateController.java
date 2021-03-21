package com.payroll.payrollWebService.controllers.payroll;

import com.payroll.payrollWebService.models.common.CodeConstants;
import com.payroll.payrollWebService.models.payroll.mst_state;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import com.payroll.payrollWebService.service.State.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class StateController {

    @Autowired
    private StateService stateService;

    @GetMapping("/states")
    public List<mst_state> listState(){
        return stateService.findAll();
    }

    @GetMapping("/state/{id}")
    public Optional<mst_state> getOne(@PathVariable(value = "id") Long id){
        return stateService.findById(id);
    }

    @PostMapping("/newstate")
    public ResponseEntity<?> addState(@Valid @RequestBody mst_state state) {
        try
        {
            mst_state stateToAdd=stateService.save(state);
            return ResponseEntity.ok(new MessageResponse(stateToAdd.getReturnMessage().getCode(),
                    stateToAdd.getReturnMessage().getMessage()));
        }catch(Exception ex){
            return ResponseEntity.ok(new MessageResponse(CodeConstants.FAILURE.getID(),ex.getMessage()));
        }
    }

    @PostMapping("/modifystate")
    public ResponseEntity<?> modifystate(@Valid @RequestBody mst_state state) {
        try
        {
            mst_state stateToModify=stateService.modify(state);
            return ResponseEntity.ok(new MessageResponse(stateToModify.getReturnMessage().getCode(),
                    stateToModify.getReturnMessage().getMessage()));
        }catch(Exception ex){
            return ResponseEntity.ok(new MessageResponse(CodeConstants.FAILURE.getID(),
                    ex.getMessage()));
        }
    }

    @PostMapping("/deletestate/{id}")
    public ResponseEntity<?> deleteState(@PathVariable(value = "id") Long id)
    {
        try
        {
            MessageResponse msgResp=stateService.removeOne(id);
            return ResponseEntity.ok(msgResp);
        }catch(Exception ex){
            return ResponseEntity.ok(new MessageResponse(CodeConstants.FAILURE.getID(),
                    ex.getMessage()));
        }
    }
}




