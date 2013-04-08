<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="navbar navbar-inverse navbar-fixed-top" id="navbar">
	<c:url var="home" value="/" />
		<div class="navbar-inner">
			<div class="container">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> 
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</a> 
				<a class="brand" href="${home}" style="padding : 0">
					<img alt="Inquilinis.com" id="logo" title="Ajudando você a morar melhor"
						src="http://res.cloudinary.com/inquilinus/image/upload/v1362749559/Logo_1_Original_331x75_qosj3p.png"/>
				</a>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>