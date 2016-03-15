/**
 * 
 */
package no.systema.overview.ufortolledeoppdrag.util;

/**
 * @author oscardelatorre
 * @date Aug 27, 2013
 * 
 */
public class UoppdragConstants {
	public static final String ACTIVE_URL_RPG = "activeUrlRPG";
	public static final String ACTIVE_URL_RPG_INITVALUE = "=)";
	
	//url
	public static final String URL_CHAR_DELIMETER_FOR_URL_WITH_HTML_REQUEST_GET = "?";
	public static final String URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST = "&"; //Used for GET and POST
	//
	public static final String DOMAIN_SEARCH_FILTER_GATE_CHAR = "searchFilterGateChar";
	public static final String DOMAIN_SEARCH_FILTER = "searchFilter";
	
	public static final String DOMAIN_MODEL = "model";
	public static final String DOMAIN_RECORD = "record";
	public static final String DOMAIN_CONTAINER = "container";
	
	public static final String DOMAIN_LIST = "list";
	public static final String DOMAIN_LIST_SIZE = "listSize";
	public static final String DOMAIN_UFORT_LIST_SIZE = "ufortListSize";
	public static final String DOMAIN_CHART_CATEGORIES_LIST= "chartCategoriesList";
	
	
	public static final String DOMAIN_LIST_TYPE = "listType";
	public static final String DOMAIN_LIMIT_LOWER = "llim";
	public static final String DOMAIN_LIMIT_UPPER = "ulim";
	
	public static final String DOMAIN_LIST_INTERVAL = "listInterval";
	public static final String ASPECT_IMG_SORT_PNG = "imgSortPng";
	public static final String ASPECT_IMG_SORT_PNG_NEUTRAL = "imgSortPngNeutral";
	public static final String ASPECT_ACTIVE_SORT_COLUMN = "sortCol";
	
	//
	public static final String DOMAIN_CATEGORY_TOTAL_GREEN_10 = "CAT_TOTAL_GREEN_10";
	public static final String DOMAIN_CATEGORY_TOTAL_YELLOW_10 = "CAT_TOTAL_YELLOW_10";
	public static final String DOMAIN_CATEGORY_TOTAL_RED_10 = "CAT_TOTAL_RED_10";
	public static final String DOMAIN_JFREE_CHART_MANAGER = "jfreeChartManager";
	
	
	//aspects in view (sucha as errors, logs, other
	public static final String ASPECT_ERROR_MESSAGE = "errorMessage";
	public static final String ASPECT_ERROR_META_INFO = "errorInfo";
		
	
	//==========
	//SESSION
	//==========
	//subset (emergency, watch or ok list) depending on bar selection
	public static final String SESSION_UOPP_SUBSET_LIST = "_UOPP_SUBSET_LIST";
	public static final String SESSION_UOPP_SUBSET_LIST_FILTERED = "_UOPP_SUBSET_LIST_FILTERED";
	//used when storing the JSON object in the session (when applicable)
	public static final String SESSION_UOPP_JSON_CONTAINER_GRAPH = "_UOPP_JSON_CONTAINER_GRAPH"; 
	
		
}
