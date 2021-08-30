package com.payroll.payrollWebService.service.MenuItem;

import com.payroll.payrollWebService.models.common.CodeConstants;
import com.payroll.payrollWebService.models.payroll.mst_menu_item;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import com.payroll.payrollWebService.repository.payroll.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class MenuItemServiceDAL extends MenuItemServiceImpl {

    @Autowired
    private MenuItemRepository menuItemRep;

    public MenuItemServiceDAL() {}

    @Override
    public mst_menu_item save(mst_menu_item menuItem)
    {
        MessageResponse msgResp =new MessageResponse();
        try{
            mst_menu_item menuItemToUpdate = menuItemRep.save(menuItem);
            msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(),
                    "Menu Item Details updated successfully!");
            menuItemToUpdate.setReturnMessage(msgResp);
            return menuItemToUpdate;
        }catch(Exception ex)
        {
            System.out.println("Error Is: " + ex.getMessage());
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),
                    "Failed to update menu item details");
            menuItem.setReturnMessage(msgResp);
            return menuItem;
        }
    }

    @Override
    public List<mst_menu_item> findAll() {
        return (List<mst_menu_item>) menuItemRep.findAll();
    }

    @Override
    public Optional<mst_menu_item> findById(Long id) {return menuItemRep.findById(id); }

    @Override
    public MessageResponse removeOne(Long id)
    {
        MessageResponse msgResp = new MessageResponse();
        try
        {
            menuItemRep.deleteById(id);
            msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(), "Menu Item Details deleted successfully!");
            return msgResp;
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),"Failed to delete Menu Item Details");
            return msgResp;
        }
    }

}
