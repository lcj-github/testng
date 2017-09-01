package com.lcj.util;

import java.sql.SQLException;

import org.xml.sax.SAXException;

import com.alibaba.fastjson.JSONArray;

public class JTestF {

	private String hostnameString;
	  private String hostportString;
	  private String protocol;
	  private String urlString;
	  private String urlEnv;

	
	  public JTestF(String hostname, String hostport, String protocol)
	  {
	    this.urlEnv = (protocol + "://" + hostname + ":" + hostport);
	  }

	  public void setRequestParams(String path, String parms)
	  {
	    this.urlString = (this.urlEnv + path + "?" + parms);
	  }

	  public JSONArray getTestcases(String mysqlurl, String user, String pwd, String query)
	    throws ClassNotFoundException, SQLException
	  {
	    return MysqlUtil.doQuery(mysqlurl, user, pwd, query);
	  }

	  public String sendRequest()
	    throws SAXException, Exception
	  {
	    String resultString = "";
	    resultString = HttpUtil.getData(this.urlString);
	    return resultString;
	  }

	  public String getHostnameString() {
	    return this.hostnameString;
	  }

	  public void setHostnameString(String hostnameString) {
	    this.hostnameString = hostnameString;
	  }

	  public String getHostportString() {
	    return this.hostportString;
	  }

	  public void setHostportString(String hostportString) {
	    this.hostportString = hostportString;
	  }

	  public String getProtocol() {
	    return this.protocol;
	  }

	  public void setProtocol(String protocol) {
	    this.protocol = protocol;
	  }

	  public String getUrlString() {
	    return this.urlString;
	  }

	  public void setUrlString(String urlString) {
	    this.urlString = urlString;
	  }
}
