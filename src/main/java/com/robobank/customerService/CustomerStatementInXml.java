package com.robobank.customerService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.robobank.entity.TransactionDetails;
import com.robobank.utils.CustomerMappingUtils;

public class CustomerStatementInXml implements CustomerStatement {

	public List<TransactionDetails> process(String filePath) {

		List<TransactionDetails> transactionDetails = new ArrayList<>();
		CustomerMappingUtils customerMappingUtils = new CustomerMappingUtils();
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new File(filePath));
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("record");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					String[] recordDeatils = new String[7];
					recordDeatils[0] = eElement.getAttribute("reference");
					recordDeatils[1] = eElement.getElementsByTagName("accountNumber").item(0).getTextContent();
					recordDeatils[2] = eElement.getElementsByTagName("description").item(0).getTextContent();
					recordDeatils[3] = eElement.getElementsByTagName("startBalance").item(0).getTextContent();
					recordDeatils[4] = eElement.getElementsByTagName("mutation").item(0).getTextContent();
					recordDeatils[5] = eElement.getElementsByTagName("endBalance").item(0).getTextContent();
					transactionDetails.add(customerMappingUtils.mapToTransactionDetails(recordDeatils));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return transactionDetails;
	}

}
