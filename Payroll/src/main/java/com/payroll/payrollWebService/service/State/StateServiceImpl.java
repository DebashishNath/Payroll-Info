package com.payroll.payrollWebService.service.State;

import com.payroll.payrollWebService.models.common.CodeConstants;
import com.payroll.payrollWebService.models.payroll.mst_state;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import com.payroll.payrollWebService.repository.payroll.StateRepository;
import com.payroll.payrollWebService.service.State.StateServiceDAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
abstract class StateServiceImpl implements StateService {

    @Autowired
    private StateRepository stateRepository;

    public StateServiceImpl() {}

    @Override
    public mst_state save(mst_state state)
    {
        return new StateServiceDAL().save(state);
    }

    @Override
    public mst_state modify(mst_state state)
    {
        return new StateServiceDAL().modify(state);
    }

    @Override
    public List<mst_state> findAll() {
        return new StateServiceDAL().findAll();
    }

    @Override
    public Optional<mst_state> findById(Long id) {return new StateServiceDAL().findById(id); }

    @Override
    public MessageResponse removeOne(Long id)
    {
        return new StateServiceDAL().removeOne(id);
    }

}
