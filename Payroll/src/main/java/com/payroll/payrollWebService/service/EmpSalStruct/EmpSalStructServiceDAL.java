package com.payroll.payrollWebService.service.EmpSalStruct;

import com.payroll.payrollWebService.models.common.CodeConstants;
import com.payroll.payrollWebService.models.common.ErrorHandling;
import com.payroll.payrollWebService.models.payroll.EmpEarnDedIdentity;
import com.payroll.payrollWebService.models.payroll.mst_district;
import com.payroll.payrollWebService.models.payroll.trn_emp_salary_structure;
import com.payroll.payrollWebService.models.payroll.mst_earn_ded_components;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import com.payroll.payrollWebService.repository.payroll.EarnDeductionRepository;
import com.payroll.payrollWebService.repository.payroll.EmpSalStructRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
class EmpSalStructServiceDAL extends EmpSalStructServiceImpl{

    @Autowired
    private EmpSalStructRepository empSalStructRep;

    @Autowired
    private EarnDeductionRepository earnDedRep;

    public EmpSalStructServiceDAL(){}

    @Override
    public trn_emp_salary_structure save(trn_emp_salary_structure empSalaryStructure)
    {
        MessageResponse msgResp =new MessageResponse();
        try{
            System.out.println("Inside save() of employee salary structure");
            EmpEarnDedIdentity empEarnDedIdentity= empSalaryStructure.getEmpEmpEarnDedIdentity();
            Optional<trn_emp_salary_structure> optEmpSal=findById(empEarnDedIdentity);

            if(optEmpSal.isPresent())
            {
                msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),
                        "Duplicate salary structure entry for this employee");
                empSalaryStructure.setReturnMessage(msgResp);
                return empSalaryStructure;
            }
            trn_emp_salary_structure empSalStructToAdd = empSalStructRep.save(empSalaryStructure);
            msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(),
                    "Salary Structure added successfully!");
            empSalStructToAdd.setReturnMessage(msgResp);
            return empSalStructToAdd;
        }catch(Exception ex)
        {
            System.out.println("Error Is: " + ex.getMessage());
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),
                    "Failed to add salary Structure");
            empSalaryStructure.setReturnMessage(msgResp);
            return empSalaryStructure;
        }
    }

    @Override
    public trn_emp_salary_structure modify(trn_emp_salary_structure empSalaryStructure)
    {
        MessageResponse msgResp = new MessageResponse();
        try
        {
            Optional<trn_emp_salary_structure> isEmpSalStructPresent = empSalStructRep.findById(empSalaryStructure.getEmpEmpEarnDedIdentity());
            if(isEmpSalStructPresent.isPresent())
            {
                trn_emp_salary_structure empSalStructToModify = empSalStructRep.save(empSalaryStructure);
                msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(),
                        "Salary structure modified successfully!");
                empSalStructToModify.setReturnMessage(msgResp);
                return empSalStructToModify;
            }
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),
                    "Salary structure not found to modify");
            empSalaryStructure.setReturnMessage(msgResp);
            return empSalaryStructure;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            empSalaryStructure.setReturnMessage(ErrorHandling.GetErrorMessage(ex.getMessage()));
            return empSalaryStructure;
        }
    }

    @Override
    public Optional<trn_emp_salary_structure> findById(EmpEarnDedIdentity empEarnDedIdentity) {
        return empSalStructRep.findById(empEarnDedIdentity); }

    @Override
    public List<trn_emp_salary_structure> findAll(Long empId)
    {
        List<trn_emp_salary_structure> lstEmpSalStruct=(List<trn_emp_salary_structure>) empSalStructRep.findAll();
        List<trn_emp_salary_structure> filterEmpSalStruct=new ArrayList<>();
        List<mst_earn_ded_components> lstEarnDedComponents=(List<mst_earn_ded_components>)earnDedRep.findAll();

        Stream<trn_emp_salary_structure> streamSalStruct=lstEmpSalStruct.stream()
                .filter(c->c.getEmpEmpEarnDedIdentity().getEmp_id().equals(empId));
        filterEmpSalStruct = streamSalStruct.collect(Collectors.toList());

        /*for(int i=0;i<lstEmpSalStruct.size();i++)
        {
            if(lstEmpSalStruct.get(i).getEmpEmpEarnDedIdentity().getEmp_id()==empId){
                filterEmpSalStruct.add(lstEmpSalStruct.get(i));
            }
        }*/

        for(int i=0;i<filterEmpSalStruct.size();i++)
        {
            for(int j=0;j<lstEarnDedComponents.size();j++)
            {
                if(filterEmpSalStruct.get(i).getEmpEmpEarnDedIdentity().getEarn_ded_id() ==
                        lstEarnDedComponents.get(j).getEarn_ded_id())
                {
                    filterEmpSalStruct.get(i).setEarnDedComponents(lstEarnDedComponents.get(j));
                }
            }
        }
        return filterEmpSalStruct;
    }
}
