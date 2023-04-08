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
import com.bolsadeideas.springboot.backend.apirest.models.services.IUploadFileService;

@Service
public class ProductServiceImpl implements IProductService {
	
	
	private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	private ProductModelToProductDTOMapper productModelToProductDTOMapper;

	@Autowired
	private IProductRepository productRepository;
	
	@Autowired
	private IUploadFileService uploadService;
	
	
	

	@Override
	@Transactional(readOnly =true)
	public List<ProductDTO> findAll() {
		List<ProductModel> productModels = productRepository.findAll();
		List<ProductDTO> result = productModels.stream().map(productModelToProductDTOMapper::productModelToProductDTO).collect(Collectors.toList());
		return result;
	}
	
	@Override
	@Transactional(readOnly =true)
	public Page<ProductDTO> findAll(Pageable pageable) {
		Page<ProductModel> productModels= productRepository.findAll(pageable);
		// The pageable abstraction has the map method:
		Page<ProductDTO> result = productModels.map(productModelToProductDTOMapper::productModelToProductDTO);
		return result;
	}

	@Override
	@Transactional(readOnly =true)
	public ProductDTO findById(Long id) {
		ProductModel productModel = productRepository.findById(id).orElse(null);
		ProductDTO result = productModelToProductDTOMapper.productModelToProductDTO(productModel);
		return result;
	}

	@Override
	@Transactional
	public ProductDTO save(ProductDTO productDTO) {
		ProductModel productModel = productModelToProductDTOMapper.productDTOToProductModel(productDTO);
		ProductModel productModelSaved = productRepository.save(productModel);
		ProductDTO result = productModelToProductDTOMapper.productModelToProductDTO(productModelSaved);
		return result;
	}

	@Override
	@Transactional
	public void delete(Long id) {
		ProductDTO productDTO = this.findById(id);
		String scene3D = productDTO.getScene3D();
		
		//TODO: Elimininar todas las fotos cuando se tenga ese atributo
		//List<String> images = productDTO.getImages();
		// images.stream().forEach(image -> uploadService.eliminar(image)));
		uploadService.eliminar(scene3D);
		
		productRepository.deleteById(id);
	}
	
	

}
