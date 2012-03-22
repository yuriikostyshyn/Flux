package com.flux.web.controller.transaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.flux.manager.AccountManager;
import com.flux.manager.TransactionsManager;
import com.flux.web.controller.account.AccountController;
import com.flux.web.util.propertyeditor.AccountPropertyEditor;
import com.flux.web.util.validator.TransactionValidator;

public class TransactionListControllerTest {

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
	private HttpServletRequest mockRequest;
	@Mock
	private HttpServletResponse mockResponse;
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
	
	
	private TransactionListController underTest;

	@Before
	public void setUp() {
		underTest = new TransactionListController();
		
		MockitoAnnotations.initMocks(this);
		
		underTest.setTransactionsManager(mockTransactionsManager);
		underTest.setTransactionValidator(mockTransactionValidator);
		underTest.setAccountEditor(mockAccountEditor);
	}

	@Test
	public void shouldRedirectToTransactionsListPage() {
		ModelAndView resultModelAndView = underTest.showTransactionsByAccountId(mockRequest, mockResponse);
		Assert.assertEquals(TransactionListController.HOMEPAGE_PATH, resultModelAndView.getViewName());
	}

	@Test
	public void shouldAddTransactionsAttributeToResultModelAndView() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put(DUMMY_ELEMENT_NAME, DUMMY_ELEMENT);
		
		Mockito.when(mockTransactionsManager.addTransactionsByAccountIdToModel(mockRequest)).thenReturn(model);
		
		ModelAndView resultModelAndView = underTest.showTransactionsByAccountId(mockRequest, mockResponse);
		Map<String,Object> resultModel = resultModelAndView.getModel();
		
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
		ModelAndView resultModelAndView = underTest.initNewTransactionForm(MOCK_SELECTED_ACCOUNT_ID, mockModel);
		String resultPath = resultModelAndView.getViewName();
		Assert.assertEquals(TransactionListController.NEW_TRANSACTION_PAGE_PATH, resultPath);
	}	
	
	
	@Test
	public void shouldInitAccountsFieldInAccountEditors(){
		Mockito.when(mockWebRequest.getAttribute(AccountManager.ACCOUNTS_ATTRIBUTE_NAME, RequestAttributes.SCOPE_SESSION)).thenReturn(mockAccounts);
		
		underTest.initBinder(mockDataBinder, mockWebRequest);
		Mockito.verify(mockAccountEditor).setAccounts(mockAccounts);
	}
	
	@Test
	public void shouldRegisterAccountEditor(){
		Mockito.when(mockWebRequest.getAttribute(AccountManager.ACCOUNTS_ATTRIBUTE_NAME, RequestAttributes.SCOPE_SESSION)).thenReturn(mockAccounts);
		
		underTest.initBinder(mockDataBinder, mockWebRequest);	
		
		Mockito.verify(mockDataBinder).registerCustomEditor(Account.class, mockAccountEditor);
	}
}
