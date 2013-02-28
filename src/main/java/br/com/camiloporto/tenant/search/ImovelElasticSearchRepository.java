package br.com.camiloporto.tenant.search;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.camiloporto.tenant.model.Imovel;

//@Component
public class ImovelElasticSearchRepository implements ImovelSearchRepository {
	
	@Autowired
	private Client esClient;
	
	private String indexName = "imoveis";
	private String indexTypeName = "imovel";

	public ImovelElasticSearchRepository() {
	}
	
	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}
	
	public void setIndexTypeName(String indexTypeName) {
		this.indexTypeName = indexTypeName;
	}
	
	public void setEsClient(Client esClient) {
		this.esClient = esClient;
	}
	
	@Override
	public Imovel index(Imovel i) {
		String json = i.toJson();
		IndexResponse ir = client().prepareIndex(indexName, indexTypeName)
	        .setRefresh(true)
	        .setSource(json.getBytes()) 
	        .execute().actionGet();
		i.setId(ir.id());
		return i;
	}
	
	private Client client() {
		return this.esClient;
	}

	@Override
	public Imovel findById(String id) {
		SearchResponse response = client().prepareSearch(indexName).setTypes(indexTypeName)
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
		SearchResponse response = client().prepareSearch(indexName).setTypes(indexTypeName)
	            .setQuery(QueryBuilders.boolQuery()
	            .must(QueryBuilders.queryString(query)))
	            .execute()
	            .actionGet();
		return fromHitsToImovel(response.hits());
	}

	public List<Imovel> findAll() {
		QueryBuilder qb = QueryBuilders.matchAllQuery();
		SearchResponse response = client().prepareSearch(indexName).setTypes(indexTypeName)
	            .setQuery(qb)
	            .addSort("ultimaAtualizacao", SortOrder.DESC)
	            .execute()
	            .actionGet();
		return fromHitsToImovel(response.hits());
	}
	
	private List<Imovel> fromHitsToImovel(SearchHits hits) {
		List<Imovel> result = new ArrayList<Imovel>();
		for (SearchHit hit : hits) {
			result.add(Imovel.fromJsonToImovel(hit.getSourceAsString()));
		}
		return result;
	}
	

}
