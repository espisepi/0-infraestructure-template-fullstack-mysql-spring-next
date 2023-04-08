package com.bolsadeideas.springboot.backend.apirest.models.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileServiceImpl implements IUploadFileService{
	
	private final Logger log = LoggerFactory.getLogger(UploadFileServiceImpl.class);
	
	private final static String UPLOAD_DIRECTORY = "uploads";

	@Override
	public Resource load(String nameFile) throws MalformedURLException {
		
		Path pathFile = getPath(nameFile);
		log.info(pathFile.toString());
		
		Resource resource = new UrlResource(pathFile.toUri());
		
		if(!resource.exists() && !resource.isReadable()) {
			pathFile = Paths.get("src/main/resources/static/images").resolve("no-usuario.png").toAbsolutePath();
			
			resource = new UrlResource(pathFile.toUri());
			
			log.error("Error no se pudo cargar el archivo: " + nameFile);
			
		}
		return resource;
	}

	@Override
	public String copy(MultipartFile file) throws IOException {
		
		String nameFile = UUID.randomUUID().toString() + "_nameOriginalFileIs_" +  file.getOriginalFilename().replace(" ", "");
		
		Path pathFile = getPath(nameFile);
		log.info(pathFile.toString());
		
		Files.copy(file.getInputStream(), pathFile);
		
		return nameFile;
	}

	@Override
	public boolean delete(String nameFile) {
		
		if(nameFile !=null && nameFile.length() >0) {
			Path pathFile = Paths.get("uploads").resolve(nameFile).toAbsolutePath();
			File file = pathFile.toFile();
			if(file.exists() && file.canRead()) {
				file.delete();
				return true;
			}
		}
		
		return false;
	}

	@Override
	public Path getPath(String nameFile) {
		return Paths.get(UPLOAD_DIRECTORY).resolve(nameFile).toAbsolutePath();
	}

}
