package com.lcj.util;

import org.xml.sax.SAXException;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebResponse;

public class HttpUtil {
	
	 public static String getQuoteDyna(String url, String type)
			    throws Exception, SAXException
			  {
			    WebConversation web = new WebConversation();

			    GetMethodWebRequest get = new GetMethodWebRequest(url);

			    WebResponse response = web.getResponse(get);
			    if (response == null)
			    {
			      System.out.println("response=null");			   
			    }
			    String ret = null;
			    if (type.equalsIgnoreCase("json"))
			    {
			      ret = response.getText();
			    }

			    return ret;
			  }

			  public static String getDataByType(String url, String type)
			    throws Exception, SAXException
			  {
			    WebConversation web = new WebConversation();

			    GetMethodWebRequest get = new GetMethodWebRequest(url);

			    WebResponse response = web.getResponse(get);

			    String ret = null;
			    if (type.equalsIgnoreCase("json"))
			    {
			      ret = response.getText();
			    }
			    return ret;
			  }

			  public static String getData(String url)
			    throws Exception, SAXException
			  {
			    WebConversation web = new WebConversation();

			    GetMethodWebRequest get = new GetMethodWebRequest(url);

			    WebResponse response = web.getResponse(get);

			    String ret = null;
			    ret = response.getText();

			    return ret;
			  }

}
