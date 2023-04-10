package com.bolsadeideas.springboot.backend.apirest.features.shop.product.mapper;


import com.bolsadeideas.springboot.backend.apirest.features.shop.product.dto.ProductCategoryDTO;
import com.bolsadeideas.springboot.backend.apirest.features.shop.product.dto.ProductDTO;
import com.bolsadeideas.springboot.backend.apirest.features.shop.product.model.ProductCategoryModel;
import com.bolsadeideas.springboot.backend.apirest.features.shop.product.model.ProductModel;



public interface ProductModelToProductDTOMapper {

	ProductDTO productModelToProductDTO(ProductModel productModel);

	ProductModel productDTOToProductModel(ProductDTO productDTO);
	
	ProductCategoryDTO map(ProductCategoryModel value);
	
	ProductCategoryModel map(ProductCategoryDTO value);

}
