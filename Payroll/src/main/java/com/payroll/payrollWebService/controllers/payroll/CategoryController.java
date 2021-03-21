package com.payroll.payrollWebService.controllers.payroll;

import com.payroll.payrollWebService.models.common.CodeConstants;
import com.payroll.payrollWebService.models.payroll.mst_category;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import com.payroll.payrollWebService.service.Category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/addcategory")
    public ResponseEntity<?> AddEmpSalStruct(@Valid @RequestBody mst_category category) {
        try
        {
            mst_category categoryToAdd= categoryService.save(category);
            return ResponseEntity.ok(new MessageResponse(categoryToAdd.getReturnMessage().getCode(),
                    categoryToAdd.getReturnMessage().getMessage()));
        }catch(Exception ex){
            return ResponseEntity.ok(new MessageResponse(CodeConstants.FAILURE.getID(),ex.getMessage()));
        }
    }
}




