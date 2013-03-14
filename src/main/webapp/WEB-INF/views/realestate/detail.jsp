<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style type="text/css">
.imovel-local {
	margin: 0;
}
#imovel-comments {
	margin: 3em 0;
}
#myCarousel {
	margin: 5em 0;
}
.carousel img {
/*     width : 430px; */
/*     height : 300px; */
    margin : 0 auto;
}
.thumbup {
	width: 35px;
	height: 35px;
	display: inline;
}
#lnkEnvioFoto {
    font-size : larger;
}
#upload {
	margin : 1em 0;
}
#uploadDiv {
    width : 80%;
    background : lightgrey;
    border : dashed 1px black;
    margin : 1em 0;
    padding : 1em;
    display : none;
}
.noPhoto {
    width : 80%;
    height: 200px;
    background : url('http://res.cloudinary.com/inquilinus/image/upload/v1362748860/sem_foto.png') no-repeat center;
    border : 2px dashed grey;
    margin: 0 auto;
}
.capitalize {
	text-transform: capitalize;
}
.uppercase {
	text-transform: uppercase;
}
.row {
	margin : 1em auto;
}
#imovel-info h4 {
	margin : 0.5em 0;
}
</style>
	<div class="container" style="margin-top : 2em;">
		<div class="row">
		<div id="imovel-info" class="span12">
			<h3 class="capitalize">${imovel.tipo}</h3>
			<h4 class="imovel-local">
				<span class="capitalize">${imovel.bairro}</span>, 
				<span class="capitalize">${imovel.cidade}</span>-<span class="uppercase">${imovel.estado}</span>
			</h4>
			<span>${imovel.rua}, ${imovel.complemento}</span><br />
			<!-- 
			<span class="avaliacao-qtde"><small>(47)</small></span> 
			<img src="/Tenant/images/thumbup.gif" class="thumbup"></img>
			 -->
			<!-- 
		        <div>Apartamento em Lagoa Nova, Natal-RN</div>
		        <div>Residencial LifeStyle</div>
		        <div>(47) XXXX</div>
	         -->
	    </div>
	    </div>
	    <div class="row" style="border-bottom: solid 1px lightgray; border-top: solid 1px lightgray">
	    <c:if test="${empty medias}">
	    	<div class="noPhoto span12"></div>
	    </c:if>
	    <c:if test="${not empty medias}">
			<div id="imovel-media" class="span12">
				<div id="myCarousel" class="carousel slide">
					<!-- Carousel items -->
					<div class="carousel-inner">
						 <c:forEach items="${medias}" var="mediaURL">
						 	<div class="item">
								<img
									src="${mediaURL}"
									alt=""></img>
							</div>
						 </c:forEach>
					</div>
					<!-- Carousel nav -->
					<a class="carousel-control left" href="#myCarousel"
						data-slide="prev">&lsaquo;</a> <a class="carousel-control right"
						href="#myCarousel" data-slide="next">&rsaquo;</a>
				</div>
			</div>
		</c:if>
		</div> <!-- end row -->
		
		<div id="upload" class="row">
			<a href="#" id="lnkUploadMedia">Tenho uma foto deste imóvel e gostaria de compartilhar</a>
			<div id="uploadDiv">
	        	<span>No momento o envio de fotos não está disponível. Você pode nos enviar sua foto através do email <a href="#">camiloporto@gmail.com</a></span>
	        </div>
		</div>
		<div id="imovel-comments" class="container">
			<h3>Comentarios</h3>
			<div id="commentarioFormDiv" class="row">
				<c:url var="commentFormAction" value="/realestates/comment" />
				<form action="${commentFormAction}" method="POST" id="commentForm">
					<label for="comment">Envie seu comentário sobre este imóvel</label>
					<textarea cols="20" rows="8" name="comment" id="comment" style="display: block;" class="span12"></textarea>
					<input type="hidden" value="${imovel.id}" name="imovelId">
					<input type="submit" value="Enviar" class="btn btn-primary" />
				</form>
			</div>
			<div class="media" class="row">
				<a class="pull-left"> <img
					src="http://2.gravatar.com/avatar/22bd03ace6f176bfe0c593650bcf45d8?s=48&d=&r=G"></img>
				</a>
				<div class="media-body">
					<h4>Donec iaculis</h4>
					<p>Etiam eu turpis nisl. Maecenas ipsum nisi, mollis a bibendum
						ornare, eleifend a magna. Nullam ac ultrices orci. Suspendisse
						feugiat vulputate nisi, quis blandit augue dignissim id. Maecenas
						laoreet leo non velit elementum accumsan. Curabitur sagittis
						adipiscing nibh sit amet cursus.</p>
				</div>
			</div>
			<div class="media" class="row">
				<a class="pull-left"> <img
					src="https://lh5.googleusercontent.com/-govT1DrCf6E/AAAAAAAAAAI/AAAAAAAAAAA/BPlkNKv4v2o/s48-c-k/photo.jpg"></img>
				</a>
				<div class="media-body">
					<h4>Aliquam vulputate</h4>
					<p>Curabitur iaculis elit turpis, ut ultrices metus. Integer
						lacus urna, laoreet vitae hendrerit nec, vehicula ut urna.
						Suspendisse quis elit eros, quis hendrerit elit. Nam eu libero
						quis magna molestie placerat vitae eget ante. Aenean vitae laoreet
						urna.</p>
				</div>
			</div>
			<div class="media" class="row">
				<a class="pull-left"> <img
					src="http://gospelmais.com.br/wp-content/uploads/avatars/44893/4ef460c284ea678dfcd9a47eb7b7a254-bpthumb.jpg"></img>
				</a>
				<div class="media-body">
					<h4>Praesent varius</h4>
					<p>Maecenas ligula turpis, dignissim ac cursus a, auctor id
						dui. Cras aliquam bibendum mi sed placerat. Cum sociis natoque
						penatibus et magnis dis parturient montes, nascetur ridiculus mus.
						Duis nec augue ut quam sodales varius. Sed ultrices mauris
						vehicula dui imperdiet mollis.</p>
				</div>
			</div>
		</div>
	</div>