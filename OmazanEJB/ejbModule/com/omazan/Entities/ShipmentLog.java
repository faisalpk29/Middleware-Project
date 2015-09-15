package com.omazan.Entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: ShipmentLog
 *
 */
@Entity

public class ShipmentLog implements Serializable {

	
	@Id @GeneratedValue
	private int Id;
	private String Status;
	@Temporal(TemporalType.TIMESTAMP)
	private Date LogTime;
	private static final long serialVersionUID = 1L;

	public ShipmentLog() {
		super();
	}   
	public int getId() {
		return this.Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}   
	public String getStatus() {
		return this.Status;
	}

	public void setStatus(String Status) {
		this.Status = Status;
	}   
	public Date getLogTime() {
		return this.LogTime;
	}

	public void setLogTime(Date LogTime) {
		this.LogTime = LogTime;
	}
   
}
