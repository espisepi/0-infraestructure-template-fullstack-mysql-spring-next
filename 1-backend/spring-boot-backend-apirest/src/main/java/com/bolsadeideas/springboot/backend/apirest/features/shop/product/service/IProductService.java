package com.bolsadeideas.springboot.backend.apirest.features.shop.product.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bolsadeideas.springboot.backend.apirest.features.shop.product.dto.ProductDTO;
import com.bolsadeideas.springboot.backend.apirest.features.shop.product.model.ProductModel;


public interface IProductService {
	
	public List<ProductDTO> findAll();
	
	public Page<ProductModel> findAll(Pageable pageable);
	
	public ProductModel findById(Long id);
	
	public ProductModel save(ProductModel product);
	
	public void delete(Long id);
}
