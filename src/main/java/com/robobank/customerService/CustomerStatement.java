package com.robobank.customerService;

import java.util.List;

import com.robobank.entity.TransactionDetails;

public interface CustomerStatement {

	List<TransactionDetails> process(String filePath);
}
