<%@ page contentType="text/html; charset=UTF-8" %>
<script type="text/javascript">
		$('#myCarousel').carousel({
			interval: 3000
		});
		$('#lnkUploadMedia').click(function(evt){
		    $('#uploadDiv').toggle('slow');
		});
</script>