package cl.bahatech.bahagamesbackend.model.request;

import lombok.Data;

@Data
public class AgregarUsuarioRequest {
    private String usuario;
    private String pass;
    private String nombre;
    private String fechaNacimiento;
    private Integer edad;
    private String rut;
    private String fono;
    private String calle;
    private String numero;
    private String casa;
    private String region;
    private String comuna;
    private String correo;
    private String estado;
}
