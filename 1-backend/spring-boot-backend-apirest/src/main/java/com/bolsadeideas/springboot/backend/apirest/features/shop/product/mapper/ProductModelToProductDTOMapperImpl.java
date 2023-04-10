package com.bolsadeideas.springboot.backend.apirest.features.shop.product.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bolsadeideas.springboot.backend.apirest.features.shop.product.dto.ProductCategoryDTO;
import com.bolsadeideas.springboot.backend.apirest.features.shop.product.dto.ProductDTO;
import com.bolsadeideas.springboot.backend.apirest.features.shop.product.model.ProductCategoryModel;
import com.bolsadeideas.springboot.backend.apirest.features.shop.product.model.ProductModel;

@Component
public class ProductModelToProductDTOMapperImpl implements ProductModelToProductDTOMapper {
	
	@Autowired
	private ProductCategoryModelToProductCategoryDTOMapper productCategoryModelToProductCategoryDTOMapper;
	

    @Override
    public ProductDTO productModelToProductDTO(ProductModel productModel) {
        if ( productModel == null ) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();

        productDTO.setId( productModel.getId() );
        productDTO.setName( productModel.getName() );
        productDTO.setCategories( productCategoryModelListToProductCategoryDTOList( productModel.getCategories() ) );
        productDTO.setCreatedAt( productModel.getCreatedAt() );
        productDTO.setDescription( productModel.getDescription() );
        List<String> list1 = productModel.getImages();
        if ( list1 != null ) {
            productDTO.setImages( new ArrayList<String>( list1 ) );
        }
        productDTO.setNumberOfStock( productModel.getNumberOfStock() );
        productDTO.setPrice( productModel.getPrice() );
        productDTO.setScene3D( productModel.getScene3D() );
        productDTO.setSlug( productModel.getSlug() );
        productDTO.setUpdatedAt( productModel.getUpdatedAt() );

        return productDTO;
    }

    @Override
    public ProductModel productDTOToProductModel(ProductDTO productDTO) {
        if ( productDTO == null ) {
            return null;
        }

        ProductModel productModel = new ProductModel();

        productModel.setId( productDTO.getId() );
        productModel.setName( productDTO.getName() );
        productModel.setCategories( productCategoryDTOListToProductCategoryModelList( productDTO.getCategories() ) );
        productModel.setCreatedAt( productDTO.getCreatedAt() );
        productModel.setDescription( productDTO.getDescription() );
        List<String> list1 = productDTO.getImages();
        if ( list1 != null ) {
            productModel.setImages( new ArrayList<String>( list1 ) );
        }
        productModel.setNumberOfStock( productDTO.getNumberOfStock() );
        productModel.setPrice( productDTO.getPrice() );
        productModel.setScene3D( productDTO.getScene3D() );
        productModel.setSlug( productDTO.getSlug() );
        productModel.setUpdatedAt( productDTO.getUpdatedAt() );

        return productModel;
    }

    protected List<ProductCategoryDTO> productCategoryModelListToProductCategoryDTOList(List<ProductCategoryModel> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductCategoryDTO> list1 = new ArrayList<ProductCategoryDTO>( list.size() );
        for ( ProductCategoryModel productCategoryModel : list ) {
            list1.add( productCategoryModelToProductCategoryDTO( productCategoryModel ) );
        }

        return list1;
    }

    protected List<ProductCategoryModel> productCategoryDTOListToProductCategoryModelList(List<ProductCategoryDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductCategoryModel> list1 = new ArrayList<ProductCategoryModel>( list.size() );
        for ( ProductCategoryDTO productCategoryDTO : list ) {
            list1.add( productCategoryDTOToProductCategoryModel( productCategoryDTO ) );
        }

        return list1;
    }
    
    @Override
    public ProductCategoryDTO productCategoryModelToProductCategoryDTO(ProductCategoryModel value) {
        if ( value == null ) {
            return null;
        }

        return productCategoryModelToProductCategoryDTOMapper.productCategoryModelToProductCategoryDTO(value);
    }
    

    @Override
    public ProductCategoryModel productCategoryDTOToProductCategoryModel(ProductCategoryDTO value) {
        if ( value == null ) {
            return null;
        }

    	return productCategoryModelToProductCategoryDTOMapper.productCategoryDTOToProductCategoryModel(value);
    }
}

