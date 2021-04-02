package com.payroll.payrollWebService.service.EmpAttendance;

import com.payroll.payrollWebService.models.common.CodeConstants;
import com.payroll.payrollWebService.models.payroll.mst_employee;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import com.payroll.payrollWebService.repository.payroll.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.time.Month;
import java.util.List;

@Service
class EmpAttendanceServiceDAL extends EmpAttendanceServiceImpl {
    @Autowired
    private EmployeeRepository empRep;

    @PersistenceContext
    private EntityManager em;

    @Override
    public MessageResponse GenerateAllAttendance(Integer p_month, Integer p_year) {
        MessageResponse msp = new MessageResponse();
        try
        {
            List<mst_employee> lstEmployees = (List<mst_employee>) empRep.findAll();

            if(lstEmployees.size()>0) {
                for (int i = 0; i <= lstEmployees.size() - 1; i++) {
                    ExecuteAttendance(p_month,p_year,lstEmployees.get(i).getEmp_id());
                }
                msp.setCode(CodeConstants.SUCCESS.getID());
                msp.setMessage("Attendance Generated Successfully For " + Month.of(p_month) + "-" + p_year);
            }
            else
            {
                msp.setCode(CodeConstants.FAILURE.getID());
                msp.setMessage("Failed to generate attendance");
            }
        } catch (Exception ex) {
            msp.setCode(CodeConstants.FAILURE.getID());
            msp.setMessage(ex.getMessage());
        }
        return msp;
    }

    private void ExecuteAttendance(Integer p_month,Integer p_year,Long emp_id){
        StoredProcedureQuery generateAttendance =
                em.createNamedStoredProcedureQuery("GenerateMonthlyAttendance")
                        .registerStoredProcedureParameter("p_month", Integer.class, ParameterMode.IN)
                        .setParameter("p_month", p_month)
                        .registerStoredProcedureParameter("p_year", Integer.class, ParameterMode.IN)
                        .setParameter("p_year", p_year)
                        .registerStoredProcedureParameter("p_emp_id", Long.class, ParameterMode.IN)
                        .setParameter("p_emp_id", emp_id);
        generateAttendance.execute();
    }

    @Override
    public MessageResponse GenerateSingleAttendance(Integer p_month, Integer p_year,Long p_emp_id) {
        MessageResponse msp = new MessageResponse();
        try
        {
           ExecuteAttendance(p_month,p_year,p_emp_id);
           msp.setCode(CodeConstants.SUCCESS.getID());
           msp.setMessage("Attendance Generated Successfully For " + Month.of(p_month) + "-" + p_year);
        } catch (Exception ex)
        {
            msp.setCode(CodeConstants.FAILURE.getID());
            msp.setMessage(ex.getMessage());
        }
        return msp;
    }

}
