/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.main.util;

/**
 * All type of system constants for TVINN-SAD in general
 * 
 * @author oscardelatorre
 * @date Apr 30, 2014
 * 
 *
 */
public final class TvinnSadMaintenanceConstants {
	
	
	//session constants
	public static final String ACTIVE_URL_RPG_TVINN_SAD_MAINTENANCE = "activeUrlRPG_TvinnSadMaintenance";
	public static final String ACTIVE_URL_RPG_UPDATE_TVINN_SAD_MAINTENANCE = "activeUrlRPGUpdate_TvinnSadMaintenance";
	public static final String ACTIVE_URL_RPG_FETCH_ITEM_TVINN_SAD_MAINTENANCE = "activeUrlRPGFetchItem_TvinnSadMaintenance"; //Ajax
	public static final String ACTIVE_URL_RPG_INITVALUE = "=)";
	
	//actions
	public static final String EDIT_ACTION_ON_TOPIC = "editActionOnTopic";
	public static final String EDIT_ACTION_ON_TOPIC_ITEM = "editActionOnTopicItem";
	
	public static final String ACTION_FETCH = "doFetch";
	public static final String ACTION_UPDATE = "doUpdate";
	public static final String ACTION_CREATE = "doCreate";
	public static final String ACTION_DELETE = "doDelete";
	public static final String ACTION_SEND = "doSend";
	
	//update modes
	public static final String MODE_UPDATE = "U";
	public static final String MODE_ADD = "A";
	public static final String MODE_DELETE = "D";
	public static final String MODE_SEND = "S";
	
	//url
	public static final String URL_CHAR_DELIMETER_FOR_URL_WITH_HTML_REQUEST_GET = "?";
	public static final String URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST = "&"; //Used for GET and POST
	//base path for resource files (for drop-downs or other convenient files
	
	public static final String RESOURCE_FILES_PATH = "/WEB-INF/resources/files/";
	public static final String RESOURCE_MODEL_KEY_HOURS_LIST = "hoursList";
	public static final String RESOURCE_MODEL_KEY_MINUTES_LIST = "minutesList";
	public static final String RESOURCE_MODEL_KEY_UOM_LIST = "uomList";
	
	//CODES
	public static final int ERROR_CODE = -1;
	
	
	
	//domain objects for model-view passing values
	public static final String DOMAIN_MODEL = "model";
	public static final String DOMAIN_RECORD = "record";
	public static final String DOMAIN_LIST = "list";
	public static final String DOMAIN_LIST_SIZE = "listSize";
	
	/*
	public static final String DOMAIN_SEARCH_FILTER = "searchFilter";
	public static final String SESSION_SEARCH_FILTER = "searchFilter";
	//filter per module
	public static final String DOMAIN_SEARCH_FILTER_SADIMPORT = "searchFilterSadImport";
	public static final String DOMAIN_SEARCH_FILTER_SADEXPORT = "searchFilterSadExport";
	public static final String DOMAIN_SEARCH_FILTER_SADIMPORT_NCTS = "searchFilterSadImportNcts";
	public static final String DOMAIN_SEARCH_FILTER_SADEXPORT_NCTS = "searchFilterSadExportNcts";
	public static final String SESSION_SEARCH_FILTER_SADIMPORT = "searchFilterSadImport";
	public static final String SESSION_SEARCH_FILTER_SADEXPORT = "searchFilterSadExport";
	public static final String SESSION_SEARCH_FILTER_SADIMPORT_NCTS = "searchFilterSadImportNcts";
	public static final String SESSION_SEARCH_FILTER_SADEXPORT_NCTS = "searchFilterSadExportNcts";
	
	
	public static final String DOMAIN_RECORD_TOPIC_TVINN_SAD = "recordTopicTvinnSad";
	public static final String DOMAIN_RECORD_TOPIC_TVINN_SAD_UNLOADING = "recordTopicTvinnSadUnloading";
	
	public static final String DOMAIN_LIST = "list";
	public static final String DOMAIN_LIST_SIZE = "listSize";
	
	public static final String DOMAIN_RECORD_ITEM_CONTAINER_TOPIC = "recordItemContainerTopic";
	public static final String DOMAIN_RECORD_ITEM_CONTAINER_FINANS_OPPLYSNINGER_TOPIC = "recordItemContainerFinansOpplysningerTopic";
	public static final String SESSION_LIST = "sessionList";
	public static final String ITEM_LIST = "itemList";
	*/
	
	//aspects in view (sucha as errors, logs, other
	public static final String ASPECT_ERROR_MESSAGE = "errorMessage";
	public static final String ASPECT_ERROR_META_INFO = "errorInfo";
	

	   
}
