package br.com.petz.partner.model;

import java.util.UUID;

import br.com.petz.partner.entity.Customer;
import br.com.petz.partner.repository.CustomerRepository;

public class CustomerTest {

	public static Customer createSimpleData(CustomerRepository CustomerRepository) {
		return CustomerRepository.save(aSimpleCustomer());
	}

	
	public static Customer aSimpleCustomerWithId(UUID uuid) {
		return aSimpleCustomer().toBuilder().id(uuid).build();
	}

	public static Customer aSimpleCustomer() {
		return Customer.builder().streetName("Some Street name").city("Some City").country("BR").
				neighbourhood("Some neighbourhood").number("123").state("Some State")
				.complement("Some complement").build();
	}

	public static Customer aRealCustomer() {
		return Customer.builder().streetName("R. Campo Redondo").city("Campinas").country("BR").
					number("277").state("SP")
				.build();
	}
}
