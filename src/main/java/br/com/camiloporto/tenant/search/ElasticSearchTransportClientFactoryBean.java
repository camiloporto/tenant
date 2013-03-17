package br.com.camiloporto.tenant.search;

import java.util.Arrays;
import java.util.List;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

public class ElasticSearchTransportClientFactoryBean extends
		AbstractElasticSearchClientFactoryBean {
	
	private List<String> nodes = Arrays.asList("localhost:9200");
	
	public List<String> getNodes() {
		return nodes;
	}
	
	public void setNodes(List<String> nodes) {
		this.nodes = nodes;
	}

	@Override
	protected Client buildClient() throws Exception {
		TransportClient client = new TransportClient();

		for (int i = 0; i < nodes.size(); i++) {
			client.addTransportAddress(toAddress(nodes.get(i)));
		}

		return client;
	}

	private InetSocketTransportAddress toAddress(String address) {
		if (address == null) return null;

		String[] splitted = address.split(":");
		int port = 9300;
		if (splitted.length > 1) {
			port = Integer.parseInt(splitted[1]);
		}

		return new InetSocketTransportAddress(splitted[0], port);
	}

}
