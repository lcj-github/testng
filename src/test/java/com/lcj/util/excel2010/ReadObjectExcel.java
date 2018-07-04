package com.lcj.util.excel2010;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadObjectExcel {

	public static boolean isBlankRow(XSSFRow row) {
		if (row == null)
			return true;
		boolean result = true;
		for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
			XSSFCell cell = row.getCell(i, XSSFRow.RETURN_BLANK_AS_NULL);
			String value = "";
			if (cell != null) {
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
				// case Cell.CELL_TYPE_BLANK:
				// break;
				default:
					break;
				}

				if (!value.trim().equals("")) {
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
		Map<String, String> strMap = null;
		List<String> reqList = new ArrayList<String>();

		// Read the Sheet
		for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
			if (xssfSheet == null) {
				continue;
			}

			// 获取标题行
			XSSFRow xssfTitle = xssfSheet.getRow(0);
			List<String> titleList = new ArrayList<String>();
			if (null != xssfTitle) {
				int coloumNum = xssfTitle.getPhysicalNumberOfCells();
				for (int coloumIndex = 0; coloumIndex < coloumNum; coloumIndex++) {
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
                		String titleColum= titleList.get(colum); 
                		
                		if (!titleColum.equals("#isRun") && !titleColum.equals("#description"))
                		
                		{                			
                			
                			strMap.put(titleColum, getValue(xssfRow.getCell(colum)));
                		} 
                	}	                    
                	reqList.add(obtainkeyVal(strMap));
                }
            }

		}
		return reqList;
	}

	public static String obtainkeyVal(Map<String, String> strMap)
			throws Exception {
		int mapSize = strMap.size();
		String conStr = "{";
		Iterator<String> keys = strMap.keySet().iterator();
		while (keys.hasNext()) {
			mapSize--;
			String keyMap = (String) keys.next();
			String value = strMap.get(keyMap);
			/*if ((keyMap.equals("mktcdFrom") || keyMap.equals("mktcdGo"))
					&& value.equals("")) {
				value = "1200";
			}*/		
			
			conStr = conStr + "\"";
			conStr = conStr.concat(keyMap);
			conStr = conStr + "\"";
			conStr = conStr.concat(":");
			conStr = conStr + "\"";
			conStr = conStr.concat(value);
			conStr = conStr + "\"";
			if (mapSize != 0) {
				conStr = conStr.concat(",");
			}
		}
		conStr = conStr.concat("}");
		conStr = conStr.replaceAll("\"", "\'");
		return conStr;
	}

	

	@SuppressWarnings("static-access")
	private String getValue(XSSFCell xssfRow) {
		String returnVal = "";
		if (null != xssfRow) {
			xssfRow.setCellType(xssfRow.CELL_TYPE_STRING);
			returnVal = String.valueOf(xssfRow.getStringCellValue());
		}
		return returnVal;

		/*
		 * if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) { return
		 * String.valueOf(xssfRow.getBooleanCellValue()); } else if
		 * (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) { return
		 * String.valueOf(xssfRow.getNumericCellValue()); } else { return
		 * String.valueOf(xssfRow.getStringCellValue()); }
		 */
	}

	/*
	 * @SuppressWarnings("static-access") private String getValue(HSSFCell
	 * hssfCell) { if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
	 * return String.valueOf(hssfCell.getBooleanCellValue()); } else if
	 * (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) { return
	 * String.valueOf(hssfCell.getNumericCellValue()); } else { return
	 * String.valueOf(hssfCell.getStringCellValue()); } }
	 */
}
