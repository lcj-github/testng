package com.lcj.util.excel2010;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.lcj.util.AES;
import com.lcj.util.MD5Util;

public class ReadExcel {
	
	

public static boolean isBlankRow(XSSFRow row){
		if(row == null) return true;
		boolean result = true;
		for(int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++){
			XSSFCell cell = row.getCell(i, XSSFRow.RETURN_BLANK_AS_NULL);
			String value = "";
			if(cell != null){
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					value = cell.getStringCellValue();
					break;
				case Cell.CELL_TYPE_NUMERIC:
					value = String.valueOf((int) cell.getNumericCellValue());
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					value = String.valueOf(cell.getBooleanCellValue());
					break;
				case Cell.CELL_TYPE_FORMULA:
					value = String.valueOf(cell.getCellFormula());
					break;
				//case Cell.CELL_TYPE_BLANK:
				//	break;
				default:
					break;
				}
				
				if(!value.trim().equals("")){
					result = false;
					break;
				}
			}
		}
		
		return result;
	}
	
	 public List<String> readTestExcel(String path) throws Exception {
		 
		    System.out.println(Common.PROCESSING + path);
	        InputStream is = new FileInputStream(path);
	        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
	        Map<String,String> strMap = null;
	        List<String> reqList = new ArrayList<String>();
	        
	        // Read the Sheet
	        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
	            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
	            if (xssfSheet == null) {
	                continue;
	            }
	            
	           //获取标题行
	            XSSFRow xssfTitle = xssfSheet.getRow(0);
	            List<String> titleList = new ArrayList<String>();
	            if(null != xssfTitle)
	            {	            	
	            	int coloumNum= xssfTitle.getPhysicalNumberOfCells();
	            	for (int coloumIndex = 0;coloumIndex<coloumNum;coloumIndex++)
	            	{
	            		XSSFCell titleCell = xssfTitle.getCell(coloumIndex);	            		
	            		titleList.add(getValue(titleCell)); 
	            	}	            	
	            }
	            
	            // Read the Row
	            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {	            	
	            	
	                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
	                if (xssfRow != null && !isBlankRow(xssfRow)) {
	                	
	                	 //总列数  
	    	            int tdLength = titleList.size(); 	                	
	                	strMap = new  HashMap<String,String>();	                	
	                	for (int colum=0;colum<tdLength;colum++)
	                	{	                		
	                		strMap.put(titleList.get(colum), getValue(xssfRow.getCell(colum)));
	                	}	                    
	                	reqList.add(obtainkeyVal(strMap));
	                }
	            }
	        }
	        return reqList;
	 }
	 
	 
	 
	 
	 
	 public static String obtainkeyVal(Map<String,String> strMap) throws Exception
		{
			String key = "jf.gw.com.cn";
			String iv = "jf.gw.com.cn";
			
			int mapSize = strMap.size();		
			String conStr  = "{";
			Iterator<String> keys = strMap.keySet().iterator(); 
			while(keys.hasNext()) {	
			   mapSize--;	
			   String keyMap = (String) keys.next();
			   String value=strMap.get(keyMap);
			   if (keyMap.equals("Mobile"))
			   {
				   value = MD5Util.md5Hex(value, "test");
			   }			   
			   conStr = conStr+"\"";
			   conStr = conStr.concat(keyMap);	
			   conStr = conStr+"\"";
			   conStr = conStr.concat(":");
			   conStr = conStr+"\"";
			   conStr = conStr.concat(value);
			   conStr = conStr+"\"";
			   if (mapSize != 0)
			   {
				   conStr =  conStr.concat(","); 
			   } 
			}
			conStr = conStr.concat("}");
			conStr = AES.encrypt(key, iv, conStr);
			conStr = conStr.replaceAll("\r|\n", "");
			
			
			return conStr;
		}
	
	
	

	/**
     * read the Excel file
     * @param path the path of the Excel file
     * @return
     * @throws IOException
     */
    public List<Student> readExcel(String path) throws IOException {
        if (path == null || Common.EMPTY.equals(path)) {
            return null;
        } else {
            String postfix = Util.getPostfix(path);
            if (!Common.EMPTY.equals(postfix)) {
                if (Common.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
                    return readXls(path);
                } else if (Common.OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
                    return readXlsx(path);
                }
            } else {
                System.out.println(path + Common.NOT_EXCEL_FILE);
            }
        }
        return null;
    }

    /**
     * Read the Excel 2010
     * @param path the path of the excel file
     * @return
     * @throws IOException
     */
    public List<Student> readXlsx(String path) throws IOException {
        System.out.println(Common.PROCESSING + path);
        InputStream is = new FileInputStream(path);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        Student student = null;
        List<Student> list = new ArrayList<Student>();
        // Read the Sheet
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            // Read the Row
            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow != null) {
                    student = new Student();
                    XSSFCell no = xssfRow.getCell(0);
                    XSSFCell name = xssfRow.getCell(1);
                    XSSFCell age = xssfRow.getCell(2);
                    XSSFCell score = xssfRow.getCell(3);
                    student.setNo(getValue(no));
                    student.setName(getValue(name));
                    student.setAge(getValue(age));
                    student.setScore(Float.valueOf(getValue(score)));
                    list.add(student);
                }
            }
        }
        return list;
    }
    
    /**
     * Read the Excel 2003-2007
     * @param path the path of the Excel
     * @return
     * @throws IOException
     */
    public List<Student> readXls(String path) throws IOException {
        System.out.println(Common.PROCESSING + path);
        InputStream is = new FileInputStream(path);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        Student student = null;
        List<Student> list = new ArrayList<Student>();
        // Read the Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // Read the Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
                    student = new Student();
                    HSSFCell no = hssfRow.getCell(0);
                    HSSFCell name = hssfRow.getCell(1);
                    HSSFCell age = hssfRow.getCell(2);
                    HSSFCell score = hssfRow.getCell(3);
                    student.setNo(getValue(no));
                    student.setName(getValue(name));
                    student.setAge(getValue(age));
                    student.setScore(Float.valueOf(getValue(score)));
                    list.add(student);
                }
            }
        }
        return list;
    }

    @SuppressWarnings("static-access")
    private String getValue(XSSFCell xssfRow) {
    	String returnVal = "";
    	if (null !=xssfRow)
    	{
    		xssfRow.setCellType(xssfRow.CELL_TYPE_STRING);
    		returnVal = String.valueOf(xssfRow.getStringCellValue());
    	}
    	return returnVal;
    	
    /*	
        if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
            return String.valueOf(xssfRow.getBooleanCellValue());
        } else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
            return String.valueOf(xssfRow.getNumericCellValue());
        } else {
            return String.valueOf(xssfRow.getStringCellValue());
        }*/
    }

    @SuppressWarnings("static-access")
    private String getValue(HSSFCell hssfCell) {
        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
            return String.valueOf(hssfCell.getNumericCellValue());
        } else {
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }
}
