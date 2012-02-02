package com.flux.dbs.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.flux.dbs.dao.CurrencyDAO;
import com.flux.domain.Currency;
import com.flux.persistence.entities.CurrencyEntity;

@Component
@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
public class CurrencyDAOImpl extends CurrencyDAO {

	@Override
	public Currency getCurrencyById(Integer currencyId) {
		Currency resultCurrency = null;

		Query getCurrencyQuery = entityManager.createNamedQuery("getCurrencyById");
		getCurrencyQuery.setParameter(1, currencyId);
		
		List<CurrencyEntity> resultCurrencyEntities = getCurrencyQuery.getResultList();
		
		if (!resultCurrencyEntities.isEmpty()) {			
			CurrencyEntity resultCurrencyEntity = resultCurrencyEntities.get(0);
			resultCurrency = mapper.map(resultCurrencyEntity, Currency.class);			
		}
		return resultCurrency;
	}

}
