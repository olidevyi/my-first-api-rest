package com.yantas.pe.service.impl;

import com.yantas.pe.model.dao.ClientDao;
import com.yantas.pe.model.dto.ClientDto;
import com.yantas.pe.model.entity.Client;
import com.yantas.pe.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientImplService implements IClientService { // Debe llamar directamente al ClientDao

    @Autowired // Realiza la inyección de dependencia, la otra forma seria generar un constructor
    private ClientDao clientDao;

    @Override
    public List<Client> listAll() {
        return (List<Client>) clientDao.findAll();
    }

    @Transactional
    @Override
    public Client save(ClientDto clientDto) {
        Client client = Client.builder()
                .idClient(clientDto.getIdClient())
                .name(clientDto.getName())
                .lastName(clientDto.getLastName())
                .email(clientDto.getEmail())
                .registrationDate(clientDto.getRegistrationDate())
                .build();
        return clientDao.save(client);
    }

    @Transactional(readOnly = true)
    @Override
    public Client findById(Integer id) {
        return clientDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Client client) {
        clientDao.delete(client);
    }

    @Override
    public boolean existsById(Integer id) {
        return clientDao.existsById(id);
    }
}
