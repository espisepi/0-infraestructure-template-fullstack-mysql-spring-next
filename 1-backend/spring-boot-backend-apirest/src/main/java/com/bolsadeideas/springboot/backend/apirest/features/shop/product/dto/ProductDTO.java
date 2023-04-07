package com.bolsadeideas.springboot.backend.apirest.features.shop.product.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ProductDTO {

	// Atributos generales
	private Long id;
	private Date createdAt;
	private Date updatedAt;
	
	
	// Atributos de product
	private String slug;
	private String name;
	private String description;
	private List<String> images;
	private String scene3D;
	private Integer numberOfStock;
	private Double price;
	
	
	public ProductDTO() {
	}
	
	
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
	
	

}
