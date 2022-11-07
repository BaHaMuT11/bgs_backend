package cl.bahatech.bahagamesbackend.model;

import lombok.Data;

@Data
public class Publicacion {
    private Long id;
    private Long usuario;
    private String juego;
    private String descripcion;
    private Integer precio;
    private String plataforma;
    private String formato;
    private String imagen;
    private String estado;
    private String fechaCreacion;
}
