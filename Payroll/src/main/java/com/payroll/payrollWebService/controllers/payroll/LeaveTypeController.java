package com.payroll.payrollWebService.controllers.payroll;

        import com.payroll.payrollWebService.models.payroll.mst_leave_type;
        import com.payroll.payrollWebService.service.LeaveType.LeaveTypeService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.*;
        import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class LeaveTypeController {

    @Autowired
    private LeaveTypeService leaveTypeService;

    @GetMapping("/leave_types")
    public List<mst_leave_type> listLeaveType(){

        return leaveTypeService.findAll();
    }

}





