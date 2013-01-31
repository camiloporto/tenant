<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tenant.com</title>
<!-- Bootstrap -->

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="styles/bootstrap-responsive.css" rel="stylesheet">
<link href="styles/bootstrap.min.css" rel="stylesheet" media="screen">
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
	
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> 
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</a> 
				<a class="brand" href="#">Lorem Ips</a>
				<div class="nav-collapse collapse">
					<ul class="nav">
						<li class="active"><a href="#">Home</a></li>
						<li><a href="#about">Novo</a></li>
						<li><a href="#contact">Contact</a></li>
					</ul>
					<form class="navbar-search pull-right">
						<input class="search-query" type="text" placeholder="Search"></input>
						<button type="submit" class="btn">Search</button>
					</form>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>
	<div class="container" id="divListaImoveis">
		<c:forEach items="${imoveis}" var="imovel">
			<div class="media well">
				<a class="pull-left" href="realestates/${imovel.id}"> 
					<img class="media-object" src="#" width="96" height="96"></img>
				</a>
				<div class="media-body">
					<h3 class="media-heading">${imovel.tipo}</h3>
					<h4 class="imovel-local">${imovel.rua}, ${imovel.cidade}-${imovel.estado}</h4>
					<span class="imovel-condominio">${imovel.complemento}</span><br />
					<span class="avaliacao-qtde"><small>(47)</small></span> <span
						class="avaliacao">XXXXX</span><br />
				</div>
			</div>
		</c:forEach>
		<!-- 
		<div class="media well">
			<a class="pull-left" href="#"> <img class="media-object" src="#"
				width="96" height="96"></img>
			</a>
			<div class="media-body">
				<h3 class="media-heading">Apartamento</h3>
				<h4 class="imovel-local">Consectetur Adipisicing, Natal-RN</h4>
				<span class="imovel-condominio">Sed ut perspiciatis unde</span><br />
				<span class="avaliacao-qtde"><small>(47)</small></span> <span
					class="avaliacao">XXXXX</span><br />
			</div>
		</div>
		<div class="media well">
			<a class="pull-left" href="#"> <img class="media-object" src="#"
				width="96" height="96"></img>
			</a>
			<div class="media-body">
				<h3 class="media-heading">Apartamento</h3>
				<h4 class="imovel-local">Consectetur Adipisicing, Natal-RN</h4>
				<span class="imovel-condominio">Sed ut perspiciatis unde</span><br />
				<span class="avaliacao-qtde"><small>(47)</small></span> <span
					class="avaliacao">XXXXX</span><br />
			</div>
		</div>
		 -->
		<div class="footer">Lorem Ips (C)</div>
	</div>
	<!-- container -->
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>