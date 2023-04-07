package com.bolsadeideas.springboot.backend.apirest.features.shop.product.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import com.bolsadeideas.springboot.backend.apirest.features.shop.product.dto.ProductDTO;
import com.bolsadeideas.springboot.backend.apirest.features.shop.product.model.ProductModel;


// https://www.baeldung.com/mapstruct !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

@Mapper( componentModel = "spring" )
public interface ProductModelToProductDTOMapper {
	
	ProductModelToProductDTOMapper INSTANCE = Mappers.getMapper(ProductModelToProductDTOMapper.class);
	
	ProductDTO productModelToProductDTO(ProductModel productModel);
	
	ProductModel productDTOToProductModel(ProductDTO productDTO);

}
