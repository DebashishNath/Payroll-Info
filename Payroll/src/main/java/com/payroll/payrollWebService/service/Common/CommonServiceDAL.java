package com.payroll.payrollWebService.service.Common;

import com.payroll.payrollWebService.models.common.master_data;
import com.payroll.payrollWebService.models.payroll.mst_category;
import com.payroll.payrollWebService.models.payroll.mst_department;
import com.payroll.payrollWebService.models.payroll.mst_designation;
import com.payroll.payrollWebService.repository.payroll.CategoryRepository;
import com.payroll.payrollWebService.repository.payroll.DepartmentRepository;

import com.payroll.payrollWebService.repository.payroll.DesignationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommonServiceDAL extends CommonServiceImpl {

    @Autowired
    private DepartmentRepository deptRep;

    @Autowired
    private CategoryRepository catRep;

    @Autowired
    private DesignationRepository desigRep;

    public CommonServiceDAL() {}

    @Override
    public List<master_data> findAllMasterData(String formName)
    {
        List<master_data> lstMstData=new ArrayList<>();
        if(formName.equals("Department"))
        {
            List<mst_department> lstMstDept = ((List<mst_department>) deptRep.findAll())
                    .stream().filter(c->!c.getDepartment_name().trim().equals("N/A"))
                    .collect(Collectors.toList());
            for (int i = 0; i < lstMstDept.size(); i++)
            {
                master_data mstData = new master_data();
                mstData.setMasterId(lstMstDept.get(i).getDepartment_id());
                mstData.setMasterCode(lstMstDept.get(i).getDepartment_code());
                mstData.setMasterName(lstMstDept.get(i).getDepartment_name());
                lstMstData.add(mstData);
            }
        }

        if(formName.equals("Category"))
        {
            List<mst_category> lstMstCategory = ((List<mst_category>) catRep.findAll())
                    .stream().filter(c->!c.getCategory_name().trim().equals("N/A"))
                    .collect(Collectors.toList());

            for (int i = 0; i < lstMstCategory.size(); i++)
            {
                master_data mstData = new master_data();
                mstData.setMasterId(lstMstCategory.get(i).getCategory_id());
                mstData.setMasterCode(lstMstCategory.get(i).getCategory_code());
                mstData.setMasterName(lstMstCategory.get(i).getCategory_name());
                lstMstData.add(mstData);
            }
        }

        if(formName.equals("Designation"))
        {
            List<mst_designation> lstMstDesignation = ((List<mst_designation>) desigRep.findAll())
                    .stream().filter(c->!c.getDesignation_name().trim().equals("N/A"))
                    .collect(Collectors.toList());

            for (int i = 0; i < lstMstDesignation.size(); i++)
            {
                master_data mstData = new master_data();
                mstData.setMasterId(lstMstDesignation.get(i).getDesignation_id());
                mstData.setMasterCode(lstMstDesignation.get(i).getDesignation_code());
                mstData.setMasterName(lstMstDesignation.get(i).getDesignation_name());
                lstMstData.add(mstData);
            }
        }

        return lstMstData;
    }

}
