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

	public CustomerModel fromEntityToModel() {

		CustomerModel petzCustomerModel = new CustomerModel();
		BeanUtils.copyProperties(this, petzCustomerModel);

		return petzCustomerModel;
	}

	public Customer fromModelToEntity(CustomerModel petzCustomerModel) {
		BeanUtils.copyProperties(petzCustomerModel, this);
		return this;
	}
}
