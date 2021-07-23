package com.payroll.payrollWebService.service.PTAXMonthly;

import com.payroll.payrollWebService.models.common.CodeConstants;
import com.payroll.payrollWebService.models.payroll.trn_ptax_monthly;
import com.payroll.payrollWebService.models.payroll.PTaxMonthlyIdentity;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import com.payroll.payrollWebService.repository.payroll.PTAXMonthlyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
class PTAXMonthlyServiceDAL extends PTAXMonthlyServiceImpl {

    @Autowired
    private PTAXMonthlyRepository ptaxMonthlyRep;

    @PersistenceContext
    private EntityManager em;

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
    public List<trn_ptax_monthly> findAll(Long month,Long year)
    {
        MessageResponse resp=new MessageResponse();
        try
        {
            StoredProcedureQuery ptaxMonthly =
                    em.createNamedStoredProcedureQuery("ManagePTAXMonthly")
                            .registerStoredProcedureParameter("p_month", Integer.class, ParameterMode.IN)
                            .setParameter("p_month", month.intValue())
                            .registerStoredProcedureParameter("p_year", Integer.class, ParameterMode.IN)
                            .setParameter("p_year", year.intValue())
                            .registerStoredProcedureParameter("p_return_message", String.class, ParameterMode.OUT);
            ptaxMonthly.execute();
            String retMessage= (String)ptaxMonthly.getOutputParameterValue("p_return_message");

            /*To Do */
            List<trn_ptax_monthly> lstPTAXMonthly=new ArrayList<>();
            trn_ptax_monthly objPTAXMonthly=new trn_ptax_monthly();

            lstPTAXMonthly = ((List<trn_ptax_monthly>) ptaxMonthlyRep.findAll()).stream()
                    .filter(c -> c.getPtaxMonthlyIdentity().getMonth() == month.intValue()
                            && c.getPtaxMonthlyIdentity().getYear() == year.intValue())
                    .collect(Collectors.toList());
            return lstPTAXMonthly;
        }catch(Exception ex){
            return null;
        }
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
