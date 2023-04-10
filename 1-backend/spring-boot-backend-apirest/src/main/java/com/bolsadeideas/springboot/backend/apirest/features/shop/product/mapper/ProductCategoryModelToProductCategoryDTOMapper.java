package com.bolsadeideas.springboot.backend.apirest.features.shop.product.mapper;


import com.bolsadeideas.springboot.backend.apirest.features.shop.product.dto.ProductCategoryDTO;
import com.bolsadeideas.springboot.backend.apirest.features.shop.product.model.ProductCategoryModel;


public interface ProductCategoryModelToProductCategoryDTOMapper {
	
	ProductCategoryDTO productCategoryModelToProductCategoryDTO(ProductCategoryModel productCategoryModel);

	ProductCategoryModel productCategoryDTOToProductCategoryModel(ProductCategoryDTO productCategoryDTO);

}
