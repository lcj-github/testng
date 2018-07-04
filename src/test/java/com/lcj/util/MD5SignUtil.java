package com.lcj.util;

import org.apache.commons.codec.digest.DigestUtils;

 
public final class MD5SignUtil {

	public static String getSignMsg(String clientId, String osn, String secretKey)
	  {
	    String md5Str = clientId + "," + osn + "," + secretKey;
	    String signatureMsg = DigestUtils.md5Hex(md5Str);

	    return signatureMsg;
	  }
	
	
	public static String getGTJASignMsg(String clientId, String tradeNo,String procDate,String secretKey)
	  {
	    String md5Str = clientId + "," + tradeNo + "," + procDate+ "," + "FI"+ "," + secretKey;
	    System.out.println(md5Str);
	    String signatureMsg = DigestUtils.md5Hex(md5Str);

	    return signatureMsg;
	  }
	

	  public static void main(String[] args)
	  {
		  String s = getGTJASignMsg("100001", "10300", "20160804", "1qaz2wsx3edc");
		  System.out.println(s);
	    
	  }
}
