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
						<li><a href="#about">Novo</a></li>
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
		
   <div class="footer">
     Lorem Ips (C)
  </div>
</div> <!-- container -->
  <!-- 
	<div class="row" id="header" style="background: lightsteelblue">
		<div class="span2 offset1" id="header-logo">
			<h2>Aliquam</h2>
		</div>
		<div class="span10" id="header-main">
			<h4 class="span3">Neque porro quisquam</h4>
		</div>
	</div>
	
    <div class="container" style="margin : 0 auto; max-width : 970px;">	
		<div id="content" class="row" style="margin : 30px 0;">
			<div class="span8" style="padding : 10px 0;">
				<ul>
					<li style="margin : 1.5em 0;">Proin sed nibh ut tortor vulputate rhoncus dignissim interdum orci, eu.</li>
					<li style="margin : 1.5em 0;">Aliquam pharetra felis in tellus varius mattis posuere nisl at tellus iaculis.</li>
					<li style="margin : 1.5em 0;">Integer posuere nisl at tellus iaculis molestie velit id nunc elementum.</li>
					<li style="margin : 1.5em 0;">Morbi dignissim interdum orci, eu pharetra pharetra felis in tellus varius.</li>
					<li style="margin : 1.5em 0;">Curabitur molestie velit id nunc elementum.</li>
				</ul>
			</div>
			-->
			<!-- 
			<div class="span5" style="padding-top : 10px;">
					<video width="320" height="240" controls style="border : solid 1px;">
					</video><br>
			</div>
			 -->
			 <!-- 
			<div class="span4" style="background : lightsteelblue; border-radius : 1em;">
				<div style="margin : 0 30px;">
					<form id="newUserForm">
						<fieldset>
							<legend>Register Free</legend>
							<label for="email">Email</label>
							<input type="email" id="email" placeholder="enter your email"></input>
							<label for="password">Password</label>
							<input type="password" id="password" placeholder="enter your password"></input><br>
							<label for="confirmPassword">Confirm Password</label>
							<input type="password" id="confirmPassword" placeholder="confirm password"></input><br>
							<input type="submit" class="btn btn-primary" value="Sign In">
						<fieldset>
					</form>
				</div>
			</div>
			
		</div>
    </div>
     -->
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