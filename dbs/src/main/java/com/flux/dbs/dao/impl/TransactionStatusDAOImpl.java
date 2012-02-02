package com.flux.dbs.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.flux.dbs.dao.TransactionStatusDAO;
import com.flux.domain.TransactionStatus;
import com.flux.persistence.entities.TransactionStatusEntity;

@Component
@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
public class TransactionStatusDAOImpl extends TransactionStatusDAO{

	@Override
	public TransactionStatus getStatusById(Integer statusId) {
		TransactionStatus resultTransactionStatus = null;
		
		Query getStatusQuery = entityManager.createNamedQuery("getStatusById");
		getStatusQuery.setParameter(1, statusId);
		
		List<TransactionStatusEntity> resultTransactionStatusEntities = getStatusQuery.getResultList();
		
		if(!resultTransactionStatusEntities.isEmpty()){			
			TransactionStatusEntity resultTransactionStatusEntity = resultTransactionStatusEntities.get(0);
			resultTransactionStatus = mapper.map(resultTransactionStatusEntity, TransactionStatus.class);
		}
		return resultTransactionStatus;
	}

}
