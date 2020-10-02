package br.com.petz.partner.repository;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.petz.partner.entity.Customer;
import br.com.petz.partner.exception.PetzCustomerNotFoundException;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "classpath:application-test.yml")
class StAddressRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void shouldSaveAddress() {
    	Customer expected = customerRepository.save(Customer.builder()
            .city("City")
            .complement("Complement")
            .country("Country")
            .neighbourhood("neighbourhood")
            .number("123")
            .state("asdasd")
            .streetName("street")           
            .build());

    	Customer  actual = testEntityManager.find(Customer.class, expected.getId());
        Assertions.assertThat(actual)
            .isEqualTo(expected);
    }

    @Test
    public void shouldFindById() {
    	Customer  expected = testEntityManager.persistAndFlush(Customer.builder()
            .city("City")
            .complement("Complement")
            .country("Country")            
            .neighbourhood("neighbourhood")
            .number("123")
            .state("asdasd")
            .streetName("street")          
            .build());

    	Customer actual = customerRepository.findById(expected.getId()).orElseThrow(PetzCustomerNotFoundException::new);
        Assertions.assertThat(actual)
            .isEqualTo(expected);
    }
}