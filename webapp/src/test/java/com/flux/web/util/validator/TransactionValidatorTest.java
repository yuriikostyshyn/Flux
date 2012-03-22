package com.flux.web.util.validator;

import java.util.List;
import java.util.ListIterator;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.flux.domain.Transaction;

public class TransactionValidatorTest {

	@Mock
	private Transaction mockTargetTransaction;

	private TransactionValidator underTest;
	private Errors errors;

	@Before
	public void setUp() {
		underTest = new TransactionValidator();
		
		MockitoAnnotations.initMocks(this);
		
		errors = new BeanPropertyBindingResult(mockTargetTransaction, "newTransaction");
		
		
	}

	@Test
	public void shouldAddNonMatchErrorIfThereAreNotAnyAmountRelatedFieldErrors() {		
		Mockito.when(mockTargetTransaction.getAmount()).thenReturn(-1.2);
		
		boolean doesNonmatchErrorExist = validateTransactionAndGetFieldErrors(TransactionValidator.AMOUNT_FIELD_NAME, TransactionValidator.AMOUNT_NONMATCH_ERROR_NAME);
		
		Assert.assertTrue(doesNonmatchErrorExist);
	}
	
	@Test
	public void shouldNotAddNonMatchErrorIfThereAreAnyAmountRelatedFieldErrors(){	
		errors.rejectValue(TransactionValidator.AMOUNT_FIELD_NAME, "dummy error name");
		
		boolean doesNonmatchErrorExist = validateTransactionAndGetFieldErrors(TransactionValidator.AMOUNT_FIELD_NAME, TransactionValidator.AMOUNT_NONMATCH_ERROR_NAME);
	
		Assert.assertFalse(doesNonmatchErrorExist);
	}

	private boolean validateTransactionAndGetFieldErrors(String fieldName, String errorName){
		underTest.validate(mockTargetTransaction, errors);
		
		return doesFieldErrorExist(fieldName, errorName);
	}
	
	private boolean doesFieldErrorExist(String fieldName, String errorName) {
		boolean doesFieldErrorExist = false;

		List<FieldError> fieldErrors = errors.getFieldErrors(fieldName);
		if (fieldErrors != null) {

			ListIterator<FieldError> fieldErrorsIterator = fieldErrors.listIterator();
			while (fieldErrorsIterator.hasNext()) {
				FieldError currentError = fieldErrorsIterator.next();
				String errorCode = currentError.getCode();
				if (errorCode.equals(errorName)) {
					doesFieldErrorExist = true;
				}
			}
		}
		return doesFieldErrorExist;
	}
}
