package br.com.camiloporto.tenant.search;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.node.Node;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.camiloporto.tenant.model.Imovel;

@Component
public class ImovelElasticSearchRepository implements ImovelSearchRepository {
	
	@Autowired
	private Node node;
	
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
	
	public void setNode(Node node) {
		this.node = node;
	}
	
	@Override
	public void index(Imovel i) {
		String json = i.toJson();
		client().prepareIndex(indexName, indexTypeName, i.getId().toString())
        .setRefresh(true) //
        .setSource(json.getBytes()) 
        .execute().actionGet();
	}
	
	private Client client() {
		return node.client();
	}

	@Override
	public Imovel findById(long id) {
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
		List<Imovel> result = new ArrayList<Imovel>();
		for (SearchHit hit : response.hits()) {
			result.add(Imovel.fromJsonToImovel(hit.getSourceAsString()));
		}
		return result;
	}

}
