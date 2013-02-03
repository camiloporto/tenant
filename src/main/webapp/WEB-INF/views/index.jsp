	<div class="container" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:fn="http://java.sun.com/jsp/jstl/functions" xmlns:c="http://java.sun.com/jsp/jstl/core" xmlns:spring="http://www.springframework.org/tags" version="2.0">
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
		
</div> <!-- container -->
  	
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript">
		$("#searchForm").submit(function() {
			alert("Opa");
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
  