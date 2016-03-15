/**
 * 
 */
package no.systema.espedsgadmin.service;
import java.util.*;
import no.systema.espedsgadmin.model.CustomerApplicationObject;


/**
 * @author oscardelatorre
 * @date Apr 1, 2014
 */
public interface FileDatabaseService {
	public List<CustomerApplicationObject> getCustomerApplicationList();
}
