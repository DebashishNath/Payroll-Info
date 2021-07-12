package com.payroll.payrollWebService.service.EmpAttendance;

import com.payroll.payrollWebService.models.common.CodeConstants;
import com.payroll.payrollWebService.models.payroll.AttendanceIdentity;
import com.payroll.payrollWebService.models.payroll.mst_employee;
import com.payroll.payrollWebService.models.payroll.trn_emp_attendance;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import com.payroll.payrollWebService.repository.payroll.EmployeeRepository;
import com.payroll.payrollWebService.repository.payroll.EmpAttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.time.Month;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
class EmpAttendanceServiceDAL extends EmpAttendanceServiceImpl {
    @Autowired
    private EmployeeRepository empRep;

    @Autowired
    private EmpAttendanceRepository empAttendanceRep;

    @PersistenceContext
    private EntityManager em;

    @Override
    public MessageResponse GenerateAllAttendance(Integer p_month, Integer p_year) {
        MessageResponse msp = new MessageResponse();
        try
        {
            List<mst_employee> lstEmployees = ((List<mst_employee>) empRep.findAll())
                    .stream().filter(c->!c.getEmp_first_name().equals("N/A"))
                    .collect(Collectors.toList());

            if(lstEmployees.size()>0) {
                for (int i = 0; i <= lstEmployees.size() - 1; i++) {
                    System.out.println(lstEmployees.get(i).getEmp_first_name());
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

    private void ExecuteAttendance(Integer p_month,Integer p_year,Long p_emp_id){
        StoredProcedureQuery generateAttendance =
                em.createNamedStoredProcedureQuery("GenerateMonthlyAttendance")
                        .registerStoredProcedureParameter("p_month", Integer.class, ParameterMode.IN)
                        .setParameter("p_month", p_month)
                        .registerStoredProcedureParameter("p_year", Integer.class, ParameterMode.IN)
                        .setParameter("p_year", p_year)
                        .registerStoredProcedureParameter("p_emp_id", Long.class, ParameterMode.IN)
                        .setParameter("p_emp_id", p_emp_id);
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

    @Override
    public List<trn_emp_attendance> GetAttendanceOfSingleEmployee(AttendanceIdentity attendanceIdentity){
        return ((List<trn_emp_attendance>)empAttendanceRep.findAll()).stream()
                .filter(c->c.getAttendanceIdentity().getEmp_id() == attendanceIdentity.getEmp_id()
                && c.getAttendanceIdentity().getMonth() == attendanceIdentity.getMonth()
                && c.getAttendanceIdentity().getYear() == attendanceIdentity.getYear()
        ).collect(Collectors.toList());
    }

}
