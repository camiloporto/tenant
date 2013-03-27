<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<div class="container">
	<footer class="footer" style="margin : 2em 0; padding : 2em 0; border-top : solid 1px lightgray;">Inquilinus.com © 2013</footer>
</div>

<!-- Modal com Formulario de Novo Cadastro -->
<div id="newImovelDiv" class="modal hide fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <form id="newImovelForm" class="form-horizontal">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="myModalLabel">Novo Cadastro</h3>
        <small>Preencha as informações abaixo sobre o novo item (Imóvel, Construtora, Imobiliária) que deseja cadastrar</small>
    </div>
    <div class="modal-body">
        <div class="control-group">
            <label class="control-label" for="tipo">Categoria</label>
            <div class="controls">
                <select id="tipo" name="tipo">
                    <option value="Selecione aqui" selected="selected">Selecione aqui</option>
                    <option value="Apartamento">Apartamento</option>
                    <option value="Casa">Casa</option>
                    <option value="Condomínio">Condomínio</option>
                    <option value="Imobiliária">Imobiliária</option>
                    <option value="Construtora">Construtora</option>
                    <option value="Outros">Outros</option>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="estado">Estado</label>
            <div class="controls">
                <select id="estado" name="estado">
                    <option value="">Selecione o Estado</option>
                    <option value="rn">RN</option>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="cidade">Cidade</label>
            <div class="controls">
                <input type="text" id="cidade" name="cidade" placeholder="Informe a cidade"></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="bairro">Bairro</label>
            <div class="controls">
                <input type="text" id="bairro" name="bairro" placeholder="Informe o Bairro" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="rua">Rua</label>
            <div class="controls">
                <input type="text" id="rua" name="rua" placeholder="Informe a Rua"></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="complemento">Complemento</label>
            <div class="controls">
                <input type="text" id="complemento" name="complemento"></input>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="comentario">Comentários</label>
            <div class="controls">
                <textarea rows="4" type="text" id="comentario" name="comentario"></textarea> <span class="help-block">Escreva aqui seu comentário ou dúvida sobre o item que está cadastrando</span>

            </div>
        </div>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn" data-dismiss="modal" aria-hidden="true">Cancelar</button>
            <button id="saveBtn" type="submit" class="btn btn-primary" data-loading-text="Salvando...">Salvar</button>
        </div>
    </form>
</div> <!-- Fim Modal -->

<c:url var="jsBootstrapMin" value="/js/bootstrap.min.js"></c:url>
<c:url var="newUserUrl" value="/realestates"></c:url>

<script src="http://code.jquery.com/jquery-latest.js" ></script>
<script src="${jsBootstrapMin}" ></script>
<script type="text/javascript">
	$('#newImovelForm').submit(function() {
		_gaq.push(['_trackEvent', 'LinksAndButtons', 'Click', 'NewImovelSubmitted']);
		var btn = $('#saveBtn', this);
		btn.button('loading');
		var formData = $(this).serialize();
		$.ajax(
		{
			type : 'POST',
			url : '${newUserUrl}',
			data : formData,
			success : 
				function(data){
					var ok = data.status == 'ok';
					if(ok) {
						$('#newImovelDiv').modal('hide');
						document.location.href = '${newUserUrl}/' + data.id;
					} else {
						btn.button('reset');
						//Enviar mensagens de erro na tela
						alert("Erro ao enviar seu pedido de cadastro");
					}
				},
			error : function(xhr, desc, err) {
				btn.button('reset');
				console.log(xhr);
				console.log(desc);
				console.log(err);
				alert("Erro ao enviar seu pedido de cadastro");
			}
		});
		
		return false;
	});
</script>
