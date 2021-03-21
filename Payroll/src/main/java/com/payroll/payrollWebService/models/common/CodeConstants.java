package com.payroll.payrollWebService.models.common;

public enum CodeConstants{
    SUCCESS(0),
    BLANK(2),
    INVALID(3),
    WARNING(4),
    FAILURE(5),
    DUPLICATE(1062);

    private Integer id;

    CodeConstants(Integer id){
        this.id = id;
    }

    public Integer getID(){
        return id;
    }

}