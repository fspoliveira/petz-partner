package br.com.petz.partner.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.petz.partner.entity.Pet;	

@Repository
public interface PetzPetRepository extends JpaRepository<Pet, UUID> {

}
