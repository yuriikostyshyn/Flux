<head>
<%@ include file="homepageInclude.jsp"%>
</head>
<div style="margin: 10px, 10px, 10px, 5px;">
<table width="100%" height="100%" >
	<tr>
		<td width="20%">
			<h4 align="center" style="margin: 10px;">Hello ${user.name} ${user.surname}!</h4>
		</td>
		<td align="center" valign="middle" width="80%">
			<h1 style="margin: 10px;">Welcome on FLUX Money Transaction System</h1>
		</td>
	</tr>
	<tr height="80%">
		<td width ="20%">
				<input type="button" value="Accounts" class="main-page-button"/>
				<br/>
				<input type="button" value="Transactions" class="main-page-button" onclick="showTransactions()"/>
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
		<td width="80%">
			<div style="width: 100%; height: 100%; background:url(layout/images/Penguins.jpg) no-repeat, yellow; background-size: 100% 100%;">
		