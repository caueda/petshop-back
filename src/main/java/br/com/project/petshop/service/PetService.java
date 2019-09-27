package br.com.project.petshop.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import br.com.project.petshop.domain.Pet;

public interface PetService extends Serializable {
	Pet createNewPet(Pet pet);
	Optional<Pet> findById(Long id);
	List<Pet> findAll();
}
