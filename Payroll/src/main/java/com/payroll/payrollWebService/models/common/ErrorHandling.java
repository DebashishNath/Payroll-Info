package com.payroll.payrollWebService.models.common;

import com.payroll.payrollWebService.payload.response.MessageResponse;

public class ErrorHandling {
    static class ErrorKeys{
        //Designation Unique Keys
        static String UK_DESIG_CODE ="mst_designation.UK_DESIG_CODE";
        static String UK_DESIG_NAME ="mst_designation.UK_DESIG_NAME";

        //Employee Unique Keys
        static String UK_EMP_CODE ="mst_employee.UK_EMP_CODE";
        //Employee Foreign Keys
        static String FK_EMP_CATEGORY_ID="mst_employee.FK_EMP_CATEGORY_ID";
        static String FK_EMP_DEPARTMENT_ID="mst_employee.FK_EMP_DEPARTMENT_ID";
        static String FK_EMP_DESIGNATION_ID="mst_employee.FK_EMP_DESIGNATION_ID";
        static String FK_EMP_DISTRICT_ID="mst_employee.FK_EMP_DISTRICT_ID";
        static String FK_EMP_LOCATION_ID="mst_employee.FK_EMP_LOCATION_ID";
    }

    public static MessageResponse GetErrorMessage(String errorMessage){
        //Handling of Designation Errors
        if(errorMessage.contains(ErrorKeys.UK_DESIG_CODE)){
            return new MessageResponse(CodeConstants.DUPLICATE.getID(),
                    "Designation code already exists");
        }
        if(errorMessage.contains(ErrorKeys.UK_DESIG_NAME)){
            return new MessageResponse(CodeConstants.DUPLICATE.getID(),
             "Designation name already exists");
        }

        //Handling of Employee Errors
        if(errorMessage.contains(ErrorKeys.UK_EMP_CODE)){
            return new MessageResponse(CodeConstants.DUPLICATE.getID(),
                    "Employee code already exists");
        }
        if(errorMessage.contains(ErrorKeys.FK_EMP_CATEGORY_ID)){
            return new MessageResponse(CodeConstants.INVALID.getID(),
                    "Employee category do not exist");
        }

        return new MessageResponse(CodeConstants.FAILURE.getID(),
                "Failed to retrieve/update details");
    }
}
