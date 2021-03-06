package br.com.petz.partner.model;

import org.springframework.lang.NonNull;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class CustomerModel {

	@NonNull
	private String name;

	@NonNull
	private String cpfOrCNPJ;

	@NonNull
	private String dateOfBirth;

	@NonNull
	private String streetName;

	@NonNull
	private String number;
	
	private String complement;
	
	private String neighbourhood;

	@NonNull
	private String city;

	@NonNull
	private String state;

	@NonNull
	private String country;
}
