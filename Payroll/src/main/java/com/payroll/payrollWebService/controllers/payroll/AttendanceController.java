package com.payroll.payrollWebService.controllers.payroll;

import com.payroll.payrollWebService.models.common.CodeConstants;
import com.payroll.payrollWebService.models.payroll.AttendanceIdentity;
import com.payroll.payrollWebService.models.payroll.mst_category;
import com.payroll.payrollWebService.models.payroll.trn_emp_attendance;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import com.payroll.payrollWebService.service.EmpAttendance.EmpAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class AttendanceController {
    @Autowired
    private EmpAttendanceService empAttendanceService;

    @PostMapping("/save_attendance")
    public MessageResponse ManageSingleEmpAttendance(@Valid @RequestBody trn_emp_attendance emp_attendance) {
        try
        {
            return empAttendanceService.ManageSingleEmpAttendance(emp_attendance);
        }catch(Exception ex){
            return new MessageResponse(CodeConstants.FAILURE.getID(),ex.getMessage());
        }
    }

    @PostMapping("/empattendance/{month}/{year}")
    public MessageResponse GenerateAttendance(@PathVariable(value = "month") Integer month,
                                              @PathVariable(value = "year") Integer year){
        return empAttendanceService.GenerateAllAttendance(month,year);
    }

    @GetMapping("/get_single_emp_attendance/{month}/{year}/{empid}")
    public List<trn_emp_attendance> GetAttendanceOfSingleEmployee(@PathVariable(value = "month") Integer month,
                                                                  @PathVariable(value = "year") Integer year,
                                                                  @PathVariable(value = "empid") Long empid){
        AttendanceIdentity attendanceIdentity=new AttendanceIdentity(month,year,empid,null);
        return empAttendanceService.GetAttendanceOfSingleEmployee(attendanceIdentity);
    }
}
