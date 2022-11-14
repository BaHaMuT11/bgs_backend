package cl.bahatech.bahagamesbackend.model.request;

import lombok.Data;

@Data
public class CerrarVentaRequest {
    private Long publicacion;
    private Long usuario;
}
