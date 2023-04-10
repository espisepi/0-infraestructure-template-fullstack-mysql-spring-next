package com.bolsadeideas.springboot.backend.apirest.features.shop.product.service;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bolsadeideas.springboot.backend.apirest.features.shop.product.dto.ProductCategoryDTO;


public interface ProductCategoryService {
	
	
	/* Custom methods */
	
	public Page<ProductCategoryDTO> findAll(Integer page, Integer numberOfProductsToReturn);
	
	public ProductCategoryDTO update(ProductCategoryDTO newProductCategoryDTO, Long id) throws DataAccessException;

	
	
	/* Classic methods  */
	
	public List<ProductCategoryDTO> findAll();
	
	public Page<ProductCategoryDTO> findAll(Pageable pageable);
	
	public ProductCategoryDTO findById(Long id);
	
	public ProductCategoryDTO save(ProductCategoryDTO productCategoryDTO);
	
	public void delete(Long id);

}
