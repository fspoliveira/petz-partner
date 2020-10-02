package br.com.petz.partner.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.petz.partner.entity.Customer;
import br.com.petz.partner.model.CustomerModel;
import br.com.petz.partner.model.CustomerTest;
import br.com.petz.partner.model.ErrorModel;
import br.com.petz.partner.repository.CustomerRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Integration Testing for Customer API")
class CustomerControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private CustomerRepository repository;

	@Test
	@DisplayName("[Read] Testing the find all customers flow returning empty")
	public void whenIQueryForAllCustomerOnAEmptyDataSet_thenItShouldReturnEmptyResponse() throws Exception {
		// Given a simple GET request
		String responseBody = mockMvc.perform(get("/partner/api/v1/customer").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		List<CustomerModel> customerModel = objectMapper.readValue(responseBody,
				new TypeReference<List<CustomerModel>>() {
				});
		assertThat(customerModel).hasSize(0);
	}
		

	@Test
	@DisplayName("[Read] Testing the find customer by id flow with invalid ID")
	public void whenIQueryForcustomeresByIdWithInvalidId_thenItShouldReturnNotFoundWithError() throws Exception {
		// Given a simple GET request
		String responseBody = mockMvc
				.perform(get("/stoom/api/ecommerce/" + UUID.randomUUID().toString())
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andReturn().getResponse().getContentAsString();
		// Then it should return
		ErrorModel customerModel = objectMapper.readValue(responseBody, ErrorModel.class);
		assertThat(customerModel).extracting(ErrorModel::getMessage).isEqualTo("customer not found");
	}	
	

	@Test
	@DisplayName("[Delete] Testing the delete customer by invalid id")
	public void whenIDeletecustomerById_AndItDoesntExist_thenItShouldReturnNotFoundWithMessage() throws Exception {
		// Given a simple GET request
		String responseBody = mockMvc
				.perform(delete("/stoom/api/ecommerce/" + UUID.randomUUID().toString())
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andReturn().getResponse().getContentAsString();
		// Then it should return
		ErrorModel customerModel = objectMapper.readValue(responseBody, ErrorModel.class);
		assertThat(customerModel).extracting(ErrorModel::getMessage).isEqualTo("customer not found");
	}

	@Test
	@DisplayName("[Delete] Testing the delete customer by id")
	public void whenIDeletecustomerById_thenItShouldReturn200() throws Exception {
		Customer customer = CustomerTest.createSimpleData(repository);
		// Given a simple GET request
		String responseBody = mockMvc
				.perform(delete("/stoom/api/ecommerce/" + customer.getId()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent()).andReturn().getResponse().getContentAsString();
	}

	@Test
	@DisplayName("[Create] Testing the create customer")
	public void whenIPostAValidcustomer_thenItShouldReturn201() throws Exception {
		CustomerModel customerModel = realcustomerModel();
		mockMvc.perform(post("/partner/api/v1/customer").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(customerModel))).andExpect(status().isCreated())
				.andExpect(header().exists("Location"));
		Customer customer = repository.findAll().stream().findFirst().orElse(null);
		assertThat(customer).extracting(Customer::getStreetName).isEqualTo(customerModel.getStreetName());
	}

	@Test
	@DisplayName("[Create] Testing the create customer with mandatory field empty")
	public void whenIPostAncustomer_withMandatoryFieldEmpty_thenItShouldReturn400() throws Exception {
		CustomerModel customerModel = realcustomerModel().toBuilder().streetName(null).build();
		mockMvc.perform(post("/partner/api/v1/customer").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(customerModel))).andExpect(status().isBadRequest());
	}	

	@Test
	@DisplayName("[Update] Testing the update customer with invalid id")
	public void whenIUpdateAncustomer_withInvalidId_thenItShouldReturn404() throws Exception {
		CustomerModel customerModel = realcustomerModel();
		mockMvc.perform(put("/partner/api/v1/customer" + UUID.randomUUID().toString())
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(customerModel)))
				.andExpect(status().isNotFound());
	}

	private CustomerModel realcustomerModel() {
		return CustomerModel.builder().streetName("R. Campo Redondo").city("Campinas").country("BR")
				.neighbourhood("Jardim Maria Eugência").number("277")
				.state("SP").build();
	}
}