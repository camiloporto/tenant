<%@ page contentType="text/html; charset=UTF-8" %>
<script type="text/javascript">
		// Carrocel de fotos do imovel
		$('#myCarousel').carousel({
			interval: 3000
		});
		
		//Esconder/Mostrar div de upload de fotos
		$('#lnkUploadMedia').click(function(evt){
		    $('#uploadDiv').toggle('slow');
		});
		
		// Script para tratar envio de comentario do imovel
		$('#commentForm').submit(function(evt){
			alert("Seu comentário foi enviado para avaliação. Obrigado por contribuir");
			$('textarea', this).val('');
			return false;
		});
		
</script>