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

import br.com.petz.partner.entity.Pet;
import br.com.petz.partner.model.PetModel;
import br.com.petz.partner.service.PetzPetService;

@RestController
@RequestMapping("/petz/api/partner/pet")
public class PetzPetController {
	
	@Autowired
	PetzPetService petzPetService;

	@PostMapping
	public ResponseEntity<PetModel> updatePet(@Valid @RequestBody PetModel petzPetModel) {
		return petzPetService.save(petzPetModel);
	}

	@GetMapping
	public ResponseEntity<?> getAllPets() {
		return ResponseEntity.ok(petzPetService.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<PetModel> getPetById(@PathVariable("id") String id) {
		return ResponseEntity.ok(petzPetService.findById(UUID.fromString(id)).fromEntityToModel());
	}

	@PutMapping("/{id}")
	public ResponseEntity<PetModel> updatePet(@PathVariable("id") String id,
			@Valid @RequestBody PetModel petModel) {

		Pet petzPet = new Pet().fromModelToEntity(petModel).toBuilder()
				.id(UUID.fromString(id)).build();
		return ResponseEntity.ok(petzPetService.update(petzPet).fromEntityToModel());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePetById(@PathVariable("id") String id) {
		petzPetService.delete(UUID.fromString(id));
		return ResponseEntity.noContent().build();
	}
}
