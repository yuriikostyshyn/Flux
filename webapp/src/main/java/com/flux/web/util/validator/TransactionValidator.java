package com.flux.web.util.validator;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.flux.domain.Transaction;

@Component
public class TransactionValidator {

	public static final String AMOUNT_FIELD_NAME = "amount";
	public static final String AMOUNT_NONMATCH_ERROR_NAME = "nonmatch.amount";

	public void validate(Transaction target, Errors errors) {

		Transaction targetTransaction = (Transaction) target;
		
		rejectAmount(errors, targetTransaction);

	}

	private void rejectAmount(Errors errors, Transaction targetTransaction) {
		List<FieldError> amountRelatedErrors = errors.getFieldErrors(AMOUNT_FIELD_NAME);

		if (amountRelatedErrors.isEmpty()) {
			if (targetTransaction.getAmount() <= 0) {
				errors.rejectValue(AMOUNT_FIELD_NAME, AMOUNT_NONMATCH_ERROR_NAME);
			}
		}
	}

}
