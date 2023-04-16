// Source Code: https://github.com/leonardombr/Generic-CRUD-Spring-Boot/blob/master/src/main/java/com/crud/generic/repository/generic/GenericRepository.java

package com.bolsadeideas.springboot.backend.apirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bolsadeideas.springboot.backend.apirest.models.entity.BaseEntity;


public interface GenericRepository<T extends BaseEntity> extends JpaRepository<T, Long> {

}
