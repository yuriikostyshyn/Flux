<head>
<%@ include file="homePageInclude.jsp"%>
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
			<form style="height:80%">
				<input type="submit" value="Accounts" class="main-page-button"/>
				<br/>
				<input type="submit" value="Settings" class="main-page-button"/>
				<br/>
				<input type="submit" value="Help" class="main-page-button"/>
				<br/>
				<input type="button" value="Exit" id="logout-button" class="main-page-button"/>	
				<div id="dialog">
					<p class="logout-dialog-paragraph">Are you really going to leave FLUX?</p>
				</div>				
			</form>
		</td>
		<td width="80%">
			<img src="layout/images/Penguins.jpg" alt="penguins" class="main-page-image"/>
		</td>
	</tr>
</table>
</div>
