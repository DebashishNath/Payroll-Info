package com.payroll.payrollWebService.service.State;

import com.payroll.payrollWebService.models.common.CodeConstants;
import com.payroll.payrollWebService.models.payroll.mst_state;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import com.payroll.payrollWebService.repository.payroll.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.List;
import java.util.Optional;

@Service
class StateServiceDAL extends StateServiceImpl {

    @Autowired
    private StateRepository stateRepository;

    @PersistenceContext
    private EntityManager em;

    public StateServiceDAL() {}

    @Override
    public mst_state save(mst_state state)
    {
        MessageResponse msgResp =new MessageResponse();
        try{
            mst_state stateToAdd = stateRepository.save(state);
            msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(),
                    "New State details added successfully!");
            stateToAdd.setReturnMessage(msgResp);
            return stateToAdd;
        }catch(Exception ex)
        {
            System.out.println("Error Is: " + ex.getMessage());
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),
                    "Failed to add new state details");
            state.setReturnMessage(msgResp);
            return state;
        }
    }
    

    @Override
    public mst_state modify(mst_state state)
    {
        MessageResponse msgResp = new MessageResponse();
        try
        {
            mst_state stateToModify = stateRepository.save(state);
            msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(), "State details modified successfully!");
            stateToModify.setReturnMessage(msgResp);
            return stateToModify;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),"Failed to modify state");
            state.setReturnMessage(msgResp);
            return state;
        }
    }

    @Override
    public List<mst_state> findAll() {
        System.out.println("Inside findAll() to test procedure");
        StoredProcedureQuery findAllStates =
                em.createNamedStoredProcedureQuery("DisplayAllStates");
        return (List<mst_state>)findAllStates.getResultList();
        //return (List<mst_state>) stateRepository.findAll();
    }

    @Override
    public Optional<mst_state> findById(Long id) {return stateRepository.findById(id); }

    @Override
    public MessageResponse removeOne(Long id)
    {
        MessageResponse msgResp = new MessageResponse();
        try
        {
            stateRepository.deleteById(id);
            msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(), "State details deleted successfully!");
            return msgResp;
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),"Failed to delete state");
            return msgResp;
        }
    }

}
