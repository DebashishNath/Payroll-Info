package com.payroll.payrollWebService.controllers.payroll;

import com.payroll.payrollWebService.models.common.master_data;
import com.payroll.payrollWebService.service.Common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")

public class CommonController {

    @Autowired
    private CommonService commonService;

    @GetMapping("/masterdatas/{formName}")
    public List<master_data> listMasterData(@PathVariable(value = "formName") String formName)
    {
        System.out.println(formName);
        return commonService.findAllMasterData(formName);
    }
}
