package com.lcj.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class MysqlUtil {
	
	private static String url = "jdbc:mysql://127.0.0.1:3306/test";
	  private static String driver = "com.mysql.jdbc.Driver";

	  private static Connection con = null;
	  private static Statement stmt = null;

	  public static JSONArray doQuery(String ip, String port, String user, String pwd, String dbname, String queryesql)
	    throws SQLException, ClassNotFoundException
	  {
	    url = "jdbc:mysql://" + ip + ":" + port + "/" + dbname;
	    return doQuery(url, user, pwd, queryesql);
	  }

	  public static JSONArray doQuery(String mysqlurl, String user, String pwd, String queryesql)
	    throws SQLException, ClassNotFoundException
	  {
	    Class.forName(driver);
	    con = DriverManager.getConnection(mysqlurl, user, pwd);
	    con.setAutoCommit(false);

	    stmt = con.createStatement();
	    ResultSet result = stmt.executeQuery(queryesql);
	    con.commit();

	    JSONArray testcases = new JSONArray();
	    while (result.next()) {
	      JSONObject tc = new JSONObject();
	      tc.put("app", result.getString("app"));
	      tc.put("command", result.getString("command"));
	      tc.put("precommand", result.getString("precommand"));
	      tc.put("prams", result.getString("prams"));
	      tc.put("postcommand", result.getString("postcommand"));
	      tc.put("outputtype", result.getString("outputtype"));
	      tc.put("expectoutput", result.getString("expectoutput"));
	      tc.put("comment", result.getString("comment"));
	      tc.put("author", result.getString("author"));
	      tc.put("tester", result.getString("tester"));
	      tc.put("zephryID", result.getString("zephryID"));
	      tc.put("jiraID", result.getString("jiraID"));
	      tc.put("designdate", result.getString("designdate"));

	      testcases.add(tc);
	    }

	    if (stmt != null) stmt.close();
	    if (con != null) con.close();

	    return testcases;
	  }

}
