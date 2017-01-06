package no.systema.z.main.maintenance.mapper.jsonjackson.dbtable;

import java.io.InputStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainCundcContainer;

public class TestJMaintMainCundcMapper {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public final void testJsonPayLoad() throws Exception{
		String json = readFile("json.txt");
		Assert.assertNotNull(json);
		
		MaintMainCundcMapper mapper = new MaintMainCundcMapper();
		JsonMaintMainCundcContainer container = mapper.getContainer(json);
		Assert.assertNotNull(container);
		Assert.assertNotNull(container.getList());
		
	}

	private String readFile(String jsonFile) throws Exception {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

		InputStream input = classLoader.getResourceAsStream("no/systema/z/main/maintenance/mapper/jsonjackson/dbtable/" + jsonFile);
		StringBuilder builder = new StringBuilder();
		int ch;
		while ((ch = input.read()) != -1) {
			builder.append((char) ch);
		}

		return builder.toString();

	}

}
