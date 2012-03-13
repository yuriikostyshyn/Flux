<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="homepageTop.jsp"%>	
<div class="main-content-div filled-page-content" >
	<div style="height:600px;  overflow: auto;">
		<form:form method="POST" commandName="newAccount" action="newAccount.do">
			<form:errors path="*" cssClass="form-error" element="div"/>
			<table>
				<tr>
					<td>Account id: </td>
					<td>	<form:input path="accountId"/>		</td>
				</tr>
				<tr>
					<td></td>
					<td>	<form:errors path="accountId" cssClass="form-error"/>		</td>
				</tr>
				<tr>
					<td>Bank id: </td>
					<td>	<form:input path="bankId"/>		</td>
				</tr>
				<tr>
					<td></td>
					<td>	<form:errors path="bankId" cssClass="form-error"/>	</td>
				</tr>
				<tr>
					<td>Currency: </td>
					<td>	<form:select items="${currencies}" path="currency"/>	</td>
				</tr>
				<tr>
					<td></td>
					<td>	<form:errors path="currency" cssClass="form-error"/>	</td>
				</tr>
				<tr>
					<td>Security key: </td>
					<td>	<form:password  path="securityKey"/>	</td>
				</tr>
				<tr>
					<td></td>
					<td>	<form:errors path="securityKey" cssClass="form-error"/>	</td>
				</tr>
				<tr>
					<td colspan="3">	<input type="submit" value="Save"/>	</td>
				</tr>
			</table>
		</form:form>
	</div>
</div>
<%@ include file="homepageBottom.jsp"%>