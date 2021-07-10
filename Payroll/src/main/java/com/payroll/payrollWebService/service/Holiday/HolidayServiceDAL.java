package com.payroll.payrollWebService.service.Holiday;

import com.payroll.payrollWebService.models.common.CodeConstants;
import com.payroll.payrollWebService.models.common.ErrorHandling;
import com.payroll.payrollWebService.models.payroll.mst_holiday;
import com.payroll.payrollWebService.payload.response.MessageResponse;
import com.payroll.payrollWebService.repository.payroll.HolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.Date;
import java.util.stream.Collectors;

@Service
class HolidayServiceDAL extends HolidayServiceImpl {

    @Autowired
    private HolidayRepository holidayRep;

    @PersistenceContext
    private EntityManager em;

    public HolidayServiceDAL() {}

    @Override
    public mst_holiday save(mst_holiday holiday)
    {
        MessageResponse msgResp =new MessageResponse();
        try
        {
            mst_holiday holidayToUpdate = holidayRep.save(holiday);
            msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(),
                    "Holiday details updated successfully!");
            holidayToUpdate.setReturnMessage(msgResp);
            return holidayToUpdate;
        }catch(Exception ex)
        {
            System.out.println("Error Is: " + ex.getMessage());
            holiday.setReturnMessage(ErrorHandling.GetErrorMessage(ex.getMessage()));
            return holiday;
        }
    }

    @Override
    public List<mst_holiday> findAll(Integer p_month,Integer p_year)
    {
        try {
            StoredProcedureQuery getHolidays =
                    em.createNamedStoredProcedureQuery("GetHolidayMonthWise")
                            .registerStoredProcedureParameter("p_month", Integer.class, ParameterMode.IN)
                            .setParameter("p_month", p_month)
                            .registerStoredProcedureParameter("p_year", Integer.class, ParameterMode.IN)
                            .setParameter("p_year", p_year);
            return ((List<mst_holiday>) getHolidays.getResultList());
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public Optional<mst_holiday> findById(Long id) {return holidayRep.findById(id); }

    @Override
    public MessageResponse removeOne(Long id)
    {
        MessageResponse msgResp = new MessageResponse();
        try
        {
            holidayRep.deleteById(id);
            msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(), "Holiday details deleted successfully!");
            return msgResp;
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),"Failed to delete Holiday");
            return msgResp;
        }
    }

}
