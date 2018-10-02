package com.robobank.customerService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.robobank.entity.TransactionDetails;
import com.robobank.utils.CustomerMappingUtils;

public class CustomerStatementInCsv implements CustomerStatement {

	public List<TransactionDetails> process(String filePath) {
		
		List<TransactionDetails> transactionDetails = new ArrayList<>();
		CustomerMappingUtils customerMappingUtils = new CustomerMappingUtils();
		
		String line = "";
		String cvsSplitBy = ",";
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

			//just to skip the headers
			String headerLine = br.readLine();
			while ((line = br.readLine()) != null) {
				String[] recordDeatils = line.split(cvsSplitBy);
				transactionDetails.add(customerMappingUtils.mapToTransactionDetails(recordDeatils));
			}	
		} catch (IOException e) {
			e.printStackTrace();
		}
		return transactionDetails;
	}

}
