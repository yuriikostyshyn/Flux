package com.flux.domain;

import com.flux.domain.utils.HashCodeUtil;

import static com.flux.domain.utils.HashCodeUtil.hash;

public class Currency {
	private int currencyId;
	private String name;
	private String longName;

	public int getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(int currencyId) {
		this.currencyId = currencyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLongName() {
		return longName;
	}

	public void setLongName(String longName) {
		this.longName = longName;
	}

	public boolean equals(Object instanceToCompare) {
		boolean result = false;

		if (instanceToCompare != null) {
			if (instanceToCompare.getClass().equals(getClass())) {
				if (instanceToCompare == this) {
					result = true;
				} else if (instanceToCompare.hashCode() == hashCode()) {

					Currency currencyToCompare = (Currency) instanceToCompare;

					if (currencyToCompare.getCurrencyId() == getCurrencyId()) {
						result = true;
					}
				}

			}

		}

		return result;

	}

	public int hashCode() {
			return hash(HashCodeUtil.SEED, getCurrencyId());
	}
}
