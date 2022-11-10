package cl.bahatech.bahagamesbackend.model;

import lombok.Data;

@Data
public class Favorito {
    private Long usuario;
    private Long publicacion;
    private String juego;
    private String imagen;
}
