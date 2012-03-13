<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="homepageTop.jsp"%>
<div class="main-content-div filled-page-content" >
		<form action="getAccountReview.do" name="accountListViewForm" method="get">
			<div style="height:600px;  overflow: auto;">
				<table  style="width:100%;text-align: center;">
					<thead>
						<tr>
						<th></th>
						<th>Account number</th>
						<th>Bank code</th>
						<th>Currency</th>
						</tr>
					</thead>
				 	<c:forEach var="account" items="${accounts}">
				 		<tr>
				 			<td>
				 				<input type="radio"  name="selectedAccountId" value="${account.accountId}" />
				 			</td>
				 			<td>${account.accountId}</td>
				 			<td>${account.bankId}</td>
				 			<td>${account.currency.longName}</td>
				 		</tr>
				 	</c:forEach>
				</table>
			</div>
			<c:if test="${selectedAccount != null}">
				<p style="margin-left:60px;">Account ${selectedAccount.accountId} state: ${selectedAccount.amount}</p>
			</c:if>
			<div>
					<input type="button" value="Add new account" onclick="showPage('newAccount.do')" class="account-list-button"/>
					<input type="submit" value="Get review" class="account-list-button"/>
			</div>
		</form>
<%@ include file="homepageBottom.jsp"%>