package com.payroll.payrollWebService.payload.response;

public class MessageResponse {
	private Integer code;
	private String message;

	public MessageResponse(){}

	public MessageResponse(String message)
	{
	    this.message = message;
	}

	public MessageResponse(Integer code,String message)
	{
		this.code=code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
