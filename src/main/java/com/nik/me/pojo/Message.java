package com.nik.me.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//Pojo to add the Message (Extension of Lab)


@Entity
@Table(name="message_table")
public class Message {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "messageID", unique=true, nullable = false)
	private long messageId;
	
	@Column(name = "message")
	private String message;
	
	@Column(name = "fromPersonID")
	private String fromPersonId;
	
	@Column(name = "ToPersonID")
	private String toPersonId;
	
	@Column(name = "itemName")
	private String itemName;
	
	
	public long getMessageId() {
		return messageId;
	}

	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Message() {
		
		
	}
	
    public Message(String message){
        this.message = message;

	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}

	public String getFromPersonId() {
		return fromPersonId;
	}

	public void setFromPersonId(String fromPersonId) {
		this.fromPersonId = fromPersonId;
	}

	public String getToPersonId() {
		return toPersonId;
	}

	public void setToPersonId(String toPersonId) {
		this.toPersonId = toPersonId;
	}

    
	
}
