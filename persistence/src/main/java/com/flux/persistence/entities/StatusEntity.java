package com.flux.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the status database table.
 * 
 */
@Entity
@Table(name = "status")
public class StatusEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private int statusId;

	private String statusMessage;

	public StatusEntity() {
	}

	@Id
	@Column(name = "status_id")
	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	@Column(name = "status_message")
	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

}