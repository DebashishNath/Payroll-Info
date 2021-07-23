package com.payroll.payrollWebService.service.Category;

import com.payroll.payrollWebService.models.common.CodeConstants;
import com.payroll.payrollWebService.models.common.ErrorHandling;
import com.payroll.payrollWebService.models.payroll.mst_category;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import com.payroll.payrollWebService.repository.payroll.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class CategoryServiceDAL extends CategoryServiceImpl {

    @Autowired
    private CategoryRepository categoryRep;

    public CategoryServiceDAL() {}

    @Override
    public mst_category save(mst_category category)
    {
        MessageResponse msgResp =new MessageResponse();
        try{
            mst_category categoryToAdd = categoryRep.save(category);
            msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(),
                    "Category details updated successfully!");
            categoryToAdd.setReturnMessage(msgResp);
            return categoryToAdd;
        }catch(Exception ex)
        {
            System.out.println("Error Is: " + ex.getMessage());
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),
                    "Failed to add new category details");
            category.setReturnMessage(msgResp);
            return category;
        }
    }

    @Override
    public mst_category modify(mst_category category)
    {
        MessageResponse msgResp = new MessageResponse();
        try
        {
            Optional<mst_category> isCategoryPresent= categoryRep.findById(category.getCategory_id());
            if(isCategoryPresent.isPresent())
            {
                mst_category categoryToModify = categoryRep.save(category);
                msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(),
                        "Category details modified successfully!");
                categoryToModify.setReturnMessage(msgResp);
                return categoryToModify;
            }
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),
                    "Category details not found to modify");
            category.setReturnMessage(msgResp);
            return category;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            category.setReturnMessage(ErrorHandling.GetErrorMessage(ex.getMessage()));
            return category;
        }
    }

    @Override
    public List<mst_category> findAll() {
        return (List<mst_category>) categoryRep.findAll();
    }

    @Override
    public Optional<mst_category> findById(Long id) {return categoryRep.findById(id); }

    @Override
    public MessageResponse removeOne(Long id)
    {
        MessageResponse msgResp = new MessageResponse();
        try
        {
            categoryRep.deleteById(id);
            msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(), "Category details deleted successfully!");
            return msgResp;
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),"Failed to delete category");
            return msgResp;
        }
    }

}
