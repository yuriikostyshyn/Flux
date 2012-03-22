<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="homepageTop.jsp"%>	
<div class="main-content-div filled-page-content" >
	<div style="height:600px;  overflow: auto;">
		<form:form method="POST" commandName="newTransaction" action="newTransaction.do">
			<form:errors path="*" cssClass="form-error" element="div"/>
			<table>
				<tr>
					<td>Sender account id: </td>
					<td>	${newTransaction.accountFrom.accountId}		</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td>Receiver account id: </td>
					<td>	<form:select items="${accounts}" path="accountTo"/>	</td>
				</tr>
				<tr>
					<td>	<form:errors path="accountTo" cssClass="form-error"/>		</td>
					<td></td>
				</tr>
				
				<tr>
					<td>Money amount: </td>
					<td>	<form:input path="amount" style="width: 35px;"/>	</td>
				</tr>
				<tr>
					<td>	<form:errors path="amount" cssClass="form-error"/>	</td>
					<td></td>
				</tr>
				<tr>
					<td colspan="3">	<input type="submit" value="Save"/>	</td>
				</tr>
			</table>
		</form:form>
	</div>
</div>
<%@ include file="homepageBottom.jsp"%>