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

import com.flux.domain.Account;

public class AccountValidatorTest {

	private static final int ZERO_ID = 0;
	private static final String DUMMY_ERROR_NAME = "dummyErrorName";
	private static final String EMPTY_STRING = "";
	private static final String INCORRECT_LENGTH_KEY = "not 16 characters";

	@Mock
	private Account mockTargetAccount;

	private AccountValidator underTest;
	private Errors errors;

	@Before
	public void setUp() {
		underTest = new AccountValidator();
		MockitoAnnotations.initMocks(this);
		errors = new BeanPropertyBindingResult(mockTargetAccount, "newAccount");

	}

	@Test
	public void shouldRejectAccountIdValueIfAccountIdEqualsZeroAndErrorsInstanceDoesNotContainAccountIdRelatedErrors() {
		Mockito.when(mockTargetAccount.getAccountId()).thenReturn((long) ZERO_ID);
		initMockSecurityKey();

		assertErrorExists(AccountValidator.ACCOUNT_ID_FORM_FIELD_NAME,
				AccountValidator.NONMATCH_ACCOUNT_ID_PROPERTY_NAME);
	}

	@Test
	public void shouldRejectBankIdValueIfBankIdEqualsZeroAndErrorsInstanceDoesNotContainBankIdRelatedErrors() {
		Mockito.when(mockTargetAccount.getBankId()).thenReturn(ZERO_ID);
		initMockSecurityKey();

		assertErrorExists(AccountValidator.BANK_ID_FORM_FIELD_NAME, AccountValidator.NONMATCH_BANK_ID_PROPERTY_NAME);
	}

	@Test
	public void shouldNotRejectAccountIdValueIfErrorsInstanceContainsAccountIdRelatedErrors() {
		initMockSecurityKey();
		errors.rejectValue(AccountValidator.ACCOUNT_ID_FORM_FIELD_NAME, DUMMY_ERROR_NAME);
		assertErrorNotExists(AccountValidator.ACCOUNT_ID_FORM_FIELD_NAME,
				AccountValidator.NONMATCH_ACCOUNT_ID_PROPERTY_NAME);
	}

	@Test
	public void shouldNotRejectBankIdValueIfErrorsInstanceContainsBankIdRelatedErrors() {
		initMockSecurityKey();
		initFieldRelatedError();
		assertErrorNotExists(AccountValidator.BANK_ID_FORM_FIELD_NAME, AccountValidator.NONMATCH_BANK_ID_PROPERTY_NAME);
	}

	@Test
	public void shouldRejectValueIfSecurityKeyIsEmpty() {
		Mockito.when(mockTargetAccount.getSecurityKey()).thenReturn(EMPTY_STRING);

		assertErrorExists(AccountValidator.SECURITY_KEY_FORM_FIELD_NAME,
				AccountValidator.SECURITY_KEY_REQUIRED_PROPERTY_NAME);
	}

	@Test
	public void shouldRejectValueIfSecurityKeyLengthIsIncorrect() {
		Mockito.when(mockTargetAccount.getSecurityKey()).thenReturn(INCORRECT_LENGTH_KEY);
		assertErrorExists(AccountValidator.SECURITY_KEY_FORM_FIELD_NAME,
				AccountValidator.SECURITY_KEY_SIZE_MISMATCH_PROPERTY_NAME);
	}

	private void initMockSecurityKey() {
		Mockito.when(mockTargetAccount.getSecurityKey()).thenReturn(new String());
	}

	private void initFieldRelatedError() {
		errors.rejectValue(AccountValidator.BANK_ID_FORM_FIELD_NAME, DUMMY_ERROR_NAME);
	}

	private void assertErrorExists(String formFieldName, String nonmatchPropertyName) {
		boolean doesErrorExist = doesErrorExist(formFieldName, nonmatchPropertyName);

		Assert.assertTrue(doesErrorExist);
	}

	private void assertErrorNotExists(String formFieldName, String nonmatchPropertyName) {
		boolean doesErrorExist = doesErrorExist(formFieldName, nonmatchPropertyName);

		Assert.assertFalse(doesErrorExist);
	}

	private boolean doesErrorExist(String formFieldName, String errorPropertyName) {
		List<FieldError> fieldErrors = validateAccountAndGetFieldErrors(formFieldName);
		boolean result = false;
		ListIterator<FieldError> fieldErrorsIterator = fieldErrors.listIterator();
		while (fieldErrorsIterator.hasNext()) {
			FieldError currentError = fieldErrorsIterator.next();
			String errorCode = currentError.getCode();
			if (errorCode.equals(errorPropertyName)) {
				result = true;
				break;
			}
		}
		return result;
	}

	private List<FieldError> validateAccountAndGetFieldErrors(String formFieldName) {

		underTest.validate(mockTargetAccount, errors);

		List<FieldError> resultFieldErrors = errors.getFieldErrors(formFieldName);
		return resultFieldErrors;
	}

}
