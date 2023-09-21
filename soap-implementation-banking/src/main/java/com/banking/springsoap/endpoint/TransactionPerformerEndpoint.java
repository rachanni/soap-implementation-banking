package com.banking.springsoap.endpoint;


import com.banking.springsoap.gen.TransactionRequest;
import com.banking.springsoap.gen.TransactionResponse;
import com.banking.springsoap.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class TransactionPerformerEndpoint {

	private static final String NAMESPACE = "http://www.banking.com/springsoap/gen";

	@Autowired
	 private TransactionService service;

	@PayloadRoot(namespace = NAMESPACE, localPart = "transactionRequest")
	@ResponsePayload
	public TransactionResponse getTransactionRequest(@RequestPayload TransactionRequest request) {
		return service.doTransaction(request);
		
	}

}
