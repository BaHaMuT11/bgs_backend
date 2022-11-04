package cl.bahatech.bahagamesbackend.model;

import lombok.Data;

@Data
public class Usuario {
    private Long id;
    private String nombre;
    private String usuario;
    private String pass;
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
    private String fechaCreacion;
    private String imagen;
}
