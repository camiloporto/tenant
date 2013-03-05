package br.com.camiloporto.tenant.builder;

import java.io.IOException;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;


public class ElasticSearchTestIndexBuilder {
	
	private TransportClient tc;
	
	public ElasticSearchTestIndexBuilder() {
		tc = new TransportClient();
		tc.addTransportAddress(new InetSocketTransportAddress("localhost", 9300));
	}
	
	public ElasticSearchTestIndexBuilder deleteAllDocuments() throws IOException {
		
		
		tc.prepareDeleteByQuery("imoveis")
			.setQuery(QueryBuilders.matchAllQuery())
			.execute().actionGet();
		
		return this;
		
	}
	
	public void close() {
		tc.close();
	}
	
}
