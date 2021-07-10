package com.payroll.payrollWebService.controllers.payroll;

import com.payroll.payrollWebService.models.common.CodeConstants;
import com.payroll.payrollWebService.models.payroll.mst_holiday;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import com.payroll.payrollWebService.service.Holiday.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class HolidayController {

    @Autowired
    private HolidayService holidayService;

    @GetMapping("/holidays/{p_month}/{p_year}")
    public List<mst_holiday> listDepartment(@PathVariable(value = "p_month") Integer p_month,
                                            @PathVariable(value = "p_year") Integer p_year)
    {
        return holidayService.findAll(p_month,p_year);
    }

    @GetMapping("/holiday/{id}")
    public Optional<mst_holiday> getOneDepartment(@PathVariable(value = "id") Long id){
        return holidayService.findById(id);
    }

    @PostMapping("/saveholiday")
    public ResponseEntity<?> saveHoliday(@Valid @RequestBody mst_holiday holiday) {
        try
        {
            mst_holiday holidayToUpdate= holidayService.save(holiday);
            return ResponseEntity.ok(new MessageResponse(holidayToUpdate.getReturnMessage().getCode(),
                    holidayToUpdate.getReturnMessage().getMessage()));
        }catch(Exception ex){
            return ResponseEntity.ok(new MessageResponse(CodeConstants.FAILURE.getID(),ex.getMessage()));
        }
    }

    @PostMapping("/deleteHoliday/{id}")
    public ResponseEntity<?> deleteHoliday(@PathVariable(value = "id") Long id)
    {
        try
        {
            MessageResponse msgResp= holidayService.removeOne(id);
            return ResponseEntity.ok(msgResp);
        }catch(Exception ex){
            return ResponseEntity.ok(new MessageResponse(CodeConstants.FAILURE.getID(),
                    ex.getMessage()));
        }
    }
}




