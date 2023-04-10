package com.bolsadeideas.springboot.backend.apirest.features.shop.product.mapper;

import org.springframework.stereotype.Component;

import com.bolsadeideas.springboot.backend.apirest.features.shop.product.dto.ProductCategoryDTO;
import com.bolsadeideas.springboot.backend.apirest.features.shop.product.model.ProductCategoryModel;

@Component
public class ProductCategoryModelToProductCategoryDTOMapperImpl implements ProductCategoryModelToProductCategoryDTOMapper {

    @Override
    public ProductCategoryDTO productCategoryModelToProductCategoryDTO(ProductCategoryModel productCategoryModel) {
        if ( productCategoryModel == null ) {
            return null;
        }

        ProductCategoryDTO productCategoryDTO = new ProductCategoryDTO();

        productCategoryDTO.setId( productCategoryModel.getId() );
        productCategoryDTO.setName( productCategoryModel.getName() );
        productCategoryDTO.setCreatedAt( productCategoryModel.getCreatedAt() );
        productCategoryDTO.setUpdatedAt( productCategoryModel.getUpdatedAt() );

        return productCategoryDTO;
    }

    @Override
    public ProductCategoryModel productCategoryDTOToProductCategoryModel(ProductCategoryDTO productCategoryDTO) {
        if ( productCategoryDTO == null ) {
            return null;
        }

        ProductCategoryModel productCategoryModel = new ProductCategoryModel();

        productCategoryModel.setId( productCategoryDTO.getId() );
        productCategoryModel.setName( productCategoryDTO.getName() );
        productCategoryModel.setCreatedAt( productCategoryDTO.getCreatedAt() );
        productCategoryModel.setUpdatedAt( productCategoryDTO.getUpdatedAt() );

        return productCategoryModel;
    }
}
