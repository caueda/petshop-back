package br.com.project.petshop.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.project.petshop.api.exceptions.ResourceNotFoundException;
import br.com.project.petshop.api.v1.mapper.PetMapper;
import br.com.project.petshop.api.v1.model.PetDTO;
import br.com.project.petshop.domain.Pet;
import br.com.project.petshop.service.PetService;

@RestController
@RequestMapping("/api")
public class PetController {
	
	private PetService petService;
	private PetMapper petMapper;
	
	public PetController(PetService petService, PetMapper petMapper) {
		super();
		this.petService = petService;
		this.petMapper = petMapper;
	}
	
	@PostMapping("/v1/pet")
	public ResponseEntity<Object> createNewPet(@RequestBody Pet pet){
		Pet savedPet = petService.createNewPet(pet);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedPet.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/v1/pet/{id}")
	public PetDTO getPetById(@PathVariable Long id) {
		return petService.findById(id)
                .map(petMapper::petToPetDTO)
                .map(petDTO -> {
                    return petDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);		
	}
}
