package br.com.camiloporto.tenant.search;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import br.com.camiloporto.tenant.model.Imovel;

public class ImovelElasticSearchRepository implements ImovelSearchRepository {
	
	private Client client;
	
	private String indexName;
	private String indexTypeName;

	public ImovelElasticSearchRepository(Client client, String indexName, String indexTypeName) {
		this.client = client;
		this.indexName = indexName;
		this.indexTypeName = indexTypeName;
	}

	@Override
	public void index(Imovel i) {
		String json = i.toJson();
		client.prepareIndex(indexName, indexTypeName, i.getId().toString())
        .setRefresh(true) //
        .setSource(json.getBytes()) 
        .execute().actionGet();
	}

	@Override
	public Imovel findById(long id) {
		SearchResponse response = client.prepareSearch(indexName).setTypes(indexTypeName)
	            .setQuery(QueryBuilders.boolQuery()
	            .must(QueryBuilders.termQuery("_id", id))).execute().actionGet();
		Imovel retorno = null;
		if(response.hits().totalHits() > 0) {
			String json = response.hits().getAt(0).getSourceAsString();
			retorno = Imovel.fromJsonToImovel(json);
		}
		return retorno;
	}

	@Override
	public List<Imovel> genericQuery(String query) {
		SearchResponse response = client.prepareSearch(indexName).setTypes(indexTypeName)
	            .setQuery(QueryBuilders.boolQuery()
	            .must(QueryBuilders.queryString(query)))
	            .execute()
	            .actionGet();
		List<Imovel> result = new ArrayList<Imovel>();
		for (SearchHit hit : response.hits()) {
			result.add(Imovel.fromJsonToImovel(hit.getSourceAsString()));
		}
		return result;
	}

}
