package com.payroll.payrollWebService.service.Designation;

import com.payroll.payrollWebService.models.common.CodeConstants;
import com.payroll.payrollWebService.models.common.ErrorHandling;
import com.payroll.payrollWebService.models.payroll.mst_designation;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import com.payroll.payrollWebService.repository.payroll.DesignationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class DesignationServiceDAL extends DesignationServiceImpl{

    @Autowired
    private DesignationRepository desigRep;

    public DesignationServiceDAL() {}

    @Override
    public mst_designation save(mst_designation designation)
    {
        MessageResponse msgResp =new MessageResponse();
        try
        {

            mst_designation designationToAdd = desigRep.save(designation);
            msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(),
                    "New Designation details added successfully!");
            designationToAdd.setReturnMessage(msgResp);
            return designationToAdd;
        }catch(Exception ex)
        {
            System.out.println("Error Is: " + ex.getMessage());
            designation.setReturnMessage(ErrorHandling.GetErrorMessage(ex.getMessage()));
            return designation;
        }
    }

    @Override
    public mst_designation modify(mst_designation designation)
    {
        MessageResponse msgResp = new MessageResponse();
        try
        {
            Optional<mst_designation> isDesigPresent= desigRep.findById(designation.getDesignation_id());
            if(isDesigPresent.isPresent())
            {
                mst_designation designationToModify = desigRep.save(designation);
                msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(),
                        "Designation details modified successfully!");
                designationToModify.setReturnMessage(msgResp);
                return designationToModify;
            }
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),
                    "Designation details not found to modify");
            designation.setReturnMessage(msgResp);
            return designation;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            designation.setReturnMessage(ErrorHandling.GetErrorMessage(ex.getMessage()));
            return designation;
        }
    }

    @Override
    public List<mst_designation> findAll() {
        return (List<mst_designation>) desigRep.findAll();
    }

    @Override
    public Optional<mst_designation> findById(Long id) {return desigRep.findById(id); }

    @Override
    public MessageResponse removeOne(Long id)
    {
        MessageResponse msgResp = new MessageResponse();
        try
        {
            desigRep.deleteById(id);
            msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(), "Designation details deleted successfully!");
            return msgResp;
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),"Failed to delete designation");
            return msgResp;
         }
    }

}
