package br.com.camiloporto.tenant.search;

import io.searchbox.Parameters;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.camiloporto.tenant.model.ImovelMedia;

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

}
