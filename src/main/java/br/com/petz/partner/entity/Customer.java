package br.com.petz.partner.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.BeanUtils;

import br.com.petz.partner.model.CustomerModel;
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
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String name;

	private String cpfOrCnpj;

	private String dateOfBirth;	

	private String streetName;

	private String number;

	private String complement;

	private String neighbourhood;

	private String city;

	private String state;

	private String country;

	public CustomerModel fromEntityToModel() {

		CustomerModel customerModel = new CustomerModel();
		BeanUtils.copyProperties(this, customerModel);

		return customerModel;
	}

	public Customer fromModelToEntity(CustomerModel customerModel) {
		BeanUtils.copyProperties(customerModel, this);
		return this;
	}
}
