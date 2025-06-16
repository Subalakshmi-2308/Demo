package com.movie_rest_api.movie.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie_rest_api.movie.entity.Customer;
import com.movie_rest_api.movie.repository.CustomerRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerRepository customerrepository;
	
	public Customer saveCustomer(Customer customer) {
		return customerrepository.save(customer);
	}
	
	public Customer getCustomerById(Integer id) {
		return customerrepository.findById(id).orElse(null);
	}
	
	public List<Customer> getAllCustomer(){
		return customerrepository.findAll();
	}
	
	public void deleteCustomerById(Integer id) {
		customerrepository.deleteById(id);
	}
}
