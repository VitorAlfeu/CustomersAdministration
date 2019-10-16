package com.alfeu.api.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.alfeu.api.bean.Customer;
import com.alfeu.api.service.CustomerDetailService;

import br.com.alfeu.CustomerDetail;
import br.com.alfeu.GetAllCustomerDetailRequest;
import br.com.alfeu.GetAllCustomerDetailResponse;
import br.com.alfeu.GetCustomerDetailRequest;
import br.com.alfeu.GetCustomerDetailResponse;

@Endpoint
public class CustomerDetailEndPoint {

	@Autowired
	CustomerDetailService service;
	
	@PayloadRoot(namespace = "http://alfeu.com.br", localPart = "GetCustomerDetailRequest")
	@ResponsePayload
	public GetCustomerDetailResponse processCustomerDetailRequest(@RequestPayload GetCustomerDetailRequest req) throws Exception{
//		GetCustomerDetailResponse response = new GetCustomerDetailResponse();
//		
//		CustomerDetail customerDetail = new CustomerDetail(); 
//		customerDetail.setId(1);;
//		customerDetail.setName("Vitor");
//		customerDetail.setPhone("888");
//		customerDetail.setEmail("vitor@email.com");
//		
//		response.setCustomerDatail(customerDetail);
		
		Customer customer = service.findById(req.getId());
		if(customer == null) {
			throw new Exception("Customer Id inválido: " + req.getId());
		}
		
		return convertToGetCustomerDetailResponse(customer);
	}
	
	private GetCustomerDetailResponse convertToGetCustomerDetailResponse(Customer customer) {
		GetCustomerDetailResponse resp = new GetCustomerDetailResponse();
		resp.setCustomerDatail(convertToCustomerDetail(customer));
		
		return resp;
	}
	
	private CustomerDetail convertToCustomerDetail(Customer customer) {
		CustomerDetail customerDetail = new CustomerDetail();
		customerDetail.setId(customer.getId());
		customerDetail.setName(customer.getName());
		customerDetail.setPhone(customer.getPhone());
		customerDetail.setEmail(customer.getEmail());
		
		return customerDetail;
	}
	
	@PayloadRoot(namespace = "http://alfeu.com.br", localPart = "GetAllCustomerDetailRequest")
	@ResponsePayload GetAllCustomerDetailResponse processGetAllCustomerDetailResponse(@RequestPayload GetAllCustomerDetailRequest req) {
		List<Customer> customer = service.findAll();
		
		return convertToGetAllCustomerDetailResponse(customer);
	}
	
	
	private GetAllCustomerDetailResponse convertToGetAllCustomerDetailResponse(List<Customer> customers) {
		GetAllCustomerDetailResponse resp = new GetAllCustomerDetailResponse();
		customers.stream().forEach(c -> resp.getCustomerDatail().add(convertToCustomerDetail(c)));
		
		return resp;
	}
	
}