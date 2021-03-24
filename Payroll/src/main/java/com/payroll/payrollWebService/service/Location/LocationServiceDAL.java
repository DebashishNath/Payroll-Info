package com.payroll.payrollWebService.service.Location;

import com.payroll.payrollWebService.models.common.CodeConstants;
import com.payroll.payrollWebService.models.common.ErrorHandling;
import com.payroll.payrollWebService.models.payroll.mst_location;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import com.payroll.payrollWebService.repository.payroll.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceDAL extends LocationServiceImpl {

    @Autowired
    private LocationRepository locationRep;

    public LocationServiceDAL() {}

    @Override
    public mst_location save(mst_location location)
    {
        MessageResponse msgResp =new MessageResponse();
        try
        {

            mst_location locationToAdd = locationRep.save(location);
            msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(),
                    "New Location details added successfully!");
            locationToAdd.setReturnMessage(msgResp);
            return locationToAdd;
        }catch(Exception ex)
        {
            System.out.println("Error Is: " + ex.getMessage());
            location.setReturnMessage(ErrorHandling.GetErrorMessage(ex.getMessage()));
            return location;
        }
    }

    @Override
    public mst_location modify(mst_location location)
    {
        MessageResponse msgResp = new MessageResponse();
        try
        {
            Optional<mst_location> isLocationPresent= locationRep.findById(location.getLocation_id());
            if(isLocationPresent.isPresent())
            {
                mst_location locationToModify = locationRep.save(location);
                msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(),
                        "Location details modified successfully!");
                locationToModify.setReturnMessage(msgResp);
                return locationToModify;
            }
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),
                    "Location details not found to modify");
            location.setReturnMessage(msgResp);
            return location;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            location.setReturnMessage(ErrorHandling.GetErrorMessage(ex.getMessage()));
            return location;
        }
    }

    @Override
    public List<mst_location> findAll() {
        return (List<mst_location>) locationRep.findAll();
    }

    @Override
    public Optional<mst_location> findById(Long id) {return locationRep.findById(id); }

    @Override
    public MessageResponse removeOne(Long id)
    {
        MessageResponse msgResp = new MessageResponse();
        try
        {
            locationRep.deleteById(id);
            msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(), "Location details deleted successfully!");
            return msgResp;
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),"Failed to delete location details");
            return msgResp;
        }
    }

}
