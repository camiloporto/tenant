package br.com.camiloporto.tenant.search;

import org.elasticsearch.client.Client;
import org.elasticsearch.node.Node;
import org.springframework.beans.factory.annotation.Autowired;

public class ElasticSearchLocalClientFactoryBean extends AbstractElasticSearchClientFactoryBean {

	@Autowired
	private Node node;
	
	public void setNode(Node node) {
		this.node = node;
	}
	
	@Override
	protected Client buildClient() throws Exception {
		if (node == null)
			throw new Exception(
					"You must define an ElasticSearch Node as a Spring Bean.");
		return node.client();
	}

}
