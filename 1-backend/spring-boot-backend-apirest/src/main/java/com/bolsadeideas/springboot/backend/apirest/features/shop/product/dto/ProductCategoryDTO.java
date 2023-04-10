package com.bolsadeideas.springboot.backend.apirest.features.shop.product.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ProductCategoryDTO {

	// Atributos generales =======================================================
	
	@JsonProperty("id")
	private Long id;
	@JsonProperty("created_at")
	private Date createdAt;
	@JsonProperty("updated_at")
	private Date updatedAt;
		
	// Atributos de productCategory ===============================================
	
	@JsonProperty("name")
	private String name;
	
	// getters y setters generados con eclise automaticamente =======================
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	
	
}
