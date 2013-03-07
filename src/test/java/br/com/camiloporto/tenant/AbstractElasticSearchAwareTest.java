package br.com.camiloporto.tenant;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;

import br.com.camiloporto.tenant.builder.ElasticSearchTestIndexBuilder;

@ContextConfiguration(locations = {"/META-INF/spring/applicationContext.xml", "/META-INF/spring/applicationContext-jpa.xml"})
@ActiveProfiles("unit-test")
public class AbstractElasticSearchAwareTest extends AbstractTestNGSpringContextTests {
	
	private ElasticSearchTestIndexBuilder esBuilder = new ElasticSearchTestIndexBuilder();
	
	@BeforeMethod
	public void clearIndexData() throws Exception {
		esBuilder.deleteAllDocuments();
	}
	
	@AfterClass
	public void endTest() {
		esBuilder.close();
	}
	
//	@BeforeClass
//	public void initRepository() throws FileNotFoundException {
//		readMappingFile();
//	}
	
}
