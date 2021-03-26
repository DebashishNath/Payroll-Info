package com.payroll.payrollWebService.controllers.payroll;

import com.payroll.payrollWebService.models.common.CodeConstants;
import com.payroll.payrollWebService.models.payroll.mst_district;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import com.payroll.payrollWebService.service.District.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    @GetMapping("/districts")
    public List<mst_district> listDistrict(){
        return districtService.findAll();
    }

    @GetMapping("/district/{id}")
    public Optional<mst_district> getOneDistrict(@PathVariable(value = "id") Long id){
        return districtService.findById(id);
    }

    @PostMapping("/adddistrict")
    public ResponseEntity<?> AddDistrict(@Valid @RequestBody mst_district district) {
        try
        {
            mst_district districtToAdd = districtService.save(district);
            return ResponseEntity.ok(new MessageResponse(districtToAdd.getReturnMessage().getCode(),
                    districtToAdd.getReturnMessage().getMessage()));
        }catch(Exception ex){
            return ResponseEntity.ok(new MessageResponse(CodeConstants.FAILURE.getID(),ex.getMessage()));
        }
    }

    @PostMapping("/modifydistrict")
    public ResponseEntity<?> modifyCategory(@Valid @RequestBody mst_district district) {
        try
        {
            mst_district districtToModify=districtService.modify(district);
            return ResponseEntity.ok(new MessageResponse(districtToModify.getReturnMessage().getCode(),
                    districtToModify.getReturnMessage().getMessage()));
        }catch(Exception ex){
            return ResponseEntity.ok(new MessageResponse(CodeConstants.FAILURE.getID(),
                    ex.getMessage()));
        }
    }

    @PostMapping("/deletedistrict/{id}")
    public ResponseEntity<?> deleteDistrict(@PathVariable(value = "id") Long id)
    {
        try
        {
            MessageResponse msgResp=districtService.removeOne(id);
            return ResponseEntity.ok(msgResp);
        }catch(Exception ex){
            return ResponseEntity.ok(new MessageResponse(CodeConstants.FAILURE.getID(),
                    ex.getMessage()));
        }
    }
}




