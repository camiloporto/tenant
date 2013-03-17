package br.com.camiloporto.tenant.search;

import io.searchbox.Parameters;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;

import java.util.ArrayList;
import java.util.List;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.camiloporto.tenant.model.ImovelMedia;

import com.jayway.jsonpath.JsonPath;

@Component
public class MediaElasticSearchRestRepositoryImpl implements
		MediaElasticSearchRestRepository {
	
	@Autowired
	private JestClient jestClient;
	
	private String indexName = "imoveis";
	
	private String typeName = "media";

	@Override
	public ImovelMedia index(ImovelMedia media) throws Exception {
		String json = media.toJson();
		Index index = new Index.Builder(json).index(indexName).type(typeName).build();
		index.addParameter(Parameters.REFRESH, true);
		JestResult result = jestClient.execute(index);
		if(result.isSucceeded()) {
			String id = (String) result.getValue("_id");
			media.setId(id);
			
			return media;
		} else {
			throw new Exception(result.getErrorMessage());
		}
	}

	@Override
	public List<ImovelMedia> findByImovelId(String idImovel) throws Exception {
		QueryBuilder qb = QueryBuilders.termQuery("_routing", idImovel);
		Search search = new GetSearch(Search.createQueryWithBuilder(qb.toString()));
		search.addIndex(indexName);
		
		search.addType(typeName);            
		
		return executeQuery(search);
	}
	
	private List<ImovelMedia> executeQuery(Search search) throws Exception {
		JestResult result = jestClient.execute(search);
		return fromHitsToMedia(result);
	}
	
	private List<ImovelMedia> fromHitsToMedia(JestResult result) {
		JSONArray hits = JsonPath.read(result.getJsonString(), "$.hits.hits[*]");
		List<ImovelMedia> mediaList = new ArrayList<ImovelMedia>();
		for (Object hit : hits) {
			mediaList.add(createMediaFromHit((JSONObject) hit));
		}
		return mediaList;
		
	}

	private ImovelMedia createMediaFromHit(JSONObject hit) {
		String hitId = (String) hit.get("_id");
		JSONObject source = (JSONObject) hit.get("_source");
		ImovelMedia media = ImovelMedia.fromJsonToImovelMedia(source.toJSONString());
		media.setId(hitId);
		return media;
	}
	
	static class GetSearch extends Search {
		public GetSearch(String createQueryWithBuilder) {
			super(createQueryWithBuilder);
		}

		@Override
		public String getRestMethodName() {
			return "GET";
		}
	}

}
