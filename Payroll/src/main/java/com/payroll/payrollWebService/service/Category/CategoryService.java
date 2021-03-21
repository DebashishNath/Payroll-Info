package com.payroll.payrollWebService.service.Category;

import com.payroll.payrollWebService.models.payroll.mst_category;
import com.payroll.payrollWebService.payload.response.MessageResponse;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    mst_category save(mst_category category);
}
