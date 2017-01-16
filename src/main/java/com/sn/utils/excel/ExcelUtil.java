package com.sn.utils.excel;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * excel工具
 */
public class ExcelUtil extends ExcelConfig{
	/**
	 * excel读取
	 * @param comFiles
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> readExcel(CommonsMultipartFile comFiles) throws Exception {
		InputStream inputStream = comFiles.getInputStream();
		Map<String, Object> excelMap = new HashMap<String, Object>();
		List<Map<String, String>> sheetList = new ArrayList<Map<String, String>>();
		List<Map<String, String>> errorSheetList = new ArrayList<Map<String, String>>();
		StringBuilder errorSheetLog = new StringBuilder();
		if (comFiles.getOriginalFilename().contains(EXCEL_SUFFIX)) {// .xlsx文件
			return XssfExcelUtil.readExcelXSSF(inputStream, excelMap, sheetList, errorSheetList, errorSheetLog);
		} else {// .xls文件
			return HssfExcelUtil.readExcelHSSF(inputStream, excelMap, sheetList, errorSheetList, errorSheetLog);
		}
	}
	
	/**
	 * excel写入
	 * @param file
	 * @throws Exception
	 */
	public static void writeExcelFile(File file,List<Map<String, String>> sheetList) throws Exception {
		if (file.getPath().contains(EXCEL_SUFFIX)) {
			XssfExcelUtil.writeExcelXSSFFile(file,sheetList);
		}else{
			HssfExcelUtil.writeExcelHSSFFile(file,sheetList);
		}
	}

}
