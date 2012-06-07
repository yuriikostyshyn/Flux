package com.flux.domain;

import com.flux.domain.utils.HashCodeUtil;

import static com.flux.domain.utils.HashCodeUtil.hash;

public class TransactionStatus {
	private int statusId;
	private String statusMessage;

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public boolean equals(Object instanceToCompare) {
		boolean result = false;

		if (instanceToCompare != null) {
			if (instanceToCompare.getClass().equals(getClass())) {
				if (instanceToCompare == this) {
					result = true;
				} else if (instanceToCompare.hashCode() == hashCode()) {

					TransactionStatus statusToCompare = (TransactionStatus) instanceToCompare;

					if (statusToCompare.getStatusId() == getStatusId()) {
						result = true;
					}

				}
			}
		}

		return result;

	}

	public int hashCode() {
		return hash(HashCodeUtil.SEED, getStatusId());
	}
}
