package com.payroll.payrollWebService.service.Company;

import com.payroll.payrollWebService.models.common.CodeConstants;
import com.payroll.payrollWebService.models.payroll.mst_company;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import com.payroll.payrollWebService.repository.payroll.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class CompanyServiceDAL extends CompanyServiceImpl {

    @Autowired
    private CompanyRepository companyRep;

    public CompanyServiceDAL() {}

    @Override
    public mst_company save(mst_company company)
    {
        MessageResponse msgResp =new MessageResponse();
        try{
            mst_company companyToUpdate = companyRep.save(company);
            msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(),
                    "Company details updated successfully!");
            companyToUpdate.setReturnMessage(msgResp);
            return companyToUpdate;
        }catch(Exception ex)
        {
            System.out.println("Error Is: " + ex.getMessage());
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),
                    "Failed to update company details");
            company.setReturnMessage(msgResp);
            return company;
        }
    }

    @Override
    public List<mst_company> findAll() {
        return (List<mst_company>) companyRep.findAll();
    }

    @Override
    public Optional<mst_company> findById(Long id) {return companyRep.findById(id); }

    @Override
    public MessageResponse removeOne(Long id)
    {
        MessageResponse msgResp = new MessageResponse();
        try
        {
            companyRep.deleteById(id);
            msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(), "Company details deleted successfully!");
            return msgResp;
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),"Failed to delete category");
            return msgResp;
        }
    }

}
