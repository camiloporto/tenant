package br.com.camiloporto.tenant.search;

import io.searchbox.Parameters;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Get;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.search.sort.Sort;
import io.searchbox.core.search.sort.Sort.Sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.camiloporto.tenant.model.Imovel;

import com.jayway.jsonpath.JsonPath;

@Component
public class ImovelElasticSearchRestRepository implements
		ImovelSearchRepository {
	
	@Autowired
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
		String json = i.toJson();
		Index index = new Index.Builder(json).index(indexName).type(typeName).build();
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
		Imovel retorno = null;
		if(result.isSucceeded()) {
			String json = result.getJsonString();
			retorno = createImovelFromHit((JSONObject) JsonPath.read(json, "$."));
		}
		
		return retorno;
	}

	@Override
	public List<Imovel> genericQuery(String query) throws Exception {
		QueryBuilder qb = QueryBuilders.boolQuery()
        	.must(QueryBuilders.queryString(query));
		Search search = new Search(Search.createQueryWithBuilder(qb.toString()));
		search.addIndex(indexName);
		search.addType(typeName);            
		
		return executeQuery(search);
		
	}
	
	private List<Imovel> executeQuery(Search search) throws Exception {
		JestResult result = jestClient.execute(search);
		return fromHitsToImoveis(result);
	}
	
	private List<Imovel> fromHitsToImoveis(JestResult result) {
		JSONArray hits = JsonPath.read(result.getJsonString(), "$.hits.hits[*]");
		List<Imovel> imovelList = new ArrayList<Imovel>();
		for (Object hit : hits) {
			imovelList.add(createImovelFromHit((JSONObject) hit));
		}
		return imovelList;
		
	}

	private Imovel createImovelFromHit(JSONObject hit) {
		String hitId = (String) hit.get("_id");
		JSONObject source = (JSONObject) hit.get("_source");
		Imovel i = Imovel.fromJsonToImovel(source.toJSONString());
		i.setId(hitId);
		return i;
	}

	@Override
	public List<Imovel> findAll() throws Exception {
		QueryBuilder qb = QueryBuilders.matchAllQuery();
		Sort sort = new Sort("ultimaAtualizacao", Sorting.DESC);
		Search search = new Search(Search.createQueryWithBuilder(qb.toString()), Arrays.asList(sort));
		search.addIndex(indexName);
		search.addType(typeName);
		
		return executeQuery(search);
	}

}
