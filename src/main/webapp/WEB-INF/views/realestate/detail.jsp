<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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
.carousel img {
    width : 430px;
    height : 300px;
    margin : 0 auto;
}
.thumbup {
	width: 35px;
	height: 35px;
	display: inline;
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
	<div class="container">
		<div id="imovel-info">
			<h3>${imovel.tipo}</h3>
			<h4 class="imovel-local">${imovel.bairro}, ${imovel.cidade}-${imovel.estado}</h4>
			<span class="imovel-condominio">${imovel.rua}</span><br />
			<span class="avaliacao-qtde"><small>(47)</small></span> 
			<img src="images/thumbup.gif" class="thumbup"></img>
			<!-- 
		        <div>Apartamento em Lagoa Nova, Natal-RN</div>
		        <div>Residencial LifeStyle</div>
		        <div>(47) XXXX</div>
	         -->
	    </div>
		<div id="imovel-media">
			<div id="myCarousel" class="carousel slide">
				<!-- Carousel items -->
				<div class="carousel-inner">
					<div class="active item">
						<img
							src="http://www.alugarcasa.org/wp-content/uploads/2012/09/Alugar-apartamento-Dicas-Como-proceder.jpg"
							alt=""></img>
					</div>
					<div class="item">
						<img
							src="http://construdeia.com/wp-content/gallery/apartamento/apartamento-4.jpg"
							alt=""></img>
					</div>
					<div class="item">
						<img
							src="http://static.assimsefaz.com.br/images/3/71/994/207134/2/img.jpg"
							alt=""></img>
					</div>
				</div>
				<!-- Carousel nav -->
				<a class="carousel-control left" href="#myCarousel"
					data-slide="prev">&lsaquo;</a> <a class="carousel-control right"
					href="#myCarousel" data-slide="next">&rsaquo;</a>
			</div>
		</div>
		<div id="imovel-comments">
			<h3>Comentarios</h3>
			<div class="media">
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
			<div class="media">
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
			<div class="media">
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
		<footer class="footer" style="margin : 2em 0;">© Lorem Ips</footer>
	</div>
	<!-- container -->
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$('#myCarousel').carousel({
			interval: 3000
		});
	</script>
</body>
</html>