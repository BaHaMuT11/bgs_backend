package cl.bahatech.bahagamesbackend.model.request;

import lombok.Data;

@Data
public class MostrarInteresRequest {
    private Long usuario;
    private Long publicacion;
}
