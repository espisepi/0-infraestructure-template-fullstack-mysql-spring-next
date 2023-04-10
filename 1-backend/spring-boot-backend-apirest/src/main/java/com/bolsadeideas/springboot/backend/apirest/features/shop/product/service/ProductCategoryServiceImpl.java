package com.bolsadeideas.springboot.backend.apirest.features.shop.product.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.backend.apirest.features.shop.product.dto.ProductCategoryDTO;
import com.bolsadeideas.springboot.backend.apirest.features.shop.product.mapper.ProductCategoryModelToProductCategoryDTOMapper;
import com.bolsadeideas.springboot.backend.apirest.features.shop.product.model.ProductCategoryModel;
import com.bolsadeideas.springboot.backend.apirest.features.shop.product.repository.ProductCategoryRepository;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
	
	private Logger logger = LoggerFactory.getLogger(ProductCategoryServiceImpl.class);
	
	@Autowired
	private ProductCategoryRepository productCategoryRepository;
	
	@Autowired
	private ProductCategoryModelToProductCategoryDTOMapper productCategoryModelToProductCategoryDTOMapper;

	
	
	
	/* Custom methods */
	
	@Override
	@Transactional(readOnly =true)
	public Page<ProductCategoryDTO> findAll(Integer page, Integer numberOfProductsToReturn) {
		Pageable pageable = PageRequest.of(page, numberOfProductsToReturn);
		Page<ProductCategoryDTO> result = this.findAll(pageable);
		return result;
	}

	@Override
	@Transactional
	public ProductCategoryDTO update(ProductCategoryDTO newProductCategoryDTO, Long id) throws DataAccessException {

		ProductCategoryDTO productCategoryDTOInDatabase = this.findById(id);
		ProductCategoryDTO productCategoryDTOUpdated = null;
		
		if (productCategoryDTOInDatabase == null) {
			return null;
		}
		
		// Actualizamos los valores ======================================
		productCategoryDTOInDatabase.setName(newProductCategoryDTO.getName());
		productCategoryDTOInDatabase.setUpdatedAt(new Date());


		productCategoryDTOUpdated = this.save(productCategoryDTOInDatabase);

		return productCategoryDTOUpdated;
	}
	
	
	
	
	/* Classic methods */

	@Override
	@Transactional(readOnly =true)
	public List<ProductCategoryDTO> findAll() {
		// Llamamos al repositorio
		List<ProductCategoryModel> productModels = productCategoryRepository.findAll();
		// Mapeamos los datos devueltos por el repositorio
		List<ProductCategoryDTO> result = productModels.stream().map(productCategoryModelToProductCategoryDTOMapper::productCategoryModelToProductCategoryDTO).collect(Collectors.toList());
		return result;
	}

	@Override
	@Transactional(readOnly =true)
	public Page<ProductCategoryDTO> findAll(Pageable pageable) {
		// Llamamos al repositorio
		Page<ProductCategoryModel> productCategoryModels= productCategoryRepository.findAll(pageable);
		// Mapeamos los datos devueltos por el repositorio
		// The pageable abstraction has the map method:
		Page<ProductCategoryDTO> result = productCategoryModels.map(productCategoryModelToProductCategoryDTOMapper::productCategoryModelToProductCategoryDTO);
		return result;
	}

	@Override
	@Transactional(readOnly =true)
	public ProductCategoryDTO findById(Long id) {
		ProductCategoryModel productCategoryModel = productCategoryRepository.findById(id).orElse(null);
		ProductCategoryDTO result = productCategoryModelToProductCategoryDTOMapper.productCategoryModelToProductCategoryDTO(productCategoryModel);
		return result;
	}

	@Override
	@Transactional
	public ProductCategoryDTO save(ProductCategoryDTO productCategoryDTO) {
		ProductCategoryModel productCategoryModel = productCategoryModelToProductCategoryDTOMapper.productCategoryDTOToProductCategoryModel(productCategoryDTO);
		ProductCategoryModel productCategoryModelSaved = productCategoryRepository.save(productCategoryModel);
		ProductCategoryDTO result = productCategoryModelToProductCategoryDTOMapper.productCategoryModelToProductCategoryDTO(productCategoryModelSaved);
		return result;
	}

	@Override
	@Transactional
	public void delete(Long id) {
		ProductCategoryDTO productCategoryDTO = this.findById(id);
		productCategoryRepository.deleteById(id);
	}
	
}
