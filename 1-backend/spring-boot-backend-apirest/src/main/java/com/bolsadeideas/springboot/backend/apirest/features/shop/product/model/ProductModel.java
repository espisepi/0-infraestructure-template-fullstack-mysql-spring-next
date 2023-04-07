package com.bolsadeideas.springboot.backend.apirest.features.shop.product.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product")
public class ProductModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// Atributos generales
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "updated_at")
	private Date updatedAt;
	
	
	// Atributos de product
	@Column
	private String slug;
	@Column
	private String name;
	@Column
	private String description;
	@Column(name = "images")
	@ElementCollection(targetClass=String.class)
	private List<String> images;
	@Column
	private String scene3D;
	@Column(name = "number_of_stock")
	private Integer numberOfStock;
	@Column
	private Double price;
	
	// Futuras implementaciones
//	private List<String> tags;
//	private TypeProduct type;
	
	@PrePersist
	public void prePersist() {
		this.createdAt = new Date();
	}
	
	@PostPersist
	public void postPersist() {
		this.updatedAt = new Date();
	}


}
