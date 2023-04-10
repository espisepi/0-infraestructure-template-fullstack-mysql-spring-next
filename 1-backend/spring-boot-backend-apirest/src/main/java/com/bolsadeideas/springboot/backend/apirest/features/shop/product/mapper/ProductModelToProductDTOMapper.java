package com.bolsadeideas.springboot.backend.apirest.features.shop.product.mapper;


import com.bolsadeideas.springboot.backend.apirest.features.shop.product.dto.ProductCategoryDTO;
import com.bolsadeideas.springboot.backend.apirest.features.shop.product.dto.ProductDTO;
import com.bolsadeideas.springboot.backend.apirest.features.shop.product.model.ProductCategoryModel;
import com.bolsadeideas.springboot.backend.apirest.features.shop.product.model.ProductModel;



public interface ProductModelToProductDTOMapper {
	
	// Metodos de este convertidor aka mapper ================

	ProductDTO productModelToProductDTO(ProductModel productModel);

	ProductModel productDTOToProductModel(ProductDTO productDTO);
	
	// Metodos del convertidor aka mapper ProductCategoryModelToProductCategoryDTOMapper (el cual utilizamos en la clase implementacion de esta interfaz) ========
	
	ProductCategoryDTO productCategoryModelToProductCategoryDTO(ProductCategoryModel value);
	
	ProductCategoryModel productCategoryDTOToProductCategoryModel(ProductCategoryDTO value);

}
