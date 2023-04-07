package com.bolsadeideas.springboot.backend.apirest.features.shop.product.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bolsadeideas.springboot.backend.apirest.features.shop.product.dto.ProductDTO;
import com.bolsadeideas.springboot.backend.apirest.features.shop.product.model.ProductModel;
import com.bolsadeideas.springboot.backend.apirest.features.shop.product.service.IProductService;
import com.bolsadeideas.springboot.backend.apirest.models.services.UsuarioService;

@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	
	private Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	
	@Autowired
	private IProductService productService;
	
	
	
	@GetMapping("/hello")
	public ResponseEntity<?> helloWorld() {
		ResponseEntity<String> result = new ResponseEntity<String>("Hola mundo", HttpStatus.OK);
		return result;
	}
	
	
	@GetMapping("/")
	public List<ProductDTO> getProducts() {
		return productService.findAll();
	}
	
	@GetMapping("/page/{page}")
	public Page<ProductModel> getProductsPageable(@PathVariable Integer page) {
		Integer numOfProductsToReturn = 4;
		Pageable pageable = PageRequest.of(page, numOfProductsToReturn);
		Page<ProductModel> result = productService.findAll(pageable);
		return result;
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getProduct(@PathVariable Long id) {
		
		ProductModel productModel = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			productModel = productService.findById(id);
		} catch(DataAccessException e) {
			response.put("message", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()) );
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(productModel == null) {
			response.put("message", "El cliente ID: ".concat(id.toString()).concat(" no existe en la base de datos!"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<ProductModel>(productModel, HttpStatus.OK);
	}
	
	
	//@Secured("ROLE_ADMIN")
	@PostMapping("/")
	public ResponseEntity<?> createProduct(@Valid @RequestBody ProductModel productModel, BindingResult bindingResult) {
		
		ProductModel productNew = null;
		Map<String, Object> response = new HashMap<>();
		
		if(bindingResult.hasErrors()) {
			List<String> errors = bindingResult.getFieldErrors().stream().map(
					err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage()
				).collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			productNew = productService.save(productModel);
		} catch (DataAccessException e) {
			response.put("message", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("message", "El producto se ha creado con exito! ");
		response.put("product", productNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	
	

}
