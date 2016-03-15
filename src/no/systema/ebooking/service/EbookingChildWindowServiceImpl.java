package no.systema.ebooking.service;
import no.systema.main.model.jsonjackson.general.postalcodes.JsonPostalCodesContainer;
import no.systema.main.mapper.jsonjackson.general.PostalCodesMapper;

/**
 * 
 * @author oscardelatorre
 * @date Feb 2, 2016 
 * 
 */
public class EbookingChildWindowServiceImpl implements EbookingChildWindowService {
	/**
	 * 
	 */
	public JsonPostalCodesContainer getPostalCodesContainer(String utfPayload){
		JsonPostalCodesContainer container = null;
		try{
			PostalCodesMapper mapper = new PostalCodesMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
}
