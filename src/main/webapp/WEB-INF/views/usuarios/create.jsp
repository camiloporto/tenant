<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tenant.com > Usuarios</title>
</head>
<body>
	<section>
		<header>
			Nova Conta
		</header>
		<article>
			<div id="pageDescription">
				Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse auctor
			</div>
			<div>
				<form action="./usuarios" id="contaForm" method="post">
					<label for="email">Email:</label><br>
					<input type="text" id="email" name="email" /><br>
					<label for="senha">Senha:</label><br>
					<input type="password" id="password" name="password" /><br>
					<label for="repitaSenha">Confirme a senha:</label><br>
					<input type="password" id="repitaSenha" name="repitaSenha" /><br>
					<input type="submit" value="Me Cadastre Grátis!" id="createUserButton">
				</form>
			</div>
			<div id="status" style="display: none">
				Sucesso!
			</div>
		</article>
		<footer>
			Copyright
		</footer>
	</section>
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js" ></script>
	<script type="text/javascript">
		$("#contaForm").submit(function() {
			var form = $(this);
			var formData = $(this).serialize();
			$.ajax({ 
				type: "POST", 
				url: form.attr("action"), 
				data: formData, 
				contentType: "application/x-www-form-urlencoded", 
				dataType: "text", 
				success: function(data, textStatus, jqXHR) { 
					window.location.replace("./realestates");
				}, 
				error: function(jqXHR, textStatus, errorThrown) { 
					console.log(textStatus); 
				}
			});
			return false;
		});
	</script>
</body>
</html>