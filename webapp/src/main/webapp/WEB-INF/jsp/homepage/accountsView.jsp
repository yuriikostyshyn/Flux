<%@ include file="homepageTop.jsp"%>
<div class="main-content-div filled-page-content" >
	<div style="height:600px;  overflow: auto;">
		<table  style="width:100%;text-align: center;">
			<thead>
				<tr>
				<th>Account number</th>
				<th>Bank code</th>
				<th>Currency</th>
				</tr>
			</thead>
		 	<c:forEach var="account" items="${accounts}">
		 		<tr>
		 			<td>${account.accountId}</td>
		 			<td>${account.bankId}</td>
		 			<td>${account.currency.longName}</td>
		 		</tr>
		 	</c:forEach>
		</table>
	</div>
	<div>
		<input type="button" value="Add new account" style="width:15%; height:40px;  margin-left: 10%; margin-bottom: 20px;"/>
	</div>
<%@ include file="homepageBottom.jsp"%>