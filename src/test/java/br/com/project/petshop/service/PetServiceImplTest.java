package br.com.project.petshop.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.project.petshop.domain.Pet;
import br.com.project.petshop.repository.PetRepository;

public class PetServiceImplTest {
	@Mock
	PetRepository petRepository;
	
	PetService petService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		petService = new PetServiceImpl(petRepository);
	}

	@Test
	public void testCreateNewPet() {
		Pet petToBeSaved = new Pet();
		petToBeSaved.setId(1L);
		petToBeSaved.setName("Bono");
		petToBeSaved.setBreed("Pug");
		petToBeSaved.setSpecie("Dog");
		
		Mockito.when(petRepository.save(Mockito.any(Pet.class))).thenReturn(petToBeSaved);
		
		Pet savedPet = petService.createNewPet(petToBeSaved);
		
		Mockito.verify(petRepository).save(Mockito.any(Pet.class));
		assertThat(savedPet, equalTo(petToBeSaved));
	}

	@Test
	public void testFindById() {
		Pet pet = new Pet();
		pet.setId(1L);
		pet.setName("Bono");
		pet.setBreed("Pug");
		pet.setSpecie("Dog");
		
		Mockito.when(petRepository.findById(Mockito.anyLong())).thenReturn(
				Optional.ofNullable(pet)
		);
		
		Pet petFound = petService.findById(1L).get();
		assertThat(petFound, equalTo(pet));
		Mockito.verify(petRepository).findById(Mockito.anyLong());
	}

	@Test
	public void testFindAll() {
		Pet pet1 = new Pet();
		Pet pet2 = new Pet();
		List<Pet> expectedtList = Stream.of(pet1, pet2).collect(Collectors.toList());
		Mockito.when(petRepository.findAll()).thenReturn(expectedtList);
		
		List<Pet> resultList = petService.findAll();
		
		assertThat(resultList, hasSize(2));
	}

}
