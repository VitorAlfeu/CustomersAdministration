package com.alfeu.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.alfeu.api.bean.Customer;
import com.alfeu.api.helper.Status;

@Component
public class CustomerDetailService {

	private static List<Customer> customers = new ArrayList<>();
	
	static {
		Customer  customer1 = new Customer(1, "Bob", "99999", "bob@gmail.com");
		customers.add(customer1);
		
		Customer  customer2 = new Customer(2, "Mark", "88888", "mark@gmail.com");
		customers.add(customer2);
		
		Customer  customer3 = new Customer(3, "Jose", "77777", "jose@gmail.com");
		customers.add(customer3);
		
		Customer  customer4 = new Customer(4, "klay", "55555", "klay@gmail.com");
		customers.add(customer4);
	}
	
	public Customer findById(int id) {
		Optional<Customer> customerOptional =  customers.stream().filter(c -> c.getId() == id).findAny();
		
		if (customerOptional.isPresent()) {
			return customerOptional.get();
		}		
		return null;		
	}
	
	public List<Customer> findAll(){
		return customers;
	}

	public Status deleteById(int id) {
		Optional<Customer> customerOptional = customers.stream().filter(c -> c.getId() == id).findAny();
		
		if (customerOptional.isPresent()) {
			customers.remove(customerOptional.get());
			return Status.SUCCESS;
		}
		return Status.FAILURE;
	}
}
