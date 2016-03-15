import no.systema.tds.tdsexport.filter.SearchFilterTdsExportTopicList;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
/**
 * @author oscardelatorre
 *
 */
public class JavaReflexionTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JavaReflexionTester tester = new JavaReflexionTester();
		try{
		tester.run();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	/**
	 oscardelatorre
	 Dec 1, 2012
	 */
	private void run() throws Exception{
		SearchFilterTdsExportTopicList searchFilter = new SearchFilterTdsExportTopicList();
		searchFilter.setStatus("S");
		Class cl = Class.forName(searchFilter.getClass().getCanonicalName());
		Field[] fields = cl.getDeclaredFields();
		List<Field> list = Arrays.asList(fields);
		for(Field field : list){
			field.setAccessible(true);
			String value = (String)field.get(searchFilter);
			if(value!=null && !"".equals(value)){
				System.out.println(field.getName() + " Value:" + value);
			}
		}
	}
}
