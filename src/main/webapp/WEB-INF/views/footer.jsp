<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<div class="container">
	<footer class="footer" style="margin : 2em 0;">© Lorem Ips</footer>
</div>

<c:url var="jsBootstrapMin" value="/js/bootstrap.min.js"></c:url>

<script src="http://code.jquery.com/jquery-latest.js" ></script>
<script src="${jsBootstrapMin}" ></script>
 <!-- 
<script type="text/javascript">
 		$("#searchForm").submit(function() {
 			var form = $(this);
 			var formData = $(this).serialize();
 			$.ajax({ 
 				type: "GET", 
 				url: form.attr("action"), 
 				data: formData, 
 				contentType: "application/x-www-form-urlencoded" 
 			});
 		});
</script>
 -->