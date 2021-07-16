package com.payroll.payrollWebService.service.EarnDeduction;

import com.payroll.payrollWebService.models.common.CodeConstants;
import com.payroll.payrollWebService.models.common.ErrorHandling;
import com.payroll.payrollWebService.models.payroll.mst_earn_ded_components;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import com.payroll.payrollWebService.repository.payroll.EarnDeductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class EarnDeductionDAL extends EarnDeductionImpl
{
    @Autowired
    private EarnDeductionRepository earnDedRep;

    public EarnDeductionDAL() {}

    @Override
    public mst_earn_ded_components update(mst_earn_ded_components earnDedComponents)
    {
        MessageResponse msgResp =new MessageResponse();
        try
        {

            mst_earn_ded_components earnDedToUpdate = earnDedRep.save(earnDedComponents);
            msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(),
                    "Earn Deduction details updated successfully!");
            earnDedToUpdate.setReturnMessage(msgResp);
            return earnDedToUpdate;
        }catch(Exception ex)
        {
            System.out.println("Error Is: " + ex.getMessage());
            earnDedComponents.setReturnMessage(ErrorHandling.GetErrorMessage(ex.getMessage()));
            return earnDedComponents;
        }
    }

    @Override
    public mst_earn_ded_components modify(mst_earn_ded_components earnDedComponents)
    {
        MessageResponse msgResp = new MessageResponse();
        try
        {
            Optional<mst_earn_ded_components> isEarnDedPresent = earnDedRep.findById(earnDedComponents.getEarn_ded_id());
            if(isEarnDedPresent.isPresent())
            {
                mst_earn_ded_components earnDedToModify = earnDedRep.save(earnDedComponents);
                msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(),
                        "Earn Deduction details modified successfully!");
                earnDedToModify.setReturnMessage(msgResp);
                return earnDedToModify;
            }
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),
                    "Earn Deduction details not found to modify");
            earnDedComponents.setReturnMessage(msgResp);
            return earnDedComponents;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            earnDedComponents.setReturnMessage(ErrorHandling.GetErrorMessage(ex.getMessage()));
            return earnDedComponents;
        }
    }

    @Override
    public List<mst_earn_ded_components> findAll() {
        /*return (List<mst_earn_ded_components>)earnDedRep.findAll(Sort.by("earn_ded_type").descending()
                .and(Sort.by("earn_ded_priority")));*/
        return (List<mst_earn_ded_components>)earnDedRep.findAll();
    }

    @Override
    public Optional<mst_earn_ded_components> findById(Long id) {
        return earnDedRep.findById(id);
    }

    @Override
    public MessageResponse removeOne(Long id)
    {
        MessageResponse msgResp = new MessageResponse();
        try
        {
            earnDedRep.deleteById(id);
            msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(), "Earn Deduction details deleted successfully!");
            return msgResp;
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),"Failed to delete Earn Deduction");
            return msgResp;
        }
    }
}
