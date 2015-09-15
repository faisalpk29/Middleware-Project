package com.omazan.Mobile.Entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Email
 *
 */
@Entity

public class Email implements Serializable {

	@Id 
	private int Id;
	private String Message;
	@Temporal(TemporalType.TIMESTAMP)
	private Date MessageTime;
	private static final long serialVersionUID = 1L;

	public Email() {
		super();
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public Date getMessageTime() {
		return MessageTime;
	}

	public void setMessageTime(Date messageTime) {
		MessageTime = messageTime;
	}
	
	
   
}
