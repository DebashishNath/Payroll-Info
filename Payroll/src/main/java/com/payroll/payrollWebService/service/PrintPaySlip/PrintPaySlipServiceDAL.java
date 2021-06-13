package com.payroll.payrollWebService.service.PrintPaySlip;

import com.payroll.payrollWebService.models.common.CodeConstants;
import com.payroll.payrollWebService.models.payroll.PrintPaySlipIdentity;
import com.payroll.payrollWebService.models.payroll.print_single_pay_slip;
import com.payroll.payrollWebService.models.payroll.trn_print_pay_slip;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.ArrayList;
import java.util.List;

@Service
class PrintPaySlipServiceDAL extends PrintPaySlipServiceImpl {

    @PersistenceContext
    private EntityManager em;

    @Override
    public print_single_pay_slip PrintSinglePaySlip(PrintPaySlipIdentity paySlipIdentity)
    {
        MessageResponse msp = new MessageResponse();
        print_single_pay_slip printSinglePaySlip =new print_single_pay_slip();

        try
        {
            StoredProcedureQuery paySlip =
                    em.createNamedStoredProcedureQuery("PrintSinglePaySlip")
                            .registerStoredProcedureParameter("p_user_id", Integer.class, ParameterMode.IN)
                            .setParameter("p_user_id", paySlipIdentity.getUser_id().intValue())
                            .registerStoredProcedureParameter("p_month", Integer.class, ParameterMode.IN)
                            .setParameter("p_month", paySlipIdentity.getPay_month().intValue())
                            .registerStoredProcedureParameter("p_year", Integer.class, ParameterMode.IN)
                            .setParameter("p_year", paySlipIdentity.getPay_year().intValue())
                            .registerStoredProcedureParameter("p_emp_id", Integer.class, ParameterMode.IN)
                            .setParameter("p_emp_id", paySlipIdentity.getEmp_id().intValue());
            List<trn_print_pay_slip> paySlipList = paySlip.getResultList();
            if(paySlipList!=null && paySlipList.size()>0)
            {
                printSinglePaySlip.setLstPrintPaySlip(paySlipList);
                msp.setCode(CodeConstants.SUCCESS.getID());
                msp.setMessage("Payslip Printed Successfully");
            }else
            {
                msp.setCode(CodeConstants.FAILURE.getID());
                msp.setMessage("Payslip Printing failed");
            }
        }catch(Exception ex)
        {
            msp.setCode(CodeConstants.FAILURE.getID());
            msp.setMessage(ex.getMessage());
        }
        printSinglePaySlip.setReturnMessage(msp);
        return printSinglePaySlip;
    }
}
