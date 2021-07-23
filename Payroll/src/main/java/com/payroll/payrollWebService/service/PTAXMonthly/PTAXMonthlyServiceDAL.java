package com.payroll.payrollWebService.service.PTAXMonthly;

import com.payroll.payrollWebService.models.common.CodeConstants;
import com.payroll.payrollWebService.models.payroll.trn_ptax_monthly;
import com.payroll.payrollWebService.models.payroll.PTaxMonthlyIdentity;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import com.payroll.payrollWebService.repository.payroll.PTAXMonthlyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
class PTAXMonthlyServiceDAL extends PTAXMonthlyServiceImpl {

    @Autowired
    private PTAXMonthlyRepository ptaxMonthlyRep;

    public PTAXMonthlyServiceDAL() {}

    @Override
    public trn_ptax_monthly save(trn_ptax_monthly ptaxMonthly)
    {
        MessageResponse msgResp =new MessageResponse();
        try{
            trn_ptax_monthly ptaxMonthlyToSave = ptaxMonthlyRep.save(ptaxMonthly);
            msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(),
                    "PTAX Monthly details updated successfully!");
            ptaxMonthlyToSave.setReturnMessage(msgResp);
            return ptaxMonthlyToSave;
        }catch(Exception ex)
        {
            System.out.println("Error Is: " + ex.getMessage());
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),
                    "Failed to update PTAX Monthly details");
            ptaxMonthly.setReturnMessage(msgResp);
            return ptaxMonthly;
        }
    }

    @Override
    public List<trn_ptax_monthly> findAll(Long month,Long year) {

        return ((List<trn_ptax_monthly>) ptaxMonthlyRep.findAll()).stream()
                .filter(c->c.getPtaxMonthlyIdentity().getMonth()==month.intValue()
                        && c.getPtaxMonthlyIdentity().getYear()==year.intValue())
                .collect(Collectors.toList());
    }

    @Override
    public Optional<trn_ptax_monthly> findById(PTaxMonthlyIdentity ptaxMonIdentity)
    {
        return ptaxMonthlyRep.findById(ptaxMonIdentity);
    }

    @Override
    public MessageResponse removeOne(PTaxMonthlyIdentity pTaxMonthlyIdentity){
        MessageResponse msgResp = new MessageResponse();
        try
        {
            ptaxMonthlyRep.deleteById(pTaxMonthlyIdentity);
            msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(), "PTAX monthly details deleted successfully!");
            return msgResp;
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),"Failed to delete PTAX monthly");
            return msgResp;
        }
    }
}
