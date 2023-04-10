package com.bolsadeideas.springboot.backend.apirest.features.shop.product.service;

import java.io.IOException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.bolsadeideas.springboot.backend.apirest.features.shop.product.dto.ProductDTO;


public interface ProductService {
	
	/* Custom methods */
	
	public Page<ProductDTO> findAll(Integer page, Integer numberOfProductsToReturn);
	
	public ProductDTO update(ProductDTO newProductDTO, Long id) throws DataAccessException;
	
	public ProductDTO uploadScene3D(MultipartFile file, Long id) throws IOException ;
	
	
	/* Classic methods  */
	
	public List<ProductDTO> findAll();
	
	public Page<ProductDTO> findAll(Pageable pageable);
	
	public ProductDTO findById(Long id);
	
	public ProductDTO save(ProductDTO productDTO);
	
	public void delete(Long id);
}
