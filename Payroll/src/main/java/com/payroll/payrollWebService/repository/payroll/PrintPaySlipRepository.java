package com.payroll.payrollWebService.repository.payroll;

import com.payroll.payrollWebService.models.payroll.PrintPaySlipIdentity;
import com.payroll.payrollWebService.models.payroll.trn_print_pay_slip;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrintPaySlipRepository extends CrudRepository
        <trn_print_pay_slip, PrintPaySlipIdentity> {
}
