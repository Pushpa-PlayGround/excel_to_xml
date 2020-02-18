package com.ms.ndcinstructions.utils;


import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.ms.ndcinstructions.transfer.BlobStorageExcelData;



@Service
public class ExcelUtils {

	/** The logger. */
	static Logger logger;
	
	public static final String REGEX = "\\.0*$";

	private ExcelUtils() {
	}

	public static  Map<String,List<BlobStorageExcelData>> getExcelData() throws IOException {
		List<BlobStorageExcelData> excelldataList = new ArrayList<BlobStorageExcelData>();
		Map<String,List<BlobStorageExcelData>> excelDataMap = new HashMap<String,List<BlobStorageExcelData>>();
		final String FILE_PATH = "D:\\Eclipse_PlayGround\\ndcinstructions\\src\\main\\resources\\Input_Final1.xlsx";
		FileInputStream fis = null;
		fis = new FileInputStream(FILE_PATH);
//		InputStream inputStream = new ByteArrayInputStream(FILE_PATH);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
		for (Row row : sheet) {
			if (row.getRowNum() == 0)
				continue;
			BlobStorageExcelData excelData = new BlobStorageExcelData();
			for (Cell cell : row) {
				int columnIndex = formulaEvaluator.evaluateInCell(cell).getColumnIndex();
				switch (columnIndex) {
				case 0:
					if (cell.getCellTypeEnum() == CellType.STRING) {
						String value1 = String.valueOf(cell.getStringCellValue().trim());
						if (!value1.isEmpty()) {
							excelData.setUpcEan(value1);
						} else {
							excelData.setUpcEan("");
						}
					}
					break;
				case 1:
					if (cell.getCellTypeEnum() == CellType.NUMERIC) {
					
						String value2 = String.valueOf(cell.getNumericCellValue());
						if (!value2.isEmpty()) {
							if (value2.contains(".")) {
								excelData.setUpt(Integer.valueOf(value2.replaceAll(REGEX, "")));
							} else {
								excelData.setUpt(Integer.valueOf((value2)));
							}
						} 
					}
					break;
				case 2:
					if (cell.getCellTypeEnum() == CellType.NUMERIC) {
						String value3 = String.valueOf(cell.getNumericCellValue());
						if (!value3.isEmpty()) {
							if (value3.contains(".")) {
								excelData.setQuantity(Integer.valueOf(value3.replaceAll(REGEX, "")));
							} else {
								excelData.setQuantity(Integer.valueOf(value3));
							}
						} 
					}
					break;
				// To validate Date part 
				case 3:
					if (cell.getCellTypeEnum() == CellType.STRING) {
						String value4 = String.valueOf(cell.getStringCellValue().trim());
						if (!value4.isEmpty()) {
							excelData.setReceiptDate(value4);
						}else {
							excelData.setReceiptDate("");
						}
					} else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
						if(DateUtil.isCellDateFormatted(cell)) {
							SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
							String value4 = dateFormat.format(cell.getDateCellValue());
							if (!value4.isEmpty()) {
								excelData.setReceiptDate(value4);
							}else {
								excelData.setReceiptDate("");
							}
						} else {
							excelData.setReceiptDate("");
						}
					}
					break;
					
				case 4:
					if (cell.getCellTypeEnum() == CellType.STRING) {
						String value5 = String.valueOf(cell.getStringCellValue().trim());
						if (!value5.isEmpty()) {
							if (value5.contains(".")) {
								excelData.setStoreId(value5.replaceAll(REGEX, ""));
							} else {
								excelData.setStoreId(value5);
							}
						} else {
							excelData.setStoreId("");
						}
					} else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
						double d = cell.getNumericCellValue();
						long x = (long) d;
						String value2 = String.valueOf(x);
						if (!value2.isEmpty()) {
							if (value2.contains(".")) {
								excelData.setStoreId(value2.replaceAll(REGEX, ""));
							} else {
								excelData.setStoreId(value2);
							}
						} else {
							excelData.setStoreId("");
						}
					} 
					break;
				case 5:
					if (cell.getCellTypeEnum() == CellType.STRING) {
						String value6 = String.valueOf(cell.getStringCellValue().trim());
						if (!value6.isEmpty()) {
							excelData.setIntoStoreDate(value6);
						}
					else {
							excelData.setIntoStoreDate("");
						}
					}else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
						if(DateUtil.isCellDateFormatted(cell)) {
							SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
							String value6 = dateFormat.format(cell.getDateCellValue());
							if (!value6.isEmpty()) {
								excelData.setIntoStoreDate(value6);
							}else {
								excelData.setIntoStoreDate("");
							}
						} else {
							excelData.setIntoStoreDate("");
						}
					}
					break;
				case 6:
					if (cell.getCellTypeEnum() == CellType.STRING) {
						String value7 = String.valueOf(cell.getStringCellValue().trim());
						if (!value7.isEmpty()) {
							excelData.setAllocationType(value7);
						}
					else {
							excelData.setAllocationType("");
						}
					}
					break;
				case 7:
					if (cell.getCellTypeEnum() == CellType.NUMERIC) {
						String value8 = String.valueOf(cell.getNumericCellValue());
						if (!value8.isEmpty()) {
							if (value8.contains(".")) {
								excelData.setAllocatedQuantity(Integer.valueOf((value8.replaceAll(REGEX, ""))));
							} else {
								excelData.setAllocatedQuantity(Integer.valueOf(value8));
							}
						} 
					}
					break;
				case 8:
					if (cell.getCellTypeEnum() == CellType.STRING) {
						String value7 = String.valueOf(cell.getStringCellValue().trim());
						if (!value7.isEmpty()) {
							excelData.setCrossDock(value7);
						}
					
					} else if (cell.getCellTypeEnum() == CellType.BLANK) {
						String value7 = String.valueOf(cell.getStringCellValue().trim());
						if (!value7.isEmpty()) {
							excelData.setCrossDock(value7);
						} 
					}
					break;	
				}
			}
			if (null != excelData.getUpcEan()) {
				//if UPC already exists update the same value 
				if(excelDataMap.containsKey(excelData.getUpcEan())) {
					List<BlobStorageExcelData> data = excelDataMap.get(excelData.getUpcEan());
					data.add(excelData);
					excelDataMap.replace(excelData.getUpcEan(), data);
				} else {
					excelldataList = new ArrayList<>();
					excelldataList.add(excelData);
					excelDataMap.put(excelData.getUpcEan(), excelldataList);
				}
			}
		}
		workbook.close();
		fis.close();
		return excelDataMap;
	}

}
