package br.com.petz.partner.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.petz.partner.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

}
