package br.com.petz.partner.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.petz.partner.entity.Customer;
import br.com.petz.partner.exception.PetzCustomerNotFoundException;
import br.com.petz.partner.repository.CustomerRepository;
import lombok.var;

class CustomerServiceTest {

	private CustomerRepository customerRepository = mock(CustomerRepository.class);
	private PetzCustomerService petzCustomerService = mock(PetzCustomerService.class);


	@Test
    public void shouldSaveCustomer() {
        Customer expected = customer();
                
       
        when(customerRepository.save(any())).thenReturn(expected);
        var actual = petzCustomerService.save(expected.fromEntityToModel());
        Assertions.assertThat(actual)
            .isEqualTo(expected.fromEntityToModel());
    }	

	@Test
	public void shouldfindById() {
		var expected = customer();
		when(customerRepository.findById(any())).thenReturn(Optional.of(expected));
		var actual = petzCustomerService.findById(UUID.randomUUID());
		Assertions.assertThat(actual).isEqualTo(expected.fromEntityToModel());
	}

	@Test
	public void shouldThrowWhenFindByIdReturnEmpty() {
		when(customerRepository.findById(any())).thenReturn(Optional.empty());
		org.junit.jupiter.api.Assertions.assertThrows(PetzCustomerNotFoundException.class,
				() -> petzCustomerService.findById(UUID.randomUUID()));
	}

	@Test
	public void shouldDeleteById() {
		UUID id = UUID.randomUUID();
		petzCustomerService.delete(id);
		verify(customerRepository, times(1)).deleteById(id);
	}

	private Customer customer() {
		return Customer.builder().streetName("StreetName").number("123").neighbourhood("123").city("123").state("13")
				.country("123").build();
	}
}
