package com.payroll.payrollWebService.controllers.payroll;

import com.payroll.payrollWebService.models.common.CodeConstants;
import com.payroll.payrollWebService.models.payroll.mst_location;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import com.payroll.payrollWebService.service.Location.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping("/locations")
    public List<mst_location> listLocation(){
        return locationService.findAll();
    }

    @GetMapping("/location/{id}")
    public Optional<mst_location> getOneDesignation(@PathVariable(value = "id") Long id){
        return locationService.findById(id);
    }

    @PostMapping("/newlocation")
    public ResponseEntity<?> addLocation(@Valid @RequestBody mst_location location) {
        try
        {
            mst_location locationToAdd =locationService.save(location);
            return ResponseEntity.ok(new MessageResponse(locationToAdd.getReturnMessage().getCode(),
                    locationToAdd.getReturnMessage().getMessage()));
        }catch(Exception ex){
            return ResponseEntity.ok(new MessageResponse(CodeConstants.FAILURE.getID(),ex.getMessage()));
        }
    }

    @PostMapping("/modifylocation")
    public ResponseEntity<?> modifyLocation(@Valid @RequestBody mst_location location) {
        try
        {
            mst_location locationToModify=locationService.modify(location);
            return ResponseEntity.ok(new MessageResponse(locationToModify.getReturnMessage().getCode(),
                    locationToModify.getReturnMessage().getMessage()));
        }catch(Exception ex){
            return ResponseEntity.ok(new MessageResponse(CodeConstants.FAILURE.getID(),
                    ex.getMessage()));
        }
    }

    @PostMapping("/deletelocation/{id}")
    public ResponseEntity<?> deleteLocation(@PathVariable(value = "id") Long id)
    {
        try
        {
            MessageResponse msgResp=locationService.removeOne(id);
            return ResponseEntity.ok(msgResp);
        }catch(Exception ex){
            return ResponseEntity.ok(new MessageResponse(CodeConstants.FAILURE.getID(),
                    ex.getMessage()));
        }
    }
}



