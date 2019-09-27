package br.com.project.petshop.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.project.petshop.api.exceptions.CustomizedResponseEntityExceptionHandler;
import br.com.project.petshop.api.v1.mapper.PetMapper;
import br.com.project.petshop.domain.Pet;
import br.com.project.petshop.service.PetService;

public class PetControllerTest {
	@Mock
	PetService petService;
	
	@Mock
	PetMapper petMapper;
	
	@InjectMocks
	PetController petController;
	
	MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(petController)
			.setControllerAdvice(new CustomizedResponseEntityExceptionHandler())
			.build();
	}

	@Test
	public void testCreateNewPet() throws Exception {
		Pet pet = new Pet();
		pet.setId(10L);
		pet.setName("Bono");
		pet.setBreed("Pug");
		pet.setBirthDate(new Date());
		pet.setSpecie("Dog");
		pet.setWeight(new BigDecimal(10));
		
		Mockito.when(petService.createNewPet(Mockito.any(Pet.class))).thenReturn(
				pet
				);
		
		mockMvc.perform(post("/api/v1/pet")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(pet)))
		.andExpect(status().isCreated())
		.andExpect(header().string("location", containsString("/api/v1/pet/10")));
	}

	@Test
	public void testGetPetById() throws Exception {
		Pet pet = new Pet();
		pet.setId(10L);
		pet.setName("Bono");
		pet.setBreed("Pug");
		pet.setBirthDate(new Date());
		pet.setSpecie("Dog");
		pet.setWeight(new BigDecimal(10));
		
		Mockito.when(petService.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(pet));
		
		mockMvc.perform(get("/api/v1/pet/10")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", equalTo("Bono")));
	}

}
