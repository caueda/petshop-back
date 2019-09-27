package br.com.project.petshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.project.petshop.domain.Pet;

public interface PetRepository extends JpaRepository<Pet, Long>{

}
