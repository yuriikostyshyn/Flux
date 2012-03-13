package com.flux.web.util.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.flux.domain.Account;

@Component
public class AccountValidator implements Validator {

	public static final String ACCOUNT_ID_FORM_FIELD_NAME = "accountId";
	public static final String NONMATCH_ACCOUNT_ID_PROPERTY_NAME = "nonmatch.accountId";
	public static final String BANK_ID_FORM_FIELD_NAME = "bankId";
	public static final String NONMATCH_BANK_ID_PROPERTY_NAME = "nonmatch.bankId";
	public static final String SECURITY_KEY_FORM_FIELD_NAME = "securityKey";
	public static final String SECURITY_KEY_REQUIRED_PROPERTY_NAME = "required.securityKey";
	public static final String SECURITY_KEY_SIZE_MISMATCH_PROPERTY_NAME = "sizeMismatch.securityKey";
	public static final int SECURITY_KEY_REQUIRED_LENGTH = 16;

	private List<String> incorrectFields;

	public AccountValidator() {
		super();
		incorrectFields = new ArrayList<String>();
	}

	@Override
	public boolean supports(Class<?> classParameter) {

		return Account.class.isAssignableFrom(classParameter);
	}

	@Override
	public void validate(Object target, Errors errors) {
		formIncorrectFieldsList(errors);

		Account targetAccount = (Account) target;

		validateAccountId(errors, targetAccount);
		validateBankId(errors, targetAccount);
		validateSecurityKey(errors, targetAccount);

	}

	private void formIncorrectFieldsList(Errors errors) {
		List<FieldError> errorList = errors.getFieldErrors();

		for (FieldError error : errorList) {
			incorrectFields.add(error.getField());
		}
	}

	private void validateBankId(Errors errors, Account targetAccount) {
		if (doesFieldErrorExist(BANK_ID_FORM_FIELD_NAME)) {
			if (targetAccount.getBankId() <= 0) {
				errors.rejectValue(BANK_ID_FORM_FIELD_NAME, NONMATCH_BANK_ID_PROPERTY_NAME);
			}
		}
	}

	private void validateAccountId(Errors errors, Account targetAccount) {
		if (doesFieldErrorExist(ACCOUNT_ID_FORM_FIELD_NAME)) {
			if (targetAccount.getAccountId() <= 0) {
				errors.rejectValue(ACCOUNT_ID_FORM_FIELD_NAME, NONMATCH_ACCOUNT_ID_PROPERTY_NAME);
			}
		}
	}

	private void validateSecurityKey(Errors errors, Account targetAccount) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, SECURITY_KEY_FORM_FIELD_NAME,
				SECURITY_KEY_REQUIRED_PROPERTY_NAME);

		String targetAccountSecurityKey = targetAccount.getSecurityKey();
		int securityKeyLength = targetAccountSecurityKey.length();
		if (securityKeyLength != SECURITY_KEY_REQUIRED_LENGTH) {
			errors.rejectValue(SECURITY_KEY_FORM_FIELD_NAME, SECURITY_KEY_SIZE_MISMATCH_PROPERTY_NAME);
		}
	}

	private boolean doesFieldErrorExist(String fieldName) {
		boolean fieldErrorExist = false;

		if (!incorrectFields.contains(fieldName)) {
			fieldErrorExist = true;
		}
		return fieldErrorExist;
	}
}
