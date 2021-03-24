package com.payroll.payrollWebService.service.Department;

import com.payroll.payrollWebService.models.common.CodeConstants;
import com.payroll.payrollWebService.models.common.ErrorHandling;
import com.payroll.payrollWebService.models.payroll.mst_department;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import com.payroll.payrollWebService.repository.payroll.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class DepartmentServiceDAL extends DepartmentServiceImpl {

    @Autowired
    private DepartmentRepository deptRep;

    public DepartmentServiceDAL() {}

    @Override
    public mst_department save(mst_department department)
    {
        MessageResponse msgResp =new MessageResponse();
        try
        {

            mst_department deptToAdd = deptRep.save(department);
            msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(),
                    "New Department details added successfully!");
            deptToAdd.setReturnMessage(msgResp);
            return deptToAdd;
        }catch(Exception ex)
        {
            System.out.println("Error Is: " + ex.getMessage());
            department.setReturnMessage(ErrorHandling.GetErrorMessage(ex.getMessage()));
            return department;
        }
    }

    @Override
    public mst_department modify(mst_department department)
    {
        MessageResponse msgResp = new MessageResponse();
        try
        {
            Optional<mst_department> isDesigPresent= deptRep.findById(department.getDepartment_id());
            if(isDesigPresent.isPresent())
            {
                mst_department deptToModify = deptRep.save(department);
                msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(),
                        "Department details modified successfully!");
                deptToModify.setReturnMessage(msgResp);
                return deptToModify;
            }
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),
                    "Department details not found to modify");
            department.setReturnMessage(msgResp);
            return department;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            department.setReturnMessage(ErrorHandling.GetErrorMessage(ex.getMessage()));
            return department;
        }
    }

    @Override
    public List<mst_department> findAll() {
        return (List<mst_department>) deptRep.findAll();
    }

    @Override
    public Optional<mst_department> findById(Long id) {return deptRep.findById(id); }

    @Override
    public MessageResponse removeOne(Long id)
    {
        MessageResponse msgResp = new MessageResponse();
        try
        {
            deptRep.deleteById(id);
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
