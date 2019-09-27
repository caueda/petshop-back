package br.com.project.petshop.api.v1.mapper;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.junit.Assert.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;

import br.com.project.petshop.api.v1.model.PetDTO;
import br.com.project.petshop.domain.Pet;

public class PetMapperTest {
	
	PetMapper petMapper = PetMapper.INSTANCE;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testPetToPetDTO() throws ParseException {
		Pet pet = new Pet();
		pet.setId(1L);
		pet.setName("Bono");
		pet.setBreed("Pug");
		pet.setSpecie("Dog");
		pet.setBirthDate(sdf.parse("01/01/2015"));
		
		PetDTO petDTO = petMapper.petToPetDTO(pet);

		assertThat(pet.getId(), equalTo(petDTO.getId()));
		assertThat(pet.getName(), equalToIgnoringCase(petDTO.getName()));
		assertThat(pet.getBreed(), equalToIgnoringCase(petDTO.getBreed()));
		assertThat(pet.getSpecie(), equalToIgnoringCase(petDTO.getSpecie()));
		assertThat(pet.getWeight(), equalTo(petDTO.getWeight()));
		assertThat(pet.getBirthDate(), equalTo(petDTO.getBirthDate()));
	}

	@Test
	public void testPetDTOToPet() throws ParseException {
		PetDTO petDTO = new PetDTO();
		petDTO.setId(1L);
		petDTO.setName("Bono");
		petDTO.setBreed("Pug");
		petDTO.setSpecie("Dog");
		petDTO.setBirthDate(sdf.parse("01/01/2015"));
		
		Pet pet= petMapper.petDTOToPet(petDTO);

		assertThat(pet.getId(), equalTo(petDTO.getId()));
		assertThat(pet.getName(), equalToIgnoringCase(petDTO.getName()));
		assertThat(pet.getBreed(), equalToIgnoringCase(petDTO.getBreed()));
		assertThat(pet.getSpecie(), equalToIgnoringCase(petDTO.getSpecie()));
		assertThat(pet.getWeight(), equalTo(petDTO.getWeight()));
		assertThat(pet.getBirthDate(), equalTo(petDTO.getBirthDate()));
	}

}
