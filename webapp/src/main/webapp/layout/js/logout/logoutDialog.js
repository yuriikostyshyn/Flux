$(function() {	$('#dialog').dialog({		modal : true,		autoOpen : false,		resizable: false,		width: 400,		height: 150,		buttons : {			"Cancel" : function() {				$(this).dialog("close");			},			"Ok" : function() {				window.location = "logout.do";			}		}	});	$('#logout-button').click(function() {		$('#dialog').dialog("open");	});});