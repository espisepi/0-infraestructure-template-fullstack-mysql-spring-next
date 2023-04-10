package com.bolsadeideas.springboot.backend.apirest.features.shop.product.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ProductDTO {

	// Atributos generales =========================================================
	
	@JsonProperty("id")
	private Long id;
	@JsonProperty("created_at")
	private Date createdAt;
	@JsonProperty("updated_at")
	private Date updatedAt;
	
	
	// Atributos de product =========================================================
	
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
	
	@JsonProperty("categories")
	private List<ProductCategoryDTO> categories;

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

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public String getScene3D() {
		return scene3D;
	}

	public void setScene3D(String scene3d) {
		scene3D = scene3d;
	}

	public Integer getNumberOfStock() {
		return numberOfStock;
	}

	public void setNumberOfStock(Integer numberOfStock) {
		this.numberOfStock = numberOfStock;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public List<ProductCategoryDTO> getCategories() {
		return categories;
	}

	public void setCategories(List<ProductCategoryDTO> categories) {
		this.categories = categories;
	}
	
	
	

}
