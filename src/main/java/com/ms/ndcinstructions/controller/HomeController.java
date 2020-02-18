package com.ms.ndcinstructions.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.ndcinstructions.service.NdcInstructionGenerationService;
import com.ms.ndcinstructions.transfer.BlobStorageExcelData;
import com.ms.ndcinstructions.utils.ExcelUtils;

@RestController
public class HomeController {
	@Autowired
	ExcelUtils utils;
	
	@Autowired
	NdcInstructionGenerationService ndcInstructionGenerationService;
	
//	@GetMapping("/test")
//	public String test() {
//		System.out.println("Testing");
//		try {
//			System.out.println("size:" + utils.getExcelData().size());
//			System.out.println("Print excelData:" + utils.getExcelData());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return "success";
//	}
	
	@GetMapping("/test")
	public String test() throws IOException, InterruptedException {
	 List<BlobStorageExcelData> prodResponse = new ArrayList<>();
	 Map <String,List<BlobStorageExcelData>>  excelMap = new HashMap<>();
	 System.out.println("Testing");
	 try {
		 //prodResponse = ExcelUtils.getExcelData();
		 excelMap = ExcelUtils.getExcelData();
	  System.out.println(" Map size:" + excelMap.size());
	  System.out.println("Print excelMap:" + excelMap);
	 } catch (IOException e) {
	  // TODO Auto-generated catch block
	  e.printStackTrace();
	 }
	 
	String sapResponseXMLString = ndcInstructionGenerationService.generateNdcInstruction(excelMap);
	writeToFile(sapResponseXMLString);
//	 System.out.println("Print excelData:" + sapResponseXMLString);
	 return "Success";
	}
	
	public void writeToFile(String writeXml) throws IOException, InterruptedException {
		
		File file = new File("D:\\Lets Play\\testFile.txt");
		boolean exists = file.exists();
		  
		//Create the file
		if (!exists) {
		if (file.createNewFile())
		{
		    System.out.println("File is created!");
		  //Write Content
			FileWriter writer = new FileWriter(file);
			writer.write(writeXml);
			writer.close();
		} 
		} else {
		    System.out.println("File already exists.");
		    //so create new file
		    TimeUnit.SECONDS.sleep(2);
		    DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
			Date date = new Date();
			String suffix = dateFormat.format(date);
		    String fileName = "D:\\Lets Play\\" + "testFile_"  + suffix + ".txt";
		    File file1 = new File(fileName);
		    FileWriter writer = new FileWriter(file1);
			writer.write(writeXml);
			writer.close();
		    
		} 	 
				
	}
}

