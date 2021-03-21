package com.payroll.payrollWebService.service.Category;

import com.payroll.payrollWebService.models.common.CodeConstants;
import com.payroll.payrollWebService.models.payroll.mst_category;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import com.payroll.payrollWebService.repository.payroll.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class CategoryServiceDAL extends CategoryServiceImpl {

    @Autowired
    private CategoryRepository designationRepository;

    public CategoryServiceDAL() {}

    @Override
    public mst_category save(mst_category category)
    {
        MessageResponse msgResp =new MessageResponse();
        try{
            mst_category categoryToAdd = designationRepository.save(category);
            msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(),
                    "New Category details added successfully!");
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

//    @Override
//    public mst_designation modify(mst_designation designation)
//    {
//        MessageResponse msgResp = new MessageResponse();
//        try
//        {
//            mst_designation designationToModify = designationRepository.save(designation);
//            msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(), "Designation details modified successfully!");
//            designationToModify.setReturnMessage(msgResp);
//            return designationToModify;
//        }catch(Exception ex){
//            System.out.println(ex.getMessage());
//            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),"Failed to modify designation");
//            designation.setReturnMessage(msgResp);
//            return designation;
//        }
//    }
//
//    @Override
//    public List<mst_designation> findAll() {
//        return (List<mst_designation>) designationRepository.findAll();
//    }
//
//    @Override
//    public Optional<mst_designation> findById(Long id) {return designationRepository.findById(id); }
//
//    @Override
//    public MessageResponse removeOne(Long id)
//    {
//        MessageResponse msgResp = new MessageResponse();
//        try
//        {
//            designationRepository.deleteById(id);
//            msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(), "Designation details deleted successfully!");
//            return msgResp;
//        }catch(Exception ex)
//        {
//            System.out.println(ex.getMessage());
//            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),"Failed to delete designation");
//            return msgResp;
//        }
//    }

}
