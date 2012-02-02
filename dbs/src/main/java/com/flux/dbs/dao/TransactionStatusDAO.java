package com.flux.dbs.dao;

import com.flux.domain.TransactionStatus;


public abstract class TransactionStatusDAO extends AbstractDAO{
	
	public abstract TransactionStatus getStatusById(Integer statusId);
	
}
