package cl.bahatech.bahagamesbackend.model.request;

import lombok.Data;

@Data
public class ModificarUsuarioRequest {
    private String login;
    private String imagen;
    private String nombre;
    private String fechaNacimiento;
    private String rut;
    private String fono;
    private String calle;
    private String numero;
    private String casa;
    private String region;
    private String comuna;
    private Integer edad;
}
