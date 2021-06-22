package com.payroll.payrollWebService.repository.payroll;

        import com.payroll.payrollWebService.models.payroll.mst_earn_ded_components;
        import org.springframework.data.repository.CrudRepository;
        import org.springframework.stereotype.Repository;

@Repository
public interface EarnDeductionRepository extends CrudRepository
        <mst_earn_ded_components,Long> {}