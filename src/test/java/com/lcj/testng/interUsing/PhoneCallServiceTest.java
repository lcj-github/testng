package com.lcj.testng.interUsing;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.lcj.util.excel2010.Common;
import com.lcj.util.excel2010.TestReadObjectExcel;
import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

public class PhoneCallServiceTest { 

	
	
	public static void testphoneCall() throws Exception {
		System.out.println("testphoneCall");		
		// 建立一个WebConversation实例
		WebConversation wc = new WebConversation();
		//测试环境
		String urlfixed = "https://***.**.com.cn/***spex.aspx?op=***all&app***=902&Para=";
				
		List<String> keyValueparaList = new TestReadObjectExcel().readTestExcel(Common.phoneCall_INFO_XLSX_PATH);
		for (int i=0;i<keyValueparaList.size();i++)
		{
			String para = keyValueparaList.get(i);
			System.out.println("para=="+para);
			String paraR = para.replaceAll("\r|\n", "");		
			String sendRequest = urlfixed + paraR;		// 向指定的URL发出请求
			System.out.println("sendRequest=="+sendRequest);	
			WebRequest req = new GetMethodWebRequest(sendRequest);	
			// 获取响应对象    
			WebResponse resp = wc.getResponse(req);
			// 用getText方法获取相应的全部内容
			System.out.println(resp.getText());
		}		
		
	}
	
	//拼接请求参数 ，供参考。https://**.cn/crm**.aspx?op=makeBusy&Para={'phoneNumber':'18621308623','reasonCode':'5'}
	public static String obtainMakeBusyPara(String phoneNumber,String reasonCode,String workMode) throws Exception
	{
		
		Map<String,Object> remarkMap  = new HashMap<String,Object>();
		if (phoneNumber.length()!=0)
		{
			remarkMap.put("phoneNumber", phoneNumber);
		}	
		if (reasonCode != null)
		{
			remarkMap.put("reasonCode", reasonCode);
		}
		if (workMode.length()!=0)
		{
			remarkMap.put("workMode", workMode);
		}	
			
		int mapSize = remarkMap.size();		
		String remarkStr  = "{";
		Iterator<String> keys = remarkMap.keySet().iterator(); 
		while(keys.hasNext()) {	
		   mapSize--;	
		   String keyMap = (String) keys.next(); 
		   Object value=remarkMap.get(keyMap);
		   
		   if (value instanceof String)
		   {			   
			   String val = (String) value;			   
			   remarkStr = remarkStr+"\"";
			   remarkStr = remarkStr.concat(keyMap);	
			   remarkStr = remarkStr+"\"";
			   remarkStr = remarkStr.concat(":");
			   remarkStr = remarkStr+"\"";
			   remarkStr = remarkStr.concat(val);
			   remarkStr = remarkStr+"\""; 
		   }
		   else if (value instanceof Integer) {
			   String val = String.valueOf(value) ;
			   remarkStr = remarkStr+"\"";
			   remarkStr = remarkStr.concat(keyMap);	
			   remarkStr = remarkStr+"\"";
			   remarkStr = remarkStr.concat(":");
			   remarkStr = remarkStr.concat(val);
		 } 
		   
		   if (mapSize != 0)
		   {
			   remarkStr =  remarkStr.concat(","); 
		   } 
		   
		}
		remarkStr = remarkStr.concat("}");		
		remarkStr = remarkStr .replaceAll("\"", "\'");
		//remarkStr = AES.encrypt("key", "iv", remarkStr);
		remarkStr = remarkStr.replaceAll("\r|\n", "");
		return remarkStr;
	}
	

	

}
