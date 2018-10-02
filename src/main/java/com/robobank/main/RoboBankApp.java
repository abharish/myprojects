package com.robobank.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Scanner;

import com.robobank.customerService.CustomerStatement;
import com.robobank.customerService.CustomerStatementInCsv;
import com.robobank.customerService.CustomerStatementInXml;
import com.robobank.entity.TransactionDetails;
import com.robobank.utils.CustomerMappingUtils;

public class RoboBankApp {
	
	private String fileFormat;
	private String filePath;
	public static final String XML_FORMAT = "xml";
	public static final String CSV_FORMAT = "csv";
	
	
	public static void main(String[] args) throws Exception {
	
		RoboBankApp roboBankApp = new RoboBankApp();
		System.out.println("***************Welcome to Robo Bank*************");
		Scanner scan = new Scanner(System.in);
		roboBankApp.readFileFormatDetails(roboBankApp,scan);	
		roboBankApp.readFilePathDetails(roboBankApp, scan);
		List<TransactionDetails> transactionDetails ;
		List<String> duplicateReference ;
		List<String> validationErrors ;
		
		switch (roboBankApp.getFileFormat()) {
		case XML_FORMAT:
			CustomerStatement customerStatementInXml = new CustomerStatementInXml();
			transactionDetails = customerStatementInXml.process(roboBankApp.getFilePath());
			break;
		case CSV_FORMAT :
			CustomerStatement customerStatementInCsv = new CustomerStatementInCsv();
			transactionDetails = customerStatementInCsv.process(roboBankApp.getFilePath());
			break;
		default:
			throw new Exception("Format not supported");
		}
		
		CustomerMappingUtils customerMappingUtils = new CustomerMappingUtils();
		duplicateReference = customerMappingUtils.checkForDuplicate(transactionDetails);	
		validationErrors = customerMappingUtils.validateBalance(transactionDetails);
		roboBankApp.writeOutputDetails(duplicateReference, validationErrors);
		System.out.println("Results have been written to file RoboBank_result.txt, please check in Robobank folder");
	}
	
	void readFileFormatDetails(RoboBankApp roboBankApp,Scanner scan) {
		System.out.println("Please enter file format xml/csv");
		String fileFormat = scan.nextLine();
		
		if(fileFormat==null || fileFormat.trim().length()==0){
			System.out.println("File format is invalid");
			this.readFileFormatDetails(roboBankApp,scan);
		}else{
			roboBankApp.setFileFormat(fileFormat.toLowerCase());
		}
		
	}
	
	void readFilePathDetails(RoboBankApp roboBankApp,Scanner scan) { 
		System.out.println("Please enter file path");
		String filePath = scan.nextLine();
		File f = new File(filePath);
		if(f.exists() && !f.isDirectory()) { 
		    roboBankApp.setFilePath(filePath);
		}else{
			System.out.println("File doesn't exists");
			this.readFilePathDetails(roboBankApp, scan);	
		}
	}
	
	
	void writeOutputDetails(List<String> duplicateReference,List<String> validationErrors) {
		PrintWriter writer;
		try {
			writer = new PrintWriter("RoboBank_result.txt", "UTF-8");
			writer.println("Rabobank Customer Statement Processor results:");
			writer.println(duplicateReference.size()>0?"Duplicate Referecnce :":"No Duplicate Referecnce in the file");
			duplicateReference.forEach(writer::println);
			writer.println(validationErrors.size()>0?"Validation Errors :":"No Validation Errors in the file");
			validationErrors.forEach(writer::println);	
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public String getFileFormat() {
		return fileFormat;
	}

	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
}
