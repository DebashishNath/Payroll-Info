package com.payroll.payrollWebService.repository.payroll;
import com.payroll.payrollWebService.models.payroll.trn_ptax_monthly;
import com.payroll.payrollWebService.models.payroll.PTaxMonthlyIdentity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PTAXMonthlyRepository extends CrudRepository<trn_ptax_monthly, PTaxMonthlyIdentity> {
}