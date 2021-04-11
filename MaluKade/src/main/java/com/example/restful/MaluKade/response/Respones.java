package com.example.restful.MaluKade.response;

import java.util.Date;

public class Respones {
	private Date times_Stamp;
    private String status;
    private String message;
    private String details;
    
    public Respones() {
		
	}
    
	public Respones(Date times_Stamp, String status, String message, String details) {
		super();
		this.times_Stamp = times_Stamp;
		this.status = status;
		this.message = message;
		this.details = details;
	}

	public void setTimes_Stamp(Date times_Stamp) {
		this.times_Stamp = times_Stamp;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setDetails(String details) {
		this.details = details;
	}
    
    public Date getTimes_Stamp() {
		return times_Stamp;
	}

	public String getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

	@Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return super.toString();
    }
}
