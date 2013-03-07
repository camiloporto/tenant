package br.com.camiloporto.tenant.image;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;

@ContextConfiguration(locations = {"/META-INF/spring/applicationContext.xml"})
@ActiveProfiles("unit-test")
public class CloudinaryServiceTest extends AbstractTestNGSpringContextTests {
	
	@Autowired
	private Cloudinary cloudinary;
	
	private final String HOMER_PUBLIC_ID = "homer";
	
	@BeforeClass
	public void configCloudinary() {
		Assert.assertNotNull(cloudinary, "objeto cloudinary deveria de sido injetado");
		Assert.assertNotNull(cloudinary.getStringConfig("cloud_name"), "cloud_name deveria ter sido configurado");
		Assert.assertNotNull(cloudinary.getStringConfig("api_key"), "api_key deveria ter sido configurado");
		Assert.assertNotNull(cloudinary.getStringConfig("api_secret"), "api_secret deveria ter sido configurado");
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
