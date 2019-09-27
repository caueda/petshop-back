package br.com.project.petshop.api.v1.model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetDTO {
	private Long id;
	private String name;
	private String breed;
	private String specie;
	private BigDecimal weight;
	private Date birthDate;
}
