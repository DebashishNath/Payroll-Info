package com.payroll.payrollWebService.service.EmpAttendance;

import com.payroll.payrollWebService.models.common.CodeConstants;
import com.payroll.payrollWebService.models.payroll.AttendanceIdentity;
import com.payroll.payrollWebService.models.payroll.mst_category;
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
    public trn_emp_attendance save(trn_emp_attendance empAttendance)
    {
        MessageResponse msgResp =new MessageResponse();
        try
        {
            trn_emp_attendance attendanceToSave = empAttendanceRep.save(empAttendance);
            msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(),
                        "Attendance details updated successfully!");
            attendanceToSave.setReturnMessage(msgResp);
            return attendanceToSave;
        }catch(Exception ex)
        {
            System.out.println("Error Is: " + ex.getMessage());
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),
                    "Failed to update attendance details");
            empAttendance.setReturnMessage(msgResp);
            return empAttendance;
        }
    }
    
    @Override
    public MessageResponse GenerateAllAttendance(Integer p_month, Integer p_year) {
        MessageResponse msp = new MessageResponse();
        try
        {
            StoredProcedureQuery generateAttendance =
                em.createNamedStoredProcedureQuery("GenerateMonthlyAttendance")
                    .registerStoredProcedureParameter("p_month", Integer.class, ParameterMode.IN)
                    .setParameter("p_month", p_month)
                    .registerStoredProcedureParameter("p_year", Integer.class, ParameterMode.IN)
                    .setParameter("p_year", p_year)
                    .registerStoredProcedureParameter("p_return_message", String.class, ParameterMode.OUT);
            generateAttendance.execute();
            String returnMsg= (String)generateAttendance.getOutputParameterValue("p_return_message");
            msp.setCode(Integer.parseInt(returnMsg.substring(0,1)));
            msp.setMessage(returnMsg.substring(2));
        } catch (Exception ex) {
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
