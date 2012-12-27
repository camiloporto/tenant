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
	
  </style>
  <body>
	<div class="row" id="header" style="background: lightsteelblue">
		<div class="span2 offset1" id="header-logo">
			<h2>Aliquam</h2>
		</div>
		<div class="span11" id="header-main">
			<h4 class="span3">Neque porro quisquam</h4>
			<div class="pull-right">
				<form action="./usuarios/login" id="loginForm" class="navbar-form form-inline" method="POST">
					<input type="email" id="loginEmail" placeholder="Email" class="input-medium">
					<input type="password" id="loginPassword" placeholder="Password" class="input-medium">
					<input type="submit" class="btn" value="Sign In">
				</form>
			</div>
		</div>
	</div>
	
    <div class="conainer" style="margin : 0 auto; max-width : 970px;">	
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
			<!-- 
			<div class="span5" style="padding-top : 10px;">
					<video width="320" height="240" controls style="border : solid 1px;">
					</video><br>
			</div>
			 -->
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
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="js/bootstrap.min.js"></script>
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
// 			return false;
		});
	</script>
  </body>
</html>