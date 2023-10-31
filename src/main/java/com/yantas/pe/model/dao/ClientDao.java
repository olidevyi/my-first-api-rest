package com.yantas.pe.model.dao;

import com.yantas.pe.model.entity.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientDao extends CrudRepository<Client, Integer> {
}
