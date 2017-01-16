package com.sn.utils.excel;

import java.awt.Font;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.sn.utils.base.SystemConfig;

/**
 * excel工具（.xlsx文件）
 */
public class XssfExcelUtil extends ExcelConfig {
	/**
	 * excel读取
	 * 
	 * @param inputStream
	 * @param excelMap
	 * @param sheetList
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> readExcelXSSF(InputStream inputStream,
			Map<String, Object> excelMap, List<Map<String, String>> sheetList,
			List<Map<String, String>> errorSheetList,
			StringBuilder errorSheetLog) throws Exception {
		// 获取每个Sheet表
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		inputStream.close();
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			XSSFSheet sheet = workbook.getSheetAt(i);
			for (int j = 0; j < sheet.getPhysicalNumberOfRows(); j++) {
				if (HAS_TITLE && j == 0) {// 第一行为标题不做处理
					continue;
				}
				XSSFRow row = sheet.getRow(j);
				Map<String, String> map = new HashMap<String, String>();
				int errorTime = 0;
				for (int k = 0; k < FIELD_NAEMS_EN.length; k++) {
					XSSFCell cell = row.getCell(k);
					String cellValue = getCellContent(cell).trim();
					boolean flag = false;
					for (String cnns : FIELD_NOT_NULLS) {
						if (Integer.parseInt(cnns.trim()) == k
								&& BaseUtils.isEmpty(cellValue)) {
							flag = true;
							break;
						}
					}
					if (flag) {
						if (errorTime == 0) {
							errorSheetLog.append((j + 1) + READ_FAIL_MSG);
						}
						errorSheetLog
								.append(FIELD_NAEMS_CN[k] + CHECK_NULL_MSG);
						errorTime++;
					}
					if (!BaseUtils.isEmpty(cellValue)) {
						String cloRegexp = SystemConfig
								.getProperty(REGEXP_PREFIX + FIELD_NAEMS_EN[k]);
						if (!BaseUtils.isEmpty(cloRegexp)) {
							if (!BaseUtils.regexp(cellValue, cloRegexp)) {
								if (errorTime == 0) {
									errorSheetLog.append((j + 1)
											+ READ_FAIL_MSG);
								}
								errorSheetLog.append(FIELD_NAEMS_CN[k]
										+ cellValue + CHECK_FAIL_MSG);
								cellValue += ERROR_FLAG;
								errorTime++;
							}
						}
					}
					map.put(FIELD_NAEMS_EN[k], cellValue);
				}
				if (errorTime == 0) {
					sheetList.add(map);
				} else {
					errorSheetList.add(map);
					errorSheetLog.append("</br>");
				}
			}
		}
		excelMap.put("sheetList", sheetList);
		excelMap.put("errorSheetList", errorSheetList);
		excelMap.put("errorSheetLog", errorSheetLog);
		return excelMap;
	}

	/**
	 * 生成excel文件
	 * 
	 * @return
	 * @throws Exception
	 */
	public static BufferedInputStream writeExcelXSSF(
			List<Map<String, String>> sheetList) throws Exception {
		XSSFWorkbook workbook = new XSSFWorkbook();

		writeRow(workbook, workbook.createSheet(), sheetList);

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		workbook.write(bos);
		return new BufferedInputStream(new ByteArrayInputStream(
				bos.toByteArray()));
	}

	/**
	 * 生成excel文件
	 * 
	 * @param file
	 * @throws Exception
	 */
	public static void writeExcelXSSFFile(File file,
			List<Map<String, String>> sheetList) throws Exception {
		XSSFWorkbook workbook = new XSSFWorkbook();

		writeRow(workbook, workbook.createSheet(), sheetList);

		// 将数据写到磁盘上
		FileOutputStream fos = new FileOutputStream(file);
		try {
			workbook.write(new FileOutputStream(file));
		} finally {
			if (fos != null) {
				fos.close(); // 不管是否有异常发生都关闭文件输出流
			}
		}
	}

	/**
	 * 生成表格内容
	 * 
	 * @param workbook
	 * @param sheet
	 * @param sheetList
	 */
	private static void writeRow(XSSFWorkbook workbook, XSSFSheet sheet,
			List<Map<String, String>> sheetList) {
		if (HAS_TITLE) {
			setRowTitle(workbook, sheet);
		}
		if (sheetList != null) {
			for (int i = 0; i < sheetList.size(); i++) {
				Map<String, String> map = sheetList.get(i);
				XSSFRow row = HAS_TITLE ? sheet.createRow(i + 1) : sheet
						.createRow(i);
				// 遍历属性列表
				for (int j = 0; j < FIELD_NAEMS_CN.length; j++) {
					XSSFCell cell = row.createCell(j);
					cell.setCellType(XSSFCell.CELL_TYPE_STRING);
					String cellValue = BaseUtils.toString(map
							.get(FIELD_NAEMS_EN[j]));
					if (cellValue.contains(ERROR_FLAG)) {
						cellValue = cellValue.replace(ERROR_FLAG, "");
						XSSFCellStyle cellStyle = workbook.createCellStyle();
						XSSFFont font = workbook.createFont();
						font.setColor((short) 10);
						cellStyle.setFont(font);
						cell.setCellStyle(cellStyle);
					}
					cell.setCellValue(cellValue);
				}
			}
		}
	}

	/**
	 * 设置表格标题
	 * 
	 * @param workbook
	 * @param sheet
	 */
	private static void setRowTitle(XSSFWorkbook workbook, XSSFSheet sheet) {
		XSSFRow headRow = sheet.createRow(0);
		// 添加表格标题
		for (int i = 0; i < FIELD_NAEMS_CN.length; i++) {
			XSSFCell cell = headRow.createCell(i);
			cell.setCellType(XSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(FIELD_NAEMS_CN[i]);
			// 设置字体加粗
			XSSFCellStyle cellStyle = workbook.createCellStyle();
			XSSFFont font = workbook.createFont();
			font.setBoldweight((short) Font.BOLD);
			for (String cnns : FIELD_NOT_NULLS) {
				if (Integer.parseInt(cnns.trim()) == i) {
					font.setColor((short) 10);
				}
			}
			cellStyle.setFont(font);
			cell.setCellStyle(cellStyle);
		}
	}

	/**
	 * 读取单元内容
	 * 
	 * @param cell
	 * @return
	 */
	private static String getCellContent(XSSFCell cell) {
		StringBuffer buffer = new StringBuffer();
		switch (cell.getCellType()) {
		case XSSFCell.CELL_TYPE_NUMERIC: // 数字
			int dataFormat1 = cell.getCellStyle().getDataFormat();
			if (0xe <= dataFormat1 && dataFormat1 <= 0x16) {// 时间格式数据
				buffer.append(BaseUtils.formatDate(cell.getDateCellValue(),
						DEFAULT_DATE_FAMAT));
			} else {
				buffer.append((new BigDecimal(String.valueOf(cell
						.getNumericCellValue()))).toPlainString());
			}
			break;
		case XSSFCell.CELL_TYPE_BOOLEAN: // 布尔
			buffer.append(cell.getBooleanCellValue());
			break;
		case XSSFCell.CELL_TYPE_FORMULA: // 公式
			buffer.append(cell.getCellFormula());
			break;
		case XSSFCell.CELL_TYPE_STRING: // 字符串
			buffer.append(cell.getStringCellValue());
			break;
		case XSSFCell.CELL_TYPE_BLANK: // 空值
		case XSSFCell.CELL_TYPE_ERROR: // 故障
		default:
			break;
		}
		return buffer.toString();
	}

}
