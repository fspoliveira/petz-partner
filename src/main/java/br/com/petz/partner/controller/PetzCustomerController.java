package br.com.petz.partner.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.petz.partner.entity.Customer;
import br.com.petz.partner.model.CustomerModel;
import br.com.petz.partner.service.PetzCustomerService;

@RestController
@RequestMapping("/petz/api/partner/customer")
public class PetzCustomerController {

	@Autowired
	PetzCustomerService petzCustomerService;

	@PostMapping
	public ResponseEntity<CustomerModel> updateCustomer(@Valid @RequestBody CustomerModel petzCustomerModel) {
		return petzCustomerService.save(petzCustomerModel);
	}

	@GetMapping
	public ResponseEntity<?> getAllCustomers() {
		return ResponseEntity.ok(petzCustomerService.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<CustomerModel> getCustomerById(@PathVariable("id") String id) {
		return ResponseEntity.ok(petzCustomerService.findById(UUID.fromString(id)).fromEntityToModel());
	}

	@PutMapping("/{id}")
	public ResponseEntity<CustomerModel> updateCustomer(@PathVariable("id") String id,
			@Valid @RequestBody CustomerModel stAddressModel) {

		Customer petzCustomer = new Customer().fromModelToEntity(stAddressModel).toBuilder()
				.id(UUID.fromString(id)).build();
		return ResponseEntity.ok(petzCustomerService.update(petzCustomer).fromEntityToModel());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCustomerById(@PathVariable("id") String id) {
		petzCustomerService.delete(UUID.fromString(id));
		return ResponseEntity.noContent().build();
	}
}
