package com.lcj.testng.interUsing;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.lcj.util.MD5SignUtil;
import com.lcj.util.UUIDGenerator;

public class OpenAcctJzTest {
	
	private HttpPost method;
	private HttpClient httpClient;
	private String apiURL = "http://1*.*.*.*:****/xc-aas/kingdom/****OpenAcctStep";
	
		
	@BeforeTest
	public void setUp() {
		method = new HttpPost(apiURL);
		httpClient = HttpClients.createDefault();
		method.addHeader("Content-type","application/json; charset=utf-8");
		method.setHeader("Accept", "application/json");	
	}
	
	
	
	@Test
	public void testMobileUserName() throws Exception{
		
		String traderNo = "19900";      //券商号
		String mobile = "18511620011";	//手机号
		String clientId = "600004";     //t_client_info表client_id字段           
		String secretKey = "2011ff1d2898"; //t_client_info表client_key字段
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		String dateString = df.format(new Date());
		String osnStr = UUIDGenerator.getUUID();
		String signMsg = MD5SignUtil.getSignMsg(clientId, osnStr, secretKey);
		
		JSONObject jsonObject = new JSONObject();		
		jsonObject.put("version", "1.0");
		jsonObject.put("inputCharset","1");
		jsonObject.put("signType","1");
		jsonObject.put("userName","kh160553217");
		jsonObject.put("custName","");			
		jsonObject.put("mobile", mobile);
		jsonObject.put("currentStep","U");
		jsonObject.put("currentStepDesc","开户完成");
		jsonObject.put("operatorTime",dateString);
		jsonObject.put("clientId",clientId);
		jsonObject.put("osn",osnStr);
		jsonObject.put("sourceNo", "96");            //96  金证
		jsonObject.put("traderNo", traderNo);
		jsonObject.put("firmAcct", "000000000000");
		jsonObject.put("openAcctChannel", "1");
		jsonObject.put("childOpenAcctChannel", "16");		
		String ext = "{\"childOpenAcctChannel\":\"16\",\"node\":\"1664\",\"anon\":\"gJqcExtrQZ6JhUD5PZ9m6A..\"}";
		jsonObject.put("ext", ext);
		jsonObject.put("customerCode", "aaaaaaaa");
		jsonObject.put("signMsg", signMsg);
		
		String reqJson = jsonObject.toString();
		System.out.println("reqJson==="+reqJson);
		
		String respBody = postJson(reqJson);
		System.out.println("respBody==="+respBody);
			
	}
	
	
	
	private String postJson(String parameters){		 
		String respBody = null;		
		method.setEntity(new StringEntity(parameters, Charset.forName("UTF-8")));
		try {
			HttpResponse response = httpClient.execute(method);
			respBody =  EntityUtils.toString(response.getEntity());
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
		return respBody;
	}
	
	@AfterTest
	public void tearDown()   {
		method = null;
		httpClient   = null;
	}
	
  

}
