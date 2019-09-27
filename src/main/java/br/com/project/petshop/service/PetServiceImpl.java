package br.com.project.petshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.project.petshop.domain.Pet;
import br.com.project.petshop.repository.PetRepository;

@Service
public class PetServiceImpl implements PetService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PetRepository petRepository;
	
	public PetServiceImpl(PetRepository petRepository) {
		super();
		this.petRepository = petRepository;
	}

	@Override
	public Pet createNewPet(Pet pet) {
		return petRepository.save(pet);
	}

	@Override
	public Optional<Pet> findById(Long id) {
		Optional<Pet> optional = petRepository.findById(id);
		return optional;
	}

	@Override
	public List<Pet> findAll() {
		return petRepository.findAll();
	}

}
