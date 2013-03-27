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
.capitalize {
	text-transform: capitalize;
}
.uppercase {
	text-transform: uppercase;
}
</style>
	<div class="container" id="divListaImoveis" style="margin-top : 3em;">
		<c:if test="${empty imoveis}">
			<div class="hero-unit">
			    <h3>Hora de compartilhar...</h3>
			    <p>Não encontramos items relacionado a sua pesquisa.</p>
			    <p>Por que você não cadastra o que está procurando (Imóvel, Construtora ou Imobiliária) 
			    	para que outros usuários enviem suas experiências e comentários sobre o seu item? </p>
			    <p><strong>O Cadastro é Gratuito e leva menos de 1 minuto!</strong></p>
			    <p>
			        <a href="#newImovelDiv" data-toggle="modal" role="button" class="btn btn-primary btn-large">Quero Cadastrar »</a>
			    </p>
			</div>
		</c:if>
		<c:forEach items="${imoveis}" var="imovel">
			<div class="media well">
				<a class="pull-left" href="${realEstateHome}/${imovel.id}"> 
					<img 
						class="media-object" 
						src="http://res.cloudinary.com/inquilinus/image/upload/c_scale,h_96/v1362748860/sem_foto.png"></img>
				</a>
				<div class="media-body">
					<h3 class="media-heading capitalize">${imovel.tipo}</h3>
					<h4 class="imovel-local">
						<span class="capitalize">${imovel.bairro}</span>, 
						<span class="capitalize">${imovel.cidade}</span>-<span class="uppercase">${imovel.estado}</span>
					</h4>
					<span class="imovel-condominio capitalize">${imovel.rua}, ${imovel.complemento}</span><br />
					<!-- 
					<span class="avaliacao-qtde"><small>(47)</small></span> <span
						class="avaliacao">XXXXX</span><br />
						 -->
				</div>
			</div>
		</c:forEach>
	</div>
