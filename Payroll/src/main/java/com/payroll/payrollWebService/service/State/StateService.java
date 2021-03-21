package com.payroll.payrollWebService.service.State;

import com.payroll.payrollWebService.models.payroll.mst_state;
import com.payroll.payrollWebService.payload.response.MessageResponse;

import java.util.List;
import java.util.Optional;

public interface StateService {

    mst_state save(mst_state state);

    mst_state modify(mst_state state);

    List<mst_state> findAll();

    Optional<mst_state> findById(Long id);

    MessageResponse removeOne(Long id);

    //Bank GetBankName(String bankName);
}