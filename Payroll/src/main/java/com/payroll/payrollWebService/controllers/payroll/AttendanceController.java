package com.payroll.payrollWebService.controllers.payroll;

import com.payroll.payrollWebService.payload.response.MessageResponse;
import com.payroll.payrollWebService.service.EmpAttendance.EmpAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class AttendanceController {
    @Autowired
    private EmpAttendanceService empAttendanceService;

    @GetMapping("/empattendance/{month}/{year}")
    public MessageResponse GenerateAttendance(@PathVariable(value = "month") Integer month,
                                              @PathVariable(value = "year") Integer year){
        return empAttendanceService.GenerateAttendance(month,year);
    }
}
