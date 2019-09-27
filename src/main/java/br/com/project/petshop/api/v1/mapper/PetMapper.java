package br.com.project.petshop.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.project.petshop.api.v1.model.PetDTO;
import br.com.project.petshop.domain.Pet;

@Mapper
public interface PetMapper {
	PetMapper INSTANCE = Mappers.getMapper(PetMapper.class);
	PetDTO petToPetDTO(Pet pet);
	Pet petDTOToPet(PetDTO pet);
}
