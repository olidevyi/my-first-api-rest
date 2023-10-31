package com.yantas.pe.service;

import com.yantas.pe.model.dto.ClientDto;
import com.yantas.pe.model.entity.Client;

import java.util.List;

public interface IClientService {
    List<Client> listAll();
    Client save(ClientDto clientDto);
    Client findById(Integer id);
    void delete(Client client);
    boolean existsById(Integer id);
}
