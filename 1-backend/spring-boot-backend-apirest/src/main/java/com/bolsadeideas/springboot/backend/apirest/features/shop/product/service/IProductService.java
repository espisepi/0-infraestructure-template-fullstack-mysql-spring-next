package com.bolsadeideas.springboot.backend.apirest.features.shop.product.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bolsadeideas.springboot.backend.apirest.features.shop.product.dto.ProductDTO;
import com.bolsadeideas.springboot.backend.apirest.features.shop.product.model.ProductModel;


public interface IProductService {
	
	/* Custom methods */
	
	public Page<ProductDTO> findAll(Integer page, Integer numberOfProductsToReturn);
	
	
	
	/* Classic methods  */
	
	public List<ProductDTO> findAll();
	
	public Page<ProductDTO> findAll(Pageable pageable);
	
	public ProductDTO findById(Long id);
	
	public ProductDTO save(ProductDTO productDTO);
	
	public void delete(Long id);
}
