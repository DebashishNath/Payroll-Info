package com.payroll.payrollWebService.service.Employee;

import com.payroll.payrollWebService.models.common.CodeConstants;
import com.payroll.payrollWebService.models.common.ErrorHandling;
import com.payroll.payrollWebService.models.payroll.mst_employee;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import com.payroll.payrollWebService.repository.payroll.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class EmployeeServiceDAL extends EmployeeServiceImpl{

    @Autowired
    private EmployeeRepository empRep;

    public EmployeeServiceDAL() {}

    @Override
    public mst_employee save(mst_employee employee)
    {
        MessageResponse msgResp =new MessageResponse();
        try{
            mst_employee employeeToAdd = empRep.save(employee);
            msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(),
                    "New Employee details added successfully!");
            employeeToAdd.setReturnMessage(msgResp);
            return employeeToAdd;
        }catch(Exception ex)
        {
            System.out.println("Error Is: " + ex.getMessage() + "," + ex.getStackTrace());
            employee.setReturnMessage(ErrorHandling.GetErrorMessage(ex.getMessage()));
            return employee;
        }
    }

    /* To Update Only Employee Personal Information */
    @Override
    public mst_employee modify(mst_employee employee)
    {
        MessageResponse msgResp = new MessageResponse();
        try
        {
            Optional<mst_employee> isEmpPresent = empRep.findById(employee.getEmp_id());
            if(isEmpPresent.isPresent())
            {
                mst_employee employeeToModify=isEmpPresent.get();

                employee.setEmp_doj(employeeToModify.getEmp_doj());
                employee.setAadhar_no(employeeToModify.getAadhar_no());
                employee.setPan_no(employeeToModify.getPan_no());
                employee.setPf_no(employeeToModify.getPf_no());
                employee.setEsi_no(employeeToModify.getEsi_no());
                employee.setCategory(employeeToModify.getCategory());
                employee.setDepartment(employeeToModify.getDepartment());
                employee.setDesignation(employeeToModify.getDesignation());

                employeeToModify = empRep.save(employee);
                msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(),
                        "Employee personal details modified successfully!");
                employeeToModify.setReturnMessage(msgResp);
                return employeeToModify;
            }
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),
                    "Employee details not found to modify");
            employee.setReturnMessage(msgResp);
            return employee;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),
                    "Failed to modify employee personal details");
            employee.setReturnMessage(msgResp);
            return employee;
        }
    }

    /* To Update Only Employee Official Information */
    @Override
    public mst_employee modifyEmployeeOfficial(mst_employee employee)
    {
        MessageResponse msgResp = new MessageResponse();
        try
        {
            Optional<mst_employee> isEmpPresent = empRep.findById(employee.getEmp_id());
            if(isEmpPresent.isPresent())
            {
                mst_employee employeeToModify=isEmpPresent.get();
                employee.setEmp_code(employeeToModify.getEmp_code());
                employee.setEmp_first_name(employeeToModify.getEmp_first_name());
                employee.setEmp_middle_name(employeeToModify.getEmp_middle_name());
                employee.setEmp_last_name(employeeToModify.getEmp_last_name());
                employee.setEmp_image_path(employeeToModify.getEmp_image_path());
                employee.setGender(employeeToModify.getGender());
                employee.setDob(employeeToModify.getDob());
                employee.setAddress1(employeeToModify.getAddress1());
                employee.setAddress2(employeeToModify.getAddress2());
                employee.setLocation(employeeToModify.getLocation());
                employee.setDistrict(employeeToModify.getDistrict());
                employee.setPin(employeeToModify.getPin());
                employee.setContact_number(employeeToModify.getContact_number());
                employee.setEmail(employeeToModify.getEmail());

                employeeToModify = empRep.save(employee);
                msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(),
                        "Employee official details updated successfully!");
                employeeToModify.setReturnMessage(msgResp);
                return employeeToModify;
            }
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),
                    "Employee officially details not found to modify");
            employee.setReturnMessage(msgResp);
            return employee;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),
                    "Failed to modify employee");
            employee.setReturnMessage(msgResp);
            return employee;
        }
    }

    @Override
    public List<mst_employee> findAll() {
        return (List<mst_employee>) empRep.findAll();
    }

    @Override
    public Optional<mst_employee> findById(Long id) {
        return empRep.findById(id);
    }

    @Override
    public MessageResponse removeOne(Long id)
    {
        MessageResponse msgResp = new MessageResponse();
        try
        {
            empRep.deleteById(id);
            msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(), "Employee details deleted successfully!");
            return msgResp;
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),"Failed to delete employee");
            return msgResp;
        }
    }
}

