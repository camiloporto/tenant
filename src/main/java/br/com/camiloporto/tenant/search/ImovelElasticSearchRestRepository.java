package br.com.camiloporto.tenant.search;

import io.searchbox.Parameters;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Get;
import io.searchbox.core.Index;
import io.searchbox.core.Search;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import br.com.camiloporto.tenant.model.Imovel;

public class ImovelElasticSearchRestRepository implements
		ImovelSearchRepository {
	
	private JestClient jestClient;
	
	private String indexName = "imoveis";
	
	private String typeName = "imovel";
	
	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}
	
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	public void setJestClient(JestClient jestClient) {
		this.jestClient = jestClient;
	}

	@Override
	public Imovel index(Imovel i) throws Exception {
		Index index = new Index.Builder(i).index(indexName).type(typeName).build();
		index.addParameter(Parameters.REFRESH, true);
		JestResult result = jestClient.execute(index);
		if(result.isSucceeded()) {
			String id = (String) result.getValue("_id");
			i.setId(id);
			
			return i;
		}
		return null;
	}

	@Override
	public Imovel findById(String id) throws Exception {
		Get get = new Get.Builder(id).index(indexName).type(typeName).build();
		JestResult result = jestClient.execute(get);
		return result.getSourceAsObject(Imovel.class);
	}

	@Override
	public List<Imovel> genericQuery(String query) throws Exception {
		QueryBuilder qb = QueryBuilders.boolQuery()
        	.must(QueryBuilders.queryString(query));
		System.out.println("ImovelElasticSearchRestRepository.genericQuery() " + qb.toString());
		Search search = new Search(Search.createQueryWithBuilder(qb.toString()));
		search.addIndex(indexName);
		search.addType(typeName);            

		JestResult result = jestClient.execute(search);
		return result.getSourceAsObjectList(Imovel.class);
	}

}
