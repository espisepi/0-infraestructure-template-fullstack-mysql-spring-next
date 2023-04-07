package com.bolsadeideas.springboot.backend.apirest.features.shop.product.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.backend.apirest.features.shop.product.controller.ProductController;
import com.bolsadeideas.springboot.backend.apirest.features.shop.product.dto.ProductDTO;
import com.bolsadeideas.springboot.backend.apirest.features.shop.product.mapper.ProductModelToProductDTOMapper;
import com.bolsadeideas.springboot.backend.apirest.features.shop.product.model.ProductModel;
import com.bolsadeideas.springboot.backend.apirest.features.shop.product.repository.IProductRepository;

@Service
public class ProductServiceImpl implements IProductService {
	
	private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	private ProductModelToProductDTOMapper productModelToProductDTOMapper = ProductModelToProductDTOMapper.INSTANCE;

	
	@Autowired
	private IProductRepository productRepository;
	
	
	

	@Override
	@Transactional(readOnly =true)
	public List<ProductDTO> findAll() {
		List<ProductModel> productModels = productRepository.findAll();
		List<ProductDTO> result = productModels.stream().map(productModelToProductDTOMapper::productModelToProductDTO).collect(Collectors.toList());
		return result;
	}
	
	//TODO: MODIFICAR EL PRODUCTDTO EN LOS DEMAS (CAMBIARLO TAMBIEN EN INTERFACE)

	@Override
	@Transactional(readOnly =true)
	public Page<ProductModel> findAll(Pageable pageable) {
		Page<ProductModel> result = productRepository.findAll(pageable);
		return result;
	}

	@Override
	@Transactional(readOnly =true)
	public ProductModel findById(Long id) {
		ProductModel result = productRepository.findById(id).orElse(null);
		return result;
	}

	@Override
	@Transactional
	public ProductModel save(ProductModel product) {
		ProductModel result = productRepository.save(product);
		return result;
	}

	@Override
	@Transactional
	public void delete(Long id) {
		productRepository.deleteById(id);
	}
	
	

}
