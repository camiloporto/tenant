package br.com.camiloporto.tenant.model;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.serializable.RooSerializable;

@RooJavaBean
@RooJson
@RooSerializable
public class ImovelMedia {
	
	private String id;
	
	private String imovelId;
	
	private String fileName;
	
	private String fileExtension;
	
}
