<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url var="searchFormAction" value="/realestates" />
<style>
	
</style>
	<div class="container" style="margin-top : 3em">
<!-- 		<div class="row"> -->
<!-- 	        <div class="span12"> -->
	            <div class="hero-unit">
	                <h1>Inquilinus</h1>
	                <p>O local ideal para você compartilhar suas experiências relacionadas a moradia, imóveis, condomínios, imobiliárias, contrutoras, etc</p>
	                <div>
	                	<div class="span1"></div>
	                	<form id="searchForm" class="form-search" action="${searchFormAction}" method="get">
	                		<div class="input-append">
	                			<input 
									class="search-query span6" style="height : 34px"
									type="search" 
									name="q" id="q"
									title="Digite o nome de uma cidade, bairro, rua, condomínio, construtora, imobiliária..." 
									placeholder="Pesquisar"></input>
								<button type="submit" class="btn btn-primary btn-large">Procurar</button>
	                		</div>
						</form>
					</div>
	            </div>
	    <div class="row">
	    	<div class="span4">
				<h2>Pesquise...</h2>
				<!-- 
				<p>Imóveis em que você esteja interessado em morar ou reputação de construtoras ou imobiliárias com quem você vai se relacionar</p>
				<p></p>
				 -->
				<ul>
					<li>O que usuários falam sobre suas experiências em imóveis com que tiveram contato</li>
					<li>Quais Imóveis, Construtoras ou Imobiliárias possuem boas recomendações</li>
					<li>Se o imóvel desejado é bem recomendado pelos usuários</li>
			    </ul>
			</div>
	    	<div class="span4">
				<h2>Compartilhe...</h2>
				<!-- 
				<p>
					<strong>Uma experiência que você viveu com um imóvel, imobiliária ou construtora.</strong>
					Envie seu feedback sobre um Imóvel que você conheceu ou morou, uma imobiliária ou construtora com que você se relacionou.
					O Imóvel tinha problemas escondidos? A construtora ou imobiliária lhe atendeu bem? Você recomendaria para outras pessoas?
				</p>
				 -->
				<ul>
					<li>Uma experiência que você passou com um imóvel, imobiliária ou construtora</li>
					<li>Sua Avaliação sobre imóveis, imobiliárias, construtoras</li>
					<li>Reclamações e queixas sobre imóveis, imobiliárias e construtoras</li>
			    </ul>
			    <a href="http://camiloporto.wufoo.com/forms/z7x3p3/" class="btn" target="_blank">Compartilhe Agora!</a>
			</div>
	    	<div class="span4">
				<h2>Espalhe...</h2>
				<img alt="Facebook" src="http://res.cloudinary.com/inquilinus/image/upload/c_scale,w_64/v1363091908/http_www_curitiba_br_emb_japan_go_jp_imagem_facebook_logo_y7lgdp.png">
				<img alt="Twitter" src="http://res.cloudinary.com/inquilinus/image/upload/c_scale,w_64/v1363092921/http_www_arautos_org_images_comunidades_twitter_button_nqulmi.png">
				<img alt="Google+" src="http://res.cloudinary.com/inquilinus/image/upload/c_scale,w_64/v1363094801/http_www_pedroquintanilha_com_br_wp_content_uploads_2013_01_GooglePlus_Logo_02_hhdtfr.png">
			</div>
	    </div>
</div> <!-- container -->
  	
    
  