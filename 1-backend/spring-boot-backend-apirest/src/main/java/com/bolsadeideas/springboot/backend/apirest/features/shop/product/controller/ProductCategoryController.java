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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bolsadeideas.springboot.backend.apirest.features.shop.product.dto.ProductCategoryDTO;
import com.bolsadeideas.springboot.backend.apirest.features.shop.product.service.ProductCategoryService;


@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RestController
@RequestMapping("/api/products/categories")
public class ProductCategoryController {

	private Logger logger = LoggerFactory.getLogger(ProductCategoryController.class);
	
	@Autowired
	private ProductCategoryService productCategoryService;
	
	
	// Common Methods ====================
	
	
	@GetMapping("/hello")
	public ResponseEntity<?> helloWorld() {
		ResponseEntity<String> result = new ResponseEntity<String>("Hola mundo", HttpStatus.OK);
		return result;
	}
	
	
	@GetMapping("/")
	public List<ProductCategoryDTO> getProductsCategories() {
		return productCategoryService.findAll();
	}
	
	@GetMapping("/page/{page}")
	public Page<ProductCategoryDTO> getProductsPageableCategories(@PathVariable Integer page) {
		Integer numberOfObjectsToReturn = 4;
		return productCategoryService.findAll(page, numberOfObjectsToReturn);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getProductCategory(@PathVariable Long id) {
		
		ProductCategoryDTO productCategoryDTO = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			productCategoryDTO = productCategoryService.findById(id);
		} catch(DataAccessException e) {
			response.put("message", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()) );
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(productCategoryDTO == null) {
			response.put("message", "El cliente ID: ".concat(id.toString()).concat(" no existe en la base de datos!"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<ProductCategoryDTO>(productCategoryDTO, HttpStatus.OK);
	}
	
	
	//@Secured("ROLE_ADMIN")
	@PostMapping("/")
	public ResponseEntity<?> createProductCategory(@Valid @RequestBody ProductCategoryDTO productCategoryDTO, BindingResult bindingResult) {
		
		ProductCategoryDTO productCategoryNew = null;
		Map<String, Object> response = new HashMap<>();
		
		// Validacion de errores del formulario
		if(bindingResult.hasErrors()) {
			List<String> errors = bindingResult.getFieldErrors().stream().map(
					err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage()
				).collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		// Guardar el nuevo producto Category creado
		try {
			productCategoryNew = productCategoryService.save(productCategoryDTO);
		} catch (DataAccessException e) {
			response.put("message", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("message", "El producto se ha creado con exito! ");
		response.put("productCategory", productCategoryNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	//@Secured("ROLE_ADMIN")
	@PutMapping("/{id}")
	public ResponseEntity<?> updateProductCategory(@Valid @RequestBody ProductCategoryDTO productCategoryDTO, BindingResult result, @PathVariable Long id) {

		ProductCategoryDTO productCategoryUpdated = null;

		Map<String, Object> response = new HashMap<>();
		
		// Validacion de errores del formulario
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		// Actualizar el producto
		try {
			productCategoryUpdated = productCategoryService.update(productCategoryDTO, id);
		} catch (DataAccessException e) {
			response.put("message", "Error al actualizar la categoria del producto en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (productCategoryUpdated == null) {
			response.put("message", "Error: no se pudo editar, la categoria del producto ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}


		response.put("message", "La categoria del producto ha sido actualizado con éxito!");
		response.put("productCategory", productCategoryUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	//@Secured("ROLE_ADMIN")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
		    productCategoryService.delete(id);
		} catch (DataAccessException e) {
			response.put("message", "Error al eliminar la categoria del producto de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La categoriadel producto se ha eliminado con éxito!");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	

	
}
