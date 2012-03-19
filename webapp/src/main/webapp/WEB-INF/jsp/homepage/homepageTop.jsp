<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<head>
<%@ include file="homepageInclude.jsp"%>
</head>
<div style="margin: 10px, 10px, 10px, 5px;">
<table style="width:100%; height:100%" >
	<tr>
		<td width="20%">
			<h4 align="center" style="margin: 10px;">Hello ${user.name} ${user.surname}!</h4>
		</td>
		<td align="center" valign="middle" style="width:80%;">
			<h1 style="margin: 10px;">Welcome on FLUX Money Transaction System</h1>
		</td>
	</tr>
	<tr style="height:80%">
		<td width ="20%">
				<input type="button" value="Accounts" class="main-page-button" onclick="showPage('showAccounts.do')"/>
				<br/>
				<input type="button" value="Settings" class="main-page-button"/>
				<br/>
				<input type="button" value="Help" class="main-page-button"/>
				<br/>
				<input type="button" value="Exit" id="logout-button" class="main-page-button"/>	
				<div id="dialog">
					<p class="logout-dialog-paragraph">Are you really going to leave FLUX?</p>
				</div>				
		</td>
		<td style="width:80%;">
		