package com.yantas.pe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    private String resourceName; // "client"
    private String fieldName;   // "id"
    private Object fieldValue;  // 1

    // ALT + INSERT y seleccionar "constructor" para autogenerar el constructor.
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s no fue encontrado con: %s='%s'", resourceName, fieldName, fieldValue)); // Escribirlo
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public ResourceNotFoundException(String resourceName) {
        super(String.format("No hay registros de %s en el sistema", resourceName));
        this.resourceName = resourceName;
    }
}
