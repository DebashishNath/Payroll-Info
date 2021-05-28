package com.payroll.payrollWebService.service.District;

import com.payroll.payrollWebService.models.common.CodeConstants;
import com.payroll.payrollWebService.models.common.ErrorHandling;
import com.payroll.payrollWebService.models.payroll.mst_district;
import com.payroll.payrollWebService.models.payroll.mst_state;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import com.payroll.payrollWebService.repository.payroll.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
class DistrictServiceDAL extends DistrictServiceImpl {

    @Autowired
    private DistrictRepository districtRep;

    public DistrictServiceDAL() {}

    @Override
    public mst_district save(mst_district district)
    {
        MessageResponse msgResp =new MessageResponse();
        try
        {

            mst_district designationToAdd = districtRep.save(district);
            msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(),
                    "New District details added successfully!");
            designationToAdd.setReturnMessage(msgResp);
            return designationToAdd;
        }catch(Exception ex)
        {
            System.out.println("Error Is: " + ex.getMessage());
            district.setReturnMessage(ErrorHandling.GetErrorMessage(ex.getMessage()));
            return district;
        }
    }

    @Override
    public mst_district modify(mst_district district)
    {
        MessageResponse msgResp = new MessageResponse();
        try
        {
            Optional<mst_district> isDeptPresent = districtRep.findById(district.getDistrict_id());
            if(isDeptPresent.isPresent())
            {
                mst_district districtToModify = districtRep.save(district);
                msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(),
                        "District details modified successfully!");
                districtToModify.setReturnMessage(msgResp);
                return districtToModify;
            }
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),
                    "District details not found to modify");
            district.setReturnMessage(msgResp);
            return district;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            district.setReturnMessage(ErrorHandling.GetErrorMessage(ex.getMessage()));
            return district;
        }
    }

    @Override
    public List<mst_district> findAll(Long stateid) {
        List<mst_district> lstDistrict=(ArrayList<mst_district>) districtRep.findAll();
        Stream<mst_district> streamDistrict=lstDistrict.stream()
                .filter(c->c.getState().getState_id().equals(stateid));
        return streamDistrict.collect(Collectors.toList());
    }

    @Override
    public Optional<mst_district> findById(Long id) {return districtRep.findById(id); }

    @Override
    public MessageResponse removeOne(Long id)
    {
        MessageResponse msgResp = new MessageResponse();
        try
        {
            districtRep.deleteById(id);
            msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(), "District details deleted successfully!");
            return msgResp;
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),"Failed to delete district");
            return msgResp;
        }
    }

}
