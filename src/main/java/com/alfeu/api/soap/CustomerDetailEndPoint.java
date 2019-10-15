package com.alfeu.api.soap;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import br.com.alfeu.CustomerDetail;
import br.com.alfeu.GetCustomerDetailRequest;
import br.com.alfeu.GetCustomerDetailResponse;

@Endpoint
public class CustomerDetailEndPoint {

	@PayloadRoot(namespace = "http://alfeu.com.br", localPart = "GetCustomerDetailRequest")
	@ResponsePayload
	public GetCustomerDetailResponse processCustomerDetailRequest(@RequestPayload GetCustomerDetailRequest req) {
		GetCustomerDetailResponse response = new GetCustomerDetailResponse();
		
		CustomerDetail customerDetail = new CustomerDetail(); 
		customerDetail.setId(1);;
		customerDetail.setNome("Vitor");
		customerDetail.setPhone("888");
		customerDetail.setEmail("vitor@email.com");
		
		response.setCustomerDatail(customerDetail);
		return response;
	}
	
}