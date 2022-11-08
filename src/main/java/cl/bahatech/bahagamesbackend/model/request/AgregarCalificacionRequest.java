package cl.bahatech.bahagamesbackend.model.request;

import lombok.Data;

@Data
public class AgregarCalificacionRequest {
    private Long usuario;
    private Long publicacion;
    private Integer estrellas;
}
