package com.yantas.pe.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Builder
public class ClientDto implements Serializable {
    private Integer idClient;
    @Size(min = 2, max = 25)
    @NotEmpty(message = "Nombre Requerido!")
    private String name;
    @Size(min = 2, max = 25)
    @NotEmpty(message = "Apellido Requerido!")
    private String lastName;
    @NotEmpty(message = "Correo requerido!")
    @Email
    private String email;
    private Date registrationDate;
}
