package com.yantas.pe.controller;

import com.yantas.pe.exception.ResourceNotFoundException;
import com.yantas.pe.model.dto.ClientDto;
import com.yantas.pe.model.entity.Client;
import com.yantas.pe.model.payload.MessageResponse;
import com.yantas.pe.service.IClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ClientController {

    @Autowired
    private IClientService clientService;

    @GetMapping("/clients")
    public ResponseEntity<?> showAll() {
        List<Client> getList = clientService.listAll();
        if(getList == null || getList.isEmpty()) {
            throw new ResourceNotFoundException("clients"); // Excepción personalizada
        }
        return new ResponseEntity<>(MessageResponse
                .builder()
                .message("Se encontraron los siguientes registros.")
                .object(getList)
                .build(), HttpStatus.OK);
    }

    @PostMapping("client")
    public ResponseEntity<?> create(@RequestBody @Valid ClientDto clientDto) {
        Client clientSave = null;
        try {
            clientSave = clientService.save(clientDto);
            clientDto = ClientDto
                    .builder()
                    .idClient(clientSave.getIdClient())
                    .name(clientSave.getName())
                    .lastName(clientSave.getLastName())
                    .email(clientSave.getEmail())
                    .registrationDate(clientSave.getRegistrationDate())
                    .build();
            return new ResponseEntity<>(MessageResponse
                    .builder()
                    .message("Guardado correctamente")
                    .object(clientDto)
                    .build(), HttpStatus.CREATED);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(MessageResponse
                    .builder()
                    .message(e.getMessage())
                    .object(null)
                    .build(), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PutMapping("client/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid ClientDto clientDto, @PathVariable Integer id) {
        Client clientUpdate = null;
        try {
            if(clientService.existsById(id)){
                clientDto.setIdClient(id); // Si el id del payload no es el mismo que el path, el path reemplaza el valor al payload.
                clientUpdate = clientService.save(clientDto);
                clientDto = ClientDto
                        .builder()
                        .idClient(clientUpdate.getIdClient())
                        .name(clientUpdate.getName())
                        .lastName(clientUpdate.getLastName())
                        .email(clientUpdate.getEmail())
                        .registrationDate(clientUpdate.getRegistrationDate())
                        .build();
                return new ResponseEntity<>(MessageResponse
                        .builder()
                        .message("Actualizado correctamente")
                        .object(clientDto)
                        .build(), HttpStatus.CREATED);
            } else {
                throw new ResourceNotFoundException("client", "id", id); // Excepción personalizada
            }
        } catch (DataAccessException e) {
            return new ResponseEntity<>(MessageResponse
                    .builder()
                    .message(e.getMessage())
                    .object(null)
                    .build(), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @DeleteMapping("client/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            Client clientDelete = clientService.findById(id);
            clientService.delete(clientDelete);
            return new ResponseEntity<>(clientDelete, HttpStatus.NO_CONTENT);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(MessageResponse.builder()
                    .message(e.getMessage())
                    .object(null)
                    .build(), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @GetMapping("client/{id}")
    public ResponseEntity<?> showById(@PathVariable Integer id) {
        Client client = clientService.findById(id);
        if(client == null) {
            throw new ResourceNotFoundException("client", "id", id); // Excepción personalizada
        }
        return new ResponseEntity<>(MessageResponse
                .builder()
                .message("Se encontró el cliente.")
                .object(ClientDto
                        .builder()
                        .idClient(client.getIdClient())
                        .name(client.getName())
                        .lastName(client.getLastName())
                        .email(client.getEmail())
                        .registrationDate(client.getRegistrationDate())
                        .build())
                .build(), HttpStatus.OK);
    }
}
