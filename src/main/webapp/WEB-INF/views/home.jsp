<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inquilino.com</title>
</head>
<body>
	<section>
		<header>
			Inquilino.com
		</header>
		<article>
			<div id="pageDescription">
				Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse auctor
			</div>
			<div>
				<form action="./usuarios/login" id="loginForm" method="POST">
					<label for="email">Email:</label><br>
					<input type="text" id="email" name="email" /><br>
					<label for="senha">Senha:</label><br>
					<input type="password" id="senha" name="senha" /><br>
					<input type="submit" value="Entrar">
				</form>
			</div>
			<a href="./usuarios">É meu primeiro acesso</a>
		</article>
		<footer>
			Copyright
		</footer>
	</section>
	<script type="text/javascript">
		$("#loginForm").submit(function() {
			var form = $(this);
			var formData = $(this).serialize();
			$.ajax({ 
				type: "POST", 
				url: form.attr("action"), 
				data: formData, 
				contentType: "application/x-www-form-urlencoded" 
			});
			return false;
		});
	</script>
</body>
</html>