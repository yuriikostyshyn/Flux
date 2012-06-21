package com.flux.dbs.dao;

import com.flux.domain.TransactionStatus;


public interface TransactionStatusDAO {
	
	public TransactionStatus getStatusById(Integer statusId);
	
}
