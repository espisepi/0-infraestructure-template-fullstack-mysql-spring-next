package com.bolsadeideas.springboot.backend.apirest.features.shop.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bolsadeideas.springboot.backend.apirest.features.shop.product.model.ProductModel;

@Repository
public interface IProductRepository extends JpaRepository<ProductModel, Long>{

}
