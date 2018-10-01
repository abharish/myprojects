package com.robobank.customer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerMappingUtils {

	public TransactionDetails mapToTransactionDetails(String[] recordDeatils) {
		TransactionDetails transactionDetail = new TransactionDetails();
		transactionDetail.setAccountNumber(recordDeatils[1]);
		transactionDetail.setReferenceNumber(Long.parseLong(recordDeatils[0]));
		transactionDetail.setDescription(recordDeatils[2]);
		transactionDetail.setStartBalance(BigDecimal.valueOf(Double.parseDouble(recordDeatils[3])));
		transactionDetail.setMutation(BigDecimal.valueOf(Double.parseDouble(recordDeatils[4])));
		transactionDetail.setEndBalance(BigDecimal.valueOf(Double.parseDouble(recordDeatils[5])));
		return transactionDetail;
	}

	public List<String> validateBalance(List<TransactionDetails> transactionDetails) {
		List<String> errorList = new ArrayList<>();
		for (TransactionDetails transactionDetail : transactionDetails) {

			if (transactionDetail.getEndBalance()
					.compareTo(transactionDetail.getStartBalance().add(transactionDetail.getMutation())) != 0) {
				errorList.add("ReferenceNumber: " + transactionDetail.getReferenceNumber() + " Description: "
						+ transactionDetail.getDescription());
			}
		}
		return errorList;
	}

	public List<String> checkForDuplicate(List<TransactionDetails> transactionDetails) {

		Map<Long, Integer> map = new HashMap<>();
		List<String> duplicateReference = new ArrayList<>();
		for (TransactionDetails transactionDetail : transactionDetails) {
			if (map.get(transactionDetail.getReferenceNumber()) == null) {
				map.put(transactionDetail.getReferenceNumber(), 1);

			} else {
				Integer integer = map.get(transactionDetail.getReferenceNumber());
				if (integer == 1) {
					duplicateReference.add("ReferenceNumber: " + transactionDetail.getReferenceNumber() + " Description: "
							+ transactionDetail.getDescription());
				}
			}
		}		
		return duplicateReference;
	}
}
