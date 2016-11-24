/**
 * 
 */
package no.systema.z.main.maintenance.mapper.jsonjackson.dbtable;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import no.systema.z.main.maintenance.model.jsonjackson.dbtable.IJsonMaintMainContainer;

/**
 * This class maps an json payload on delivered {@link IJsonMaintMainContainer}
 * 
 * @author Fredrik Möller
 * @date Nov 22, 2016
 * 
 */
public class MaintMainGenericMapper {
	private String className = null;

	public MaintMainGenericMapper(IJsonMaintMainContainer container) {
		this.className = container.getClass().getName();
	}

	public Object getContainer(String utfPayload) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		Class<?> clazz = Class.forName(className);
		return mapper.readValue(utfPayload.getBytes(), clazz);
	}
}
