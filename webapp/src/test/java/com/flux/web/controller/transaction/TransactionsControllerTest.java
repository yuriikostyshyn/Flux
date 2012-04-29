package com.flux.web.controller.transaction;

import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.flux.domain.Account;
import com.flux.domain.Transaction;
import com.flux.manager.AccountsManager;
import com.flux.manager.TransactionsManager;
import com.flux.web.controller.account.AccountController;
import com.flux.web.util.propertyeditor.AccountPropertyEditor;
import com.flux.web.util.validator.TransactionValidator;

public class TransactionsControllerTest {

	private static final long MOCK_SELECTED_ACCOUNT_ID=1L;
	
	private static final String DUMMY_ELEMENT = "element ha-ha-ha";
	private static final String DUMMY_ELEMENT_NAME = "element name";
	@Mock
	private TransactionsManager mockTransactionsManager;
	@Mock
	private TransactionValidator mockTransactionValidator;
	@Mock
	private AccountPropertyEditor mockAccountEditor;
	@Mock
	private Transaction mockTransaction;
	@Mock
	private BindingResult mockBindingResult;
	@Mock
	private SessionStatus mockSessionStatus;
	@Mock
	private ModelMap mockModel;
	@Mock
	private WebDataBinder mockDataBinder;
	@Mock
	private WebRequest mockWebRequest;
	@Mock
	private List<Account> mockAccounts;
	
	
	private TransactionsController underTest;

	@Before
	public void setUp() {
		underTest = new TransactionsController();
		
		MockitoAnnotations.initMocks(this);
		
		underTest.setTransactionsManager(mockTransactionsManager);
		underTest.setTransactionValidator(mockTransactionValidator);
		underTest.setAccountEditor(mockAccountEditor);
	}

	@Test
	public void shouldRedirectToTransactionsListPage() {
		ModelAndView actualModelAndView = underTest.showTransactionsByAccountId(MOCK_SELECTED_ACCOUNT_ID);
		Assert.assertEquals(TransactionsController.HOMEPAGE_PATH, actualModelAndView.getViewName());
	}

	@Test
	public void shouldAddTransactionsAttributeToResultModelAndView() {
		ModelMap model = new ModelMap();
		model.put(DUMMY_ELEMENT_NAME, DUMMY_ELEMENT);
		
		Mockito.when(mockTransactionsManager.addTransactionsByAccountIdToModel(MOCK_SELECTED_ACCOUNT_ID)).thenReturn(model);
		
		ModelAndView actualModelAndView = underTest.showTransactionsByAccountId(MOCK_SELECTED_ACCOUNT_ID);
		Map<String,Object> resultModel = actualModelAndView.getModel();
		
		Assert.assertTrue(resultModel.containsKey(DUMMY_ELEMENT_NAME));
		
	}
	@Test
	public void shouldRedirectToShowTransactionsServlet(){
		String resultPath  = underTest.addNewTransaction(mockTransaction, mockBindingResult,mockSessionStatus);
		
		Assert.assertEquals(AccountController.ACCOUNTS_VIEW_PAGE_PATH, resultPath);
	}
	@Test
	public void shouldValidateReceivedTransactionInstance(){
		underTest.addNewTransaction(mockTransaction, mockBindingResult,mockSessionStatus);
		
		Mockito.verify(mockTransactionValidator).validate(mockTransaction,mockBindingResult);
	}
	
	@Test
	public void shouldCallTransactionsManagerToAddNewTransactionIfBindingResultHasNoErrors(){
		Mockito.when(mockBindingResult.hasErrors()).thenReturn(false);
		
		underTest.addNewTransaction(mockTransaction, mockBindingResult,mockSessionStatus);
		
		Mockito.verify(mockTransactionsManager).saveNewTransaction(mockTransaction);
	}
	
	@Test
	public void shouldInitFormWithNewTransactionInstance(){
		String expectedPath = TransactionsController.NEW_TRANSACTION_PAGE_PATH;
		
		String actualPath = underTest.initNewTransactionForm(MOCK_SELECTED_ACCOUNT_ID, mockModel);
		
		Assert.assertEquals(expectedPath, actualPath);
	}	
	
	
	@Test
	public void shouldInitAccountsFieldInAccountEditors(){
		Mockito.when(mockWebRequest.getAttribute(AccountsManager.ACCOUNTS_ATTRIBUTE_NAME, RequestAttributes.SCOPE_SESSION)).thenReturn(mockAccounts);
		
		underTest.initBinder(mockDataBinder, mockWebRequest);
		Mockito.verify(mockAccountEditor).setAccounts(mockAccounts);
	}
	
	@Test
	public void shouldRegisterAccountEditor(){
		Mockito.when(mockWebRequest.getAttribute(AccountsManager.ACCOUNTS_ATTRIBUTE_NAME, RequestAttributes.SCOPE_SESSION)).thenReturn(mockAccounts);
		
		underTest.initBinder(mockDataBinder, mockWebRequest);	
		
		Mockito.verify(mockDataBinder).registerCustomEditor(Account.class, mockAccountEditor);
	}
}
