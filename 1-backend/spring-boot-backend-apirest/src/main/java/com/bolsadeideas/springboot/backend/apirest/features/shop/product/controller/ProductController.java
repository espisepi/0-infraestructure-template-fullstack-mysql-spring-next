package com.bolsadeideas.springboot.backend.apirest.features.shop.product.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bolsadeideas.springboot.backend.apirest.features.shop.product.dto.ProductDTO;
import com.bolsadeideas.springboot.backend.apirest.features.shop.product.model.ProductModel;
import com.bolsadeideas.springboot.backend.apirest.features.shop.product.service.IProductService;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Cliente;
import com.bolsadeideas.springboot.backend.apirest.models.services.IUploadFileService;
import com.bolsadeideas.springboot.backend.apirest.models.services.UsuarioService;

@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	
	private Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private IUploadFileService uploadService;
	
	
	
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
	public Page<ProductDTO> getProductsPageable(@PathVariable Integer page) {
		Integer numOfProductsToReturn = 4;
		Pageable pageable = PageRequest.of(page, numOfProductsToReturn);
		Page<ProductDTO> result = productService.findAll(pageable);
		return result;
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getProduct(@PathVariable Long id) {
		
		ProductDTO productDTO = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			productDTO = productService.findById(id);
		} catch(DataAccessException e) {
			response.put("message", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()) );
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(productDTO == null) {
			response.put("message", "El cliente ID: ".concat(id.toString()).concat(" no existe en la base de datos!"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.OK);
	}
	
	
	//@Secured("ROLE_ADMIN")
	@PostMapping("/")
	public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDTO productDTO, BindingResult bindingResult) {
		
		ProductDTO productNew = null;
		Map<String, Object> response = new HashMap<>();
		
		if(bindingResult.hasErrors()) {
			List<String> errors = bindingResult.getFieldErrors().stream().map(
					err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage()
				).collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			productNew = productService.save(productDTO);
		} catch (DataAccessException e) {
			response.put("message", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("message", "El producto se ha creado con exito! ");
		response.put("product", productNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	//@Secured("ROLE_ADMIN")
	@PutMapping("/{id}")
	public ResponseEntity<?> updateProduct(@Valid @RequestBody ProductDTO productDTO, BindingResult result, @PathVariable Long id) {

		//Cliente clienteActual = clienteService.findById(id);
		ProductDTO productDTOInDatabase = productService.findById(id);

		//Cliente clienteUpdated = null;
		ProductDTO productUpdated = null;

		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (productDTOInDatabase == null) {
			response.put("message", "Error: no se pudo editar, el producto ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			productDTOInDatabase.setDescription(productDTO.getDescription());
			productDTOInDatabase.setImages(productDTO.getImages());
			productDTOInDatabase.setName(productDTO.getName());
			productDTOInDatabase.setNumberOfStock(productDTO.getNumberOfStock());
			productDTOInDatabase.setPrice(productDTO.getPrice());
			productDTOInDatabase.setScene3D(productDTO.getScene3D());
			productDTOInDatabase.setSlug(productDTO.getSlug());

			//clienteUpdated = clienteService.save(clienteActual);
			productUpdated = productService.save(productDTOInDatabase);

		} catch (DataAccessException e) {
			response.put("message", "Error al actualizar el producto en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("message", "El producto ha sido actualizado con éxito!");
		response.put("product", productUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	//@Secured("ROLE_ADMIN")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
		    productService.delete(id);
		} catch (DataAccessException e) {
			response.put("message", "Error al eliminar el producto de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El producto se ha eliminado con éxito!");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	//@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@PostMapping("/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){
		Map<String, Object> response = new HashMap<>();
		
		//Cliente cliente = clienteService.findById(id);
		ProductDTO productDTO = productService.findById(id);
		
		if(!archivo.isEmpty()) {

			String nombreArchivo = null;
			try {
				nombreArchivo = uploadService.copy(archivo);
			} catch (IOException e) {
				response.put("message", "Error al subir el archivo del producto");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			//TODO: Todo esto de abajo y parte de arriba meterlo en productService como un metodo uploadScene3D(MultipartFile file) 
			
			//String nombreFotoAnterior = cliente.getFoto();
			String nombreFotoAnterior = productDTO.getScene3D();
			
			uploadService.delete(nombreFotoAnterior);
						
//			cliente.setFoto(nombreArchivo);
			productDTO.setScene3D(nombreArchivo);
			
//			clienteService.save(cliente);
			productService.save(productDTO);
			
			//response.put("cliente", cliente);
			response.put("product", productDTO);
			
			response.put("message", "Has subido correctamente el archivo: " + nombreArchivo);
			
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	@GetMapping("/uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){

		Resource recurso = null;
		
		try {
			recurso = uploadService.load(nombreFoto);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}
	

}
