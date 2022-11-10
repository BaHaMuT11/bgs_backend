package cl.bahatech.bahagamesbackend.model.request;

import lombok.Data;

@Data
public class AgregarFavoritoRequest {
    private Long usuario;
    private Long publicacion;
}
