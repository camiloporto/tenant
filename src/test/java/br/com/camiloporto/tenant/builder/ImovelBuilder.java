package br.com.camiloporto.tenant.builder;

import br.com.camiloporto.tenant.model.Imovel;

public class ImovelBuilder {

	private Imovel imovel;
	
	public ImovelBuilder() {
		imovel = new Imovel();
	}
	
	public ImovelBuilder doTipo(String tipo) {
		imovel.setTipo(tipo);
		return this;
	}

	public ImovelBuilder noEstado(String estado) {
		imovel.setEstado(estado);
		return this;
	}

	public ImovelBuilder naCidade(String cidade) {
		imovel.setCidade(cidade);
		return this;
	}

	public ImovelBuilder noBairro(String bairro) {
		imovel.setBairro(bairro);
		return this;
	}

	public ImovelBuilder naRua(String rua) {
		imovel.setRua(rua);
		return this;
	}

	public Imovel create() {
		return imovel;
	}

}
