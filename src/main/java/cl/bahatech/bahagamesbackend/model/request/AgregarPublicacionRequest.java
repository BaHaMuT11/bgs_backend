package cl.bahatech.bahagamesbackend.model.request;

import lombok.Data;

@Data
public class AgregarPublicacionRequest {
    private Long usuario;
    private String juego;
    private String descripcion;
    private Integer precio;
    private String plataforma;
    private String formato;
    private String imagen;
    private String estado;
}
