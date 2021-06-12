package com.payroll.payrollWebService.service.MonthlyEmpSalSummary;

import com.payroll.payrollWebService.models.common.CodeConstants;
import com.payroll.payrollWebService.models.payroll.MonthlyEmpSalSummaryIdentity;
import com.payroll.payrollWebService.models.payroll.PrintPaySlip;
import com.payroll.payrollWebService.models.payroll.trn_monthly_emp_salary_details;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import com.payroll.payrollWebService.repository.payroll.MonthlyEmpSalSummaryRepository;
import com.payroll.payrollWebService.repository.payroll.MonthlyEmpSalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
class MonthlyEmpSalSummaryServiceDAL extends MonthlyEmpSalSummaryServiceImpl {
    @Autowired
    private MonthlyEmpSalSummaryRepository monthlyEmpSalSummaryRep;
    @Autowired
    private MonthlyEmpSalaryRepository monthlyEmpSalDetailsRep;

    @Override
    public PrintPaySlip PrintSinglePaySlip(MonthlyEmpSalSummaryIdentity empSalSummaryIdentity)
    {
        MessageResponse msp = new MessageResponse();
        PrintPaySlip printPaySlip=new PrintPaySlip();
        try
        {
            printPaySlip.setEmpSalSummary(monthlyEmpSalSummaryRep.findById(empSalSummaryIdentity));
            List<trn_monthly_emp_salary_details> lstEmpSalDetails = (List<trn_monthly_emp_salary_details>) monthlyEmpSalDetailsRep.findAll();
            List<trn_monthly_emp_salary_details> lstFilteredDetails = lstEmpSalDetails.stream().filter(
                    p -> p.getMonEmpSalIdentity().getMonth() == empSalSummaryIdentity.getMonth() &&
                            p.getMonEmpSalIdentity().getYear().intValue() == empSalSummaryIdentity.getYear().intValue() &&
                            p.getMonEmpSalIdentity().getEmp_id() == empSalSummaryIdentity.getEmp_id())
                    .collect(Collectors.toList());
            msp.setCode(CodeConstants.SUCCESS.getID());
            msp.setMessage("Payslip Printed Successfully");
            printPaySlip.setLstEmpSalDetails(lstFilteredDetails);
        }catch(Exception ex)
        {
            msp.setCode(CodeConstants.FAILURE.getID());
            msp.setMessage(ex.getMessage());
        }
        printPaySlip.setReturnMessage(msp);
        return printPaySlip;
    }
}
