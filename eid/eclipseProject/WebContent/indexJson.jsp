<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://www.java.com/js/deployJava.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$.getJSON( "identity.js", function( data ) {
		  var items = [];
		  $.each( data, function( key, val ) {
			  $.each( val, function( k, v ) {
				    items.push( "<li>" + k +" = " + v + "</li>" );
			  });
		  });
		  $( "<ul/>", {
		    "class": "my-new-list",
		    html: items.join( "" )
		  }).appendTo( "body" );
	});	
});

</script>
</head>
<body>
<%  
	out.println("session id "+session.getId()+"<br>");  
%> 
<%-- <script>
	var attributes = {
		code : 'be.fedict.eid.applet.Applet.class',
		archive : 'eid-applet-package-1.1.3.jar',
		width : 400,
		height : 300
	};
	var parameters = {
		TargetPage : 'sample',
		AppletService : 'applet-service;jsessionid=<%=session.getId()%>',
		BackgroundColor : '#ffffff'
	};
	var version = '1.6';
	deployJava.runApplet(attributes, parameters, version);
</script> --%>
</body>
</html>