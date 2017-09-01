package com.lcj.testng;

import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lcj.util.ConfigUtil;
import com.lcj.util.JTestF;

public class ContactngTest {
	
	//envirenment
	private static String ip = ConfigUtil.getConfig("ip");
	private static String port=ConfigUtil.getConfig("port");
	private static String protocol="http://"; //http or https,default http
	private static String mysqlurl=ConfigUtil.getConfig("mysqlurl");
	private static String mysqluser=ConfigUtil.getConfig("mysqluser");
	private static String mysqlpwd=ConfigUtil.getConfig("mysqlpwd");
	private static String mysqlquery=ConfigUtil.getConfig("mysqlquery");
	
	private String apiURLPrx =protocol+ip+":"+port;
	private HttpPost method;
	private HttpClient httpClient;
	
	/*//需要传入testcase的参数
	private String path;
	private String params;
	private String expect;
	 */
	
	public static Object[][]  getDataFromMysql() throws ClassNotFoundException, SQLException{
		
		JTestF jtest=new JTestF(ip, port, protocol);
		JSONArray ts=jtest.getTestcases(mysqlurl, mysqluser, mysqlpwd, mysqlquery);
		
		int i=0;
		List  testdatArrayList = new ArrayList();
		for(;i < ts.size(); i++){
			JSONObject jObject=ts.getJSONObject(i);
			Object[] testcase = {jObject.getString("command"), jObject.getString("prams"),jObject.getString("expectoutput")};
			testdatArrayList.add(testcase);
		}
		
		String[][] retStrings=new String[testdatArrayList.size()][];
		for(int index=0; index<testdatArrayList.size(); index++)
		{
		      Object[] s = (Object[])testdatArrayList.get(index);
		      retStrings[index] = new String[s.length];
		      for(int j=0;j<s.length;j++){
		    	  retStrings[index][j] = (String) s[j];
		      }
		}
		return retStrings;
	} 
	
	@DataProvider(name = "data-provider")
	public Object[][] dataProviderMethod()  throws ClassNotFoundException, SQLException {				
		Object[][] collectionObjects=getDataFromMysql();
		return collectionObjects;
	}
	
	
	@BeforeTest
	public void setUp() throws Exception {		
		System.out.println("只执行一次，在方法执行前");
		/*method = new HttpPost(path);
		httpClient   = new DefaultHttpClient();
		method.addHeader("Content-type","application/json; charset=utf-8");
		method.setHeader("Accept", "application/json");	*/
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterTest
	public void tearDown() throws Exception {
		System.out.println("只执行一次，在方法执行后");
		method = null;
		httpClient   = null;
	}
	
	

	@Test(dataProvider = "data-provider")
	public void testMethod(String path,String params,String expect) throws ParseException, IOException {
	
		System.out.println("该方法根据DataProvider中行数个数多次执行");	
		
		//本放在setup中，但 因为 path目前在dataProvider的获取，导致setup中path为null问题。
		//后期url如果固定的话，即可放到setup中
		method = new HttpPost(apiURLPrx+path);
		httpClient   = new DefaultHttpClient();
		method.addHeader("Content-type","application/json; charset=utf-8");
		method.setHeader("Accept", "application/json");	
		
		method.setEntity(new StringEntity(params, Charset.forName("UTF-8")));
		HttpResponse response = httpClient.execute(method);
		String respBody  =  EntityUtils.toString(response.getEntity());	
       // Assert.assertEquals(respBody,expect,"Not equals: ");
	}
	
	
	
	

}
