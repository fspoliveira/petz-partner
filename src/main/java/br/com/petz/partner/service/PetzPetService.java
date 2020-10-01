package br.com.petz.partner.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.petz.partner.entity.Pet;
import br.com.petz.partner.exception.PetzCustomerNotFoundException;
import br.com.petz.partner.model.PetModel;
import br.com.petz.partner.repository.PetzPetRepository;

@Service
public class PetzPetService {
	
	@Autowired
	PetzPetRepository petzPetRepository;

	public ResponseEntity<PetModel> save(PetModel petModel) {
		Pet pet = petzPetRepository.save(new Pet().fromModelToEntity(petModel));
		return new ResponseEntity<>(pet.fromEntityToModel(), HttpStatus.CREATED);
	}

	public List<Pet> getAll() {
		return petzPetRepository.findAll();
	}

	public Pet findById(UUID id) {
		return petzPetRepository.findById(id).orElseThrow(PetzCustomerNotFoundException::new);
	}

	public void delete(UUID id) {
		petzPetRepository.deleteById(id);
	}

	public Pet update(Pet pet) {
		petzPetRepository.findById(pet.getId()).orElseThrow(PetzCustomerNotFoundException::new);
		return petzPetRepository.save(pet);
	}

}
