<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="navbar navbar-inverse navbar-fixed-top" id="navbar">
	<c:url var="searchFormAction" value="/realestates" />
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
				<div class="nav-collapse collapse">
					<div class="pull-right">
						 <a style="margin: 0.5em 1em 0em 0;"
						 	onclick="_gaq.push(['_trackEvent', 'LinksAndButtons', 'Click', 'HeaderNewImovelButton']);"
						 	href="#newImovelDiv" class="btn" data-toggle="modal" role="button">Novo Cadastro</a>
	
						<form id="searchForm" class="navbar-search pull-right form-search" action="${searchFormAction}" method="get">
							<div class="input-append">
								<input class="search-query" type="text" name="q" id="q" placeholder="Pesquisa..."></input>
								<button type="submit" class="btn btn-primary">Procurar</button>
							</div>
						</form>
					</div>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>