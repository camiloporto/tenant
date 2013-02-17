<style type="text/css">
body {
	padding-top: 60px;
	padding-bottom: 40px;
}

.imovel-local {
	margin: 0;
}
</style>
	<div class="container" id="divListaImoveis" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:fn="http://java.sun.com/jsp/jstl/functions" xmlns:c="http://java.sun.com/jsp/jstl/core" xmlns:spring="http://www.springframework.org/tags" version="2.0">
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
	</div>
