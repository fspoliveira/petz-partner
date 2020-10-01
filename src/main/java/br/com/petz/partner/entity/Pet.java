package br.com.petz.partner.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.BeanUtils;

import br.com.petz.partner.model.PetModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Pet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String name;

	private String type;

	private String active;

	public PetModel fromEntityToModel() {

		PetModel petModel = new PetModel();
		BeanUtils.copyProperties(this, petModel);

		return petModel;
	}

	public Pet fromModelToEntity(PetModel petModel) {
		BeanUtils.copyProperties(petModel, this);
		return this;
	}

}
