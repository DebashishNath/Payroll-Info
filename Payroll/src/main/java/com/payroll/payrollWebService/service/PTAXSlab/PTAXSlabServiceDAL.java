package com.payroll.payrollWebService.service.PTAXSlab;

import com.payroll.payrollWebService.models.common.CodeConstants;
import com.payroll.payrollWebService.models.common.ErrorHandling;
import com.payroll.payrollWebService.models.payroll.trn_ptax_slab;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import com.payroll.payrollWebService.repository.payroll.PTAXSlabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
class PTAXSlabServiceDAL extends PTAXSlabServiceImpl {

    @Autowired
    private PTAXSlabRepository ptaxSlabRep;

    public PTAXSlabServiceDAL() {}

    @Override
    public trn_ptax_slab save(trn_ptax_slab ptaxSlab)
    {
        MessageResponse msgResp =new MessageResponse();
        try{
            trn_ptax_slab ptaxSlabToSave = ptaxSlabRep.save(ptaxSlab);
            msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(),
                    "PTAX Slab details updated successfully!");
            ptaxSlabToSave.setReturnMessage(msgResp);
            return ptaxSlabToSave;
        }catch(Exception ex)
        {
            System.out.println("Error Is: " + ex.getMessage());
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),
                    "Failed to add update PTAX Slab details");
            ptaxSlab.setReturnMessage(msgResp);
            return ptaxSlab;
        }
    }

    @Override
    public List<trn_ptax_slab> findAll(Long finYearId) {

        return ((List<trn_ptax_slab>) ptaxSlabRep.findAll()).stream()
                .filter(c->c.getFinYear().getFin_year_id() == finYearId)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<trn_ptax_slab> findById(Long id) {return ptaxSlabRep.findById(id); }

    @Override
    public MessageResponse removeOne(Long id)
    {
        MessageResponse msgResp = new MessageResponse();
        try
        {
            ptaxSlabRep.deleteById(id);
            msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(), "PTAX Slab details deleted successfully!");
            return msgResp;
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),"Failed to delete PTAX Slab");
            return msgResp;
        }
    }

}
