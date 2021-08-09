package com.payroll.payrollWebService.controllers.payroll;

import com.payroll.payrollWebService.models.common.CodeConstants;
import com.payroll.payrollWebService.models.payroll.mst_company;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import com.payroll.payrollWebService.service.Company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/companies")
    public List<mst_company> listCompany(){
        return companyService.findAll();
    }

    @GetMapping("/company/{id}")
    public Optional<mst_company> getOneCompany(@PathVariable(value = "id") Long id){
        return companyService.findById(id);
    }

    @PostMapping("/savecompany")
    public ResponseEntity<?> saveCompany(@Valid @RequestBody mst_company company) {
        try
        {
            mst_company companyToUpdate= companyService.save(company);
            return ResponseEntity.ok(new MessageResponse(companyToUpdate.getReturnMessage().getCode(),
                    companyToUpdate.getReturnMessage().getMessage()));
        }catch(Exception ex){
            return ResponseEntity.ok(new MessageResponse(CodeConstants.FAILURE.getID(),ex.getMessage()));
        }
    }

    @PostMapping("/deletecompany/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable(value = "id") Long id)
    {
        try
        {
            MessageResponse msgResp=companyService.removeOne(id);
            return ResponseEntity.ok(msgResp);
        }catch(Exception ex){
            return ResponseEntity.ok(new MessageResponse(CodeConstants.FAILURE.getID(),
                    ex.getMessage()));
        }
    }
}




