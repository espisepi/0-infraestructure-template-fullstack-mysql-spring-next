package com.bolsadeideas.springboot.backend.apirest.features.shop.product.repository;

import org.springframework.stereotype.Repository;

import com.bolsadeideas.springboot.backend.apirest.features.shop.product.model.ProductCategoryModel;
import com.bolsadeideas.springboot.backend.apirest.repositories.GenericRepository;

@Repository
public interface ProductCategoryRepository extends GenericRepository<ProductCategoryModel> {

}
