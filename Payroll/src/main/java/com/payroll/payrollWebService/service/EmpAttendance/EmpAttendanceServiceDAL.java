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
import java.util.List;

@Service
class EmpAttendanceServiceDAL extends EmpAttendanceServiceImpl {
    @Autowired
    private EmployeeRepository empRep;

    @PersistenceContext
    private EntityManager em;

    @Override
    public MessageResponse GenerateAttendance(Integer p_month, Integer p_year) {
        MessageResponse msp = new MessageResponse();
        try
        {
            List<mst_employee> lstEmployees = (List<mst_employee>) empRep.findAll();

            for (int i = 0; i <=lstEmployees.size() - 1; i++)
            {
                System.out.println("Emp Code - " + lstEmployees.get(i).getEmp_id());
                StoredProcedureQuery generateAttendance =
                        em.createNamedStoredProcedureQuery("generateMonthlyAttendance")
                        .registerStoredProcedureParameter("p_month",Integer.class, ParameterMode.IN)
                        .setParameter("p_month", p_month)
                        .registerStoredProcedureParameter("p_year",Integer.class,ParameterMode.IN)
                        .setParameter("p_year", p_year)
                        .registerStoredProcedureParameter("p_emp_id",Long.class,ParameterMode.IN)
                        .setParameter("p_emp_id", lstEmployees.get(i).getEmp_id());
                generateAttendance.execute();
            }
            msp.setCode(CodeConstants.SUCCESS.getID());
            msp.setMessage("Attendance Generated Successfully For " + p_month + "-" + p_year);

        } catch (Exception ex) {
            msp.setCode(CodeConstants.FAILURE.getID());
            msp.setMessage(ex.getMessage());
        }
        return msp;
    }
}
