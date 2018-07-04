package com.lcj.util;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Datetest {
	
	
	public final static java.sql.Date string2Date(String dateString)
			throws java.lang.Exception {
			DateFormat dateFormat;
			dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			dateFormat.setLenient(false);
			java.util.Date timeDate = dateFormat.parse(dateString);//util类型
			java.sql.Date dateTime = new java.sql.Date(timeDate.getTime());//sql类型
			return dateTime;
			}
	
	public static void main(String[] args){
		try {
			String sToDate = "2005-08-18";//用于转换成java.sql.Date的字符串
			      
			Date date1 = string2Date(sToDate);
			    
			System.out.println("Date:"+date1.toString());//结果显示
		
			}catch(Exception e) {
			e.printStackTrace();
			}
		
		/*String d = num2DateFormat( "20160821", "-", 4,7);  //2016-08-21
		System.out.println(d);*/
	
	}
	
	/**
     * @param 将字符串插入指定的位置
     *            str被插入的字符串      substr要插入的字符串 location 要插入的位置
     */

	public static String getInsert(String str, String substr, int location) {
        String s1 = str;
        String s2 = substr;
        int i = location;
        String insertedStr = s1.substring(0, i) + s2
                + s1.substring(i, s1.length()); 
        return insertedStr; 
    }
	
	
	public static String num2DateFormat(String str, String substr, int location1, int location2) {
		String t = getInsert(str,substr,location1);				
		return getInsert(t,"-",location2);
    }



}
