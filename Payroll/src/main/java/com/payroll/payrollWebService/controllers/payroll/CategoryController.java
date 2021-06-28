package com.payroll.payrollWebService.controllers.payroll;

import com.payroll.payrollWebService.models.common.CodeConstants;
import com.payroll.payrollWebService.models.payroll.mst_category;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import com.payroll.payrollWebService.service.Category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/catagories")
    public List<mst_category> listCategory(){
        return categoryService.findAll();
    }

    @GetMapping("/category/{id}")
    public Optional<mst_category> getOneCategory(@PathVariable(value = "id") Long id){
        return categoryService.findById(id);
    }

    @PostMapping("/savecategory")
    public ResponseEntity<?> saveCategory(@Valid @RequestBody mst_category category) {
        try
        {
            mst_category categoryToAdd= categoryService.save(category);
            return ResponseEntity.ok(new MessageResponse(categoryToAdd.getReturnMessage().getCode(),
                    categoryToAdd.getReturnMessage().getMessage()));
        }catch(Exception ex){
            return ResponseEntity.ok(new MessageResponse(CodeConstants.FAILURE.getID(),ex.getMessage()));
        }
    }

    @PostMapping("/modifycategory")
    public ResponseEntity<?> modifyCategory(@Valid @RequestBody mst_category category) {
        try
        {
            mst_category categoryToModify=categoryService.modify(category);
            return ResponseEntity.ok(new MessageResponse(categoryToModify.getReturnMessage().getCode(),
                    categoryToModify.getReturnMessage().getMessage()));
        }catch(Exception ex){
            return ResponseEntity.ok(new MessageResponse(CodeConstants.FAILURE.getID(),
                    ex.getMessage()));
        }
    }

    @PostMapping("/deletecategory/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable(value = "id") Long id)
    {
        try
        {
            MessageResponse msgResp=categoryService.removeOne(id);
            return ResponseEntity.ok(msgResp);
        }catch(Exception ex){
            return ResponseEntity.ok(new MessageResponse(CodeConstants.FAILURE.getID(),
                    ex.getMessage()));
        }
    }
}




