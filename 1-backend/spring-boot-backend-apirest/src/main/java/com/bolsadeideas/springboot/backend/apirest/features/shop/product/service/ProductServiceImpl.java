package com.bolsadeideas.springboot.backend.apirest.features.shop.product.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.bolsadeideas.springboot.backend.apirest.features.shop.product.controller.ProductController;
import com.bolsadeideas.springboot.backend.apirest.features.shop.product.dto.ProductDTO;
import com.bolsadeideas.springboot.backend.apirest.features.shop.product.mapper.ProductModelToProductDTOMapper;
import com.bolsadeideas.springboot.backend.apirest.features.shop.product.model.ProductModel;
import com.bolsadeideas.springboot.backend.apirest.features.shop.product.repository.ProductRepository;
import com.bolsadeideas.springboot.backend.apirest.models.services.IUploadFileService;

@Service
public class ProductServiceImpl implements IProductService {
	
	
	private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	private ProductModelToProductDTOMapper productModelToProductDTOMapper;

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private IUploadFileService uploadService;
	
	
	
	
	/* Custom methods */

	@Override
	@Transactional(readOnly =true)
	public Page<ProductDTO> findAll(Integer page, Integer numberOfProductsToReturn) {
		Pageable pageable = PageRequest.of(page, numberOfProductsToReturn);
		Page<ProductDTO> result = this.findAll(pageable);
		return result;
	}
	
	
	@Override
	@Transactional
	public ProductDTO uploadScene3D(MultipartFile file, Long id) throws IOException {
		
		// Guardamos el archivo
		String nameFile = uploadService.copy(file);
		
		// Obtenemos el producto a modificar
		ProductDTO productDTO = this.findById(id);
		
		// Borramos el archivo anterior del producto a modificar
		String namePreviousFile = productDTO.getScene3D();	
		uploadService.delete(namePreviousFile);
		
		// Anadimos la ruta del archivo nuevo al producto a modificar
		productDTO.setScene3D(nameFile);
		this.save(productDTO);
		

		
		return productDTO;
	}
	
	@Override
	@Transactional
	public ProductDTO update(ProductDTO newProductDTO, Long id) {
		
		ProductDTO productDTOInDatabase = this.findById(id);
		ProductDTO productDTOUpdated = null;
		
		if (productDTOInDatabase == null) {
			return null;
		}
		
		productDTOInDatabase.setDescription(newProductDTO.getDescription());
		productDTOInDatabase.setImages(newProductDTO.getImages());
		productDTOInDatabase.setName(newProductDTO.getName());
		productDTOInDatabase.setNumberOfStock(newProductDTO.getNumberOfStock());
		productDTOInDatabase.setPrice(newProductDTO.getPrice());
		productDTOInDatabase.setScene3D(newProductDTO.getScene3D());
		productDTOInDatabase.setSlug(newProductDTO.getSlug());

		productDTOUpdated = this.save(productDTOInDatabase);

		return productDTOUpdated;

		
	}

	
	
	/* Classic methods */

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
		uploadService.delete(scene3D);
		
		productRepository.deleteById(id);
	}
	
	

}
