<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Opiniao do Morador</title>
	
    <!-- Bootstrap -->
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="styles/bootstrap-responsive.css" rel="stylesheet">
	
    <link href="styles/bootstrap.min.css" rel="stylesheet" media="screen">
  </head>
  <style type="text/css">
  	body {
		padding-top: 60px;
		padding-bottom: 40px;
	}
	.imovel-local {
		margin: 0;
	}
  </style>
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
						<li><a href="#about" class="btn btn-primary btn-large">Novo</a></li>
						<li><a href="#contact">Contact</a></li>
					</ul>
					<form id="searchForm" class="navbar-search pull-right" action="./usuarios/login" method="post">
						<input class="search-query" type="text" placeholder="Search"></input>
						<button type="submit" class="btn">Search</button>
					</form>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
	        <div class="span7">
	            <div class="hero-unit">
	                <h1>Lorem ips</h1>
	                <p>Proin sed nibh ut tortor vulputate rhoncus dignissim interdum orci, 
	                eu Aliquam pharetra felis in tellus varius mattis posuere nisl at tellus iaculis</p>
	            </div>
	        </div>
	        <div class="span5">
	            <ul class="thumbnails">
	                <li>
	                    <a href="#" class="thumbnail">
	                        <video width="320" height="240" controls>
	                            <source src="movie.mp4" type="video/mp4"></source>
	                            <source src="movie.ogg" type="video/ogg"></source>
	                            Your browser does not support the video tag.
							</video>
	                    </a>
	                </li>
	            </ul>
	        </div>
	    </div>
	    <div class="row">
	    
			<ul class="thumbnails">
		    	<li class="span6">
		        	<div class="thumbnail">
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
		            </div>
				</li>
				<li class="span6">
		        	<div class="thumbnail">
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
		            </div>
				</li>
				<li class="span6">
		        	<div class="thumbnail">
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
		            </div>
				</li>
				<li class="span6">
		        	<div class="thumbnail">
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
		            </div>
				</li>
		    </ul>
		</div>
		
   <footer class="footer">© Lorem Ips</footer>
</div> <!-- container -->
  
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript">
		$("#searchForm").submit(function() {
			var form = $(this);
			var formData = $(this).serialize();
			$.ajax({ 
				type: "POST", 
				url: form.attr("action"), 
				data: formData, 
				contentType: "application/x-www-form-urlencoded" 
			});
// 			return false;
		});
	</script>
  </body>
</html>