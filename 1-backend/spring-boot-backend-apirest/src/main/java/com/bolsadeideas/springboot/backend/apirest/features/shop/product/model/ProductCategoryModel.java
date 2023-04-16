package com.bolsadeideas.springboot.backend.apirest.features.shop.product.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.bolsadeideas.springboot.backend.apirest.models.entity.BaseEntity;


@Entity
@Table(name="product_category")
public class ProductCategoryModel extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	
	@Column(unique=true, length=100)
	private String name;
	
	
	public ProductCategoryModel() {
		super();
	}


	// getters y setters generados con eclipse automaticamente ===========================

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
