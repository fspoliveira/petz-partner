package br.com.petz.partner.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.petz.partner.entity.Customer;
import br.com.petz.partner.exception.PetzPetNotFoundException;
import br.com.petz.partner.model.CustomerModel;
import br.com.petz.partner.repository.CustomerRepository;

@Service
public class PetzCustomerService {
	
	@Autowired
	CustomerRepository petzCustomerRepository;

	public ResponseEntity<CustomerModel> save(CustomerModel customerModel) {
		Customer petzCustomer = petzCustomerRepository
				.save(new Customer().fromModelToEntity(customerModel));
		return new ResponseEntity<>(petzCustomer.fromEntityToModel(), HttpStatus.CREATED);
	}

	public List<Customer> getAll() {
		return petzCustomerRepository.findAll();
	}

	public Customer findById(UUID id) {
		return petzCustomerRepository.findById(id).orElseThrow(PetzPetNotFoundException::new);
	}

	public void delete(UUID id) {
		petzCustomerRepository.deleteById(id);
	}

	public Customer update(Customer petzCustomer) {
		petzCustomerRepository.findById(petzCustomer.getId()).orElseThrow(PetzPetNotFoundException::new);
		return petzCustomerRepository.save(petzCustomer);
	}
}
