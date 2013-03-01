package br.com.camiloporto.tenant.image;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;

public class CloudinaryServiceTest {
	
	private Cloudinary cloudinary;
	
	private final String HOMER_PUBLIC_ID = "homer";
	
	@BeforeClass
	public void configCloudinary() {
		Map<String, String> config = new HashMap<String, String>();
		config.put("cloud_name", "inquilinus");
		config.put("api_key", "315679192766649");
		config.put("api_secret", "4G3WRttWExSy0ovHNx0HCqJKtdQ");
		cloudinary = new Cloudinary(config);
	}
	
	@Test
	public void deveGerarURLDeImagem() {
		Transformation t = new Transformation();
		t.width(50).height(60).crop("scale");
		String imgTag = cloudinary.url().format("jpg").transformation(t).generate(HOMER_PUBLIC_ID);
		System.out.println(imgTag);
	}
	
	@Test(enabled=false)
	public void deveFazerUploadDeImagem() throws IOException {
		Map<String, String> result = cloudinary.uploader().upload("src/test/resources/br/com/camiloporto/tenant/image/homer.jpg", cloudinary.emptyMap());
		System.out.println(result);
	}
}
