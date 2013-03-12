<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url var="realEstateHome" value="/realestates" />
<style type="text/css">
body {
	padding-top: 60px;
	padding-bottom: 40px;
}

.imovel-local {
	margin: 0;
}
</style>
	<div class="container" id="divListaImoveis" style="margin-top : 3em;">
		<c:forEach items="${imoveis}" var="imovel">
			<div class="media well">
				<a class="pull-left" href="${realEstateHome}/${imovel.id}"> 
					<img 
						class="media-object" 
						src="http://res.cloudinary.com/inquilinus/image/upload/c_scale,h_96/v1362748860/sem_foto.png"></img>
				</a>
				<div class="media-body">
					<h3 class="media-heading">${imovel.tipo}</h3>
					<h4 class="imovel-local">${imovel.bairro}, ${imovel.cidade}-${imovel.estado}</h4>
					<span class="imovel-condominio">${imovel.rua}, ${imovel.complemento}</span><br />
					<!-- 
					<span class="avaliacao-qtde"><small>(47)</small></span> <span
						class="avaliacao">XXXXX</span><br />
						 -->
				</div>
			</div>
		</c:forEach>
	</div>
