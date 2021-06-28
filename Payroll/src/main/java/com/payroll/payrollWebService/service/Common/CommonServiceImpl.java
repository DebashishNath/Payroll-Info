package com.payroll.payrollWebService.service.Common;

import com.payroll.payrollWebService.models.common.master_data;

import com.payroll.payrollWebService.service.Common.CommonServiceDAL;

import java.util.List;
import java.util.Optional;

abstract public class CommonServiceImpl implements CommonService{
    @Override
    public List<master_data> findAllMasterData(String FormName)
    {
        return new CommonServiceDAL().findAllMasterData(FormName);
    }
}

