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
	public TransactionStatus getStatusById(int statusId) {
		TransactionStatus result = null;
		Query getStatusQuery = entityManager.createNamedQuery("getStatusById");
		getStatusQuery.setParameter(1, statusId);
		List<TransactionStatusEntity> queryResultList = getStatusQuery.getResultList();
		if(!queryResultList.isEmpty()){
			TransactionStatusEntity resultEntity = queryResultList.get(0);
			result = mapper.map(resultEntity, TransactionStatus.class);
		}
		return result;
	}

}
