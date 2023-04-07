package com.bolsadeideas.springboot.backend.apirest.features.shop.product.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {

	// Atributos generales
	@JsonProperty("id")
	private Long id;
	@JsonProperty("created_at")
	private Date createdAt;
	@JsonProperty("updated_at")
	private Date updatedAt;
	
	
	// Atributos de product
	@NotNull
	@JsonProperty("slug")
	private String slug;
	@NotNull
	@JsonProperty("name")
	private String name;
	@NotNull
	@JsonProperty("description")
	private String description;
	@JsonProperty("images")
	private List<String> images;
	@JsonProperty("scene3D")
	private String scene3D;
	@NotNull
	@JsonProperty("number_of_stock")
	private Integer numberOfStock;
	@NotNull
	@JsonProperty("price")
	private Double price;
	

}
