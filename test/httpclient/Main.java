package httpclient;

import org.apache.commons.httpclient. HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;


import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HttpClient client = new HttpClient();
	    client.getParams().setParameter("http.useragent", "Test Client");

	    BufferedReader br = null;

	    PostMethod method = new PostMethod("http://gw.systema.no:8282/espedsg/tvinnsadimport.do?action=doFind");
	    //method.addParameter("user", "oscar");
	    //method.addParameter("pwd", "demo");
	    

	    try{
	      int returnCode = client.executeMethod(method);

	      if(returnCode == HttpStatus.SC_NOT_IMPLEMENTED) {
	        System.err.println("The Post method is not implemented by this URI");
	        // still consume the response body
	        method.getResponseBodyAsString();
	      } else {
	        br = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream()));
	        String readLine;
	        while(((readLine = br.readLine()) != null)) {
	          System.out.println(readLine);
	        }
	      }
	    } catch (Exception e) {
	      System.err.println(e);
	    } finally {
	      method.releaseConnection();
	      if(br != null) try { br.close(); } catch (Exception fe) {}
	    }


	}
	
	
	
	
}
