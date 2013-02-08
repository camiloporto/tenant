package br.com.camiloporto.tenant.search;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;

import br.com.camiloporto.tenant.model.Imovel;

public class ImovelElasticSearchRepository implements ImovelSearchRepository {
	
	private Client client;

	public ImovelElasticSearchRepository(Client client) {
		this.client = client;
	}

	@Override
	public void index(Imovel i) {
		String json = i.toJson();
		client.prepareIndex("imoveis", "imovel", i.getId().toString())
        .setRefresh(true) //
        .setSource(json.getBytes()) 
        .execute().actionGet();
	}

	@Override
	public Imovel findById(long id) {
		SearchResponse response = client.prepareSearch("imoveis").setTypes("imovel")
	            .setQuery(QueryBuilders.boolQuery()
	            .must(QueryBuilders.termQuery("_id", id))).execute().actionGet();
		String json = response.getHits().getAt(0).getSourceAsString();
		return Imovel.fromJsonToImovel(json);
	}

}
