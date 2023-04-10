package com.bolsadeideas.springboot.backend.apirest.features.shop.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bolsadeideas.springboot.backend.apirest.features.shop.product.model.ProductModel;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long>{

}
