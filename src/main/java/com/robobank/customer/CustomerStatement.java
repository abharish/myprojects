package com.robobank.customer;

import java.util.List;

public interface CustomerStatement {

	List<TransactionDetails> process(String filePath);
}
