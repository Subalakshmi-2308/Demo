package com.movie_rest_api.movie.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.movie_rest_api.movie.entity.*;
import com.movie_rest_api.movie.repository.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

	@Mock
	private CustomerRepository customerRepository;
	
	
	@InjectMocks
	private CustomerService customerService;
	
	private Customer customer;
	
	@BeforeEach
	public void setUp() {
		customer=new Customer();
		customer.setCustomerName("Suba");
		customer.setEmail("suba@gmail.com");
	}
	@Test
	public void saveCustomer() {
		when(customerRepository.save(customer)).thenReturn(customer);
		
		Customer saveCustomer = customerService.saveCustomer(customer);
		
		assertThat(saveCustomer).isNotNull();
		assertThat(saveCustomer.getCustomerName()).isEqualTo("Suba");
		verify(customerRepository,times(1)).save(saveCustomer);
		
	}
	
	@Test
	public void getAllCustomer() {
		when(customerRepository.findAll()).thenReturn(Arrays.asList(customer));
		
		List<Customer> customers = customerService.getAllCustomer();
		
		assertThat(customers).isNotNull();
		assertThat(customers).hasSize(1);
		verify(customerRepository,times(1)).findAll();
	}
	@Test
	public void getCustomer() {
		when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
		
		Customer customerid = customerService.getCustomerById(1);
		
		assertThat(customerid).isNotNull();
		assertThat(customerid.getCustomerName()).isEqualTo("Suba");
		verify(customerRepository,times(1)).findById(1);
	}
	
	@Test
	public void deleteCustomer() {
		doNothing().when(customerRepository).deleteById(1);
		
		customerService.deleteCustomerById(1);;
		verify(customerRepository,times(1)).deleteById(1);;
	}
}
