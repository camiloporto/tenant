<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML>
<html>
	<c:url var="cssBootstrapMinResponsive" value="/styles/bootstrap-responsive.css" /> 
	<c:url var="cssBootstrapMin" value="/styles/bootstrap.min.css" />
<head>
	<!-- Bootstrap -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link href="${cssBootstrapMinResponsive}" rel="stylesheet" />
		
	<link href="${cssBootstrapMin}" rel="stylesheet" media="screen" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Inquilinus.com - Ajudando Você a Morar Melhor</title>
	<!-- Estilo da pagina principal -->
	<style type="text/css">
	  	body {
			padding-top: 60px;
			padding-bottom: 40px;
		}
		.imovel-local {
			margin: 0;
		}
  </style>
</head>
<body>
	
	<tiles:insertAttribute name="header" ignore="true"/>
	<tiles:insertAttribute name="body" ignore="true"/>
	<tiles:insertAttribute name="footer" ignore="true"/>
	<tiles:insertAttribute name="customJS" ignore="true"/>
	<script type="text/javascript">

	  var _gaq = _gaq || [];
	  _gaq.push(['_setAccount', 'UA-27509209-3']);
	  _gaq.push(['_trackPageview']);

	  (function() {
	    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
	    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
	    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
	  })();

	</script>
</body>
</html>