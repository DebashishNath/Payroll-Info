package com.payroll.payrollWebService.service.Common;

import com.payroll.payrollWebService.models.common.master_data;

import java.util.List;

public interface CommonService {
    List<master_data> findAllMasterData(String FormName);
}
