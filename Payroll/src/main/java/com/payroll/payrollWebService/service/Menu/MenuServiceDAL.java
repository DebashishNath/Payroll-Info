package com.payroll.payrollWebService.service.Menu;

import com.payroll.payrollWebService.models.common.CodeConstants;
import com.payroll.payrollWebService.models.payroll.mst_menu;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import com.payroll.payrollWebService.repository.payroll.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class MenuServiceDAL extends MenuServiceImpl {

    @Autowired
    private MenuRepository menuRep;

    public MenuServiceDAL() {}

    @Override
    public mst_menu save(mst_menu menu)
    {
        MessageResponse msgResp =new MessageResponse();
        try{
            mst_menu menuToUpdate = menuRep.save(menu);
            msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(),
                    "Menu details updated successfully!");
            menuToUpdate.setReturnMessage(msgResp);
            return menuToUpdate;
        }catch(Exception ex)
        {
            System.out.println("Error Is: " + ex.getMessage());
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),
                    "Failed to update menu details");
            menu.setReturnMessage(msgResp);
            return menu;
        }
    }

    @Override
    public List<mst_menu> findAll() {
        return (List<mst_menu>) menuRep.findAll();
    }

    @Override
    public Optional<mst_menu> findById(Long id) {return menuRep.findById(id); }

    @Override
    public MessageResponse removeOne(Long id)
    {
        MessageResponse msgResp = new MessageResponse();
        try
        {
            menuRep.deleteById(id);
            msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(), "Menu details deleted successfully!");
            return msgResp;
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),"Failed to delete category");
            return msgResp;
        }
    }

}
