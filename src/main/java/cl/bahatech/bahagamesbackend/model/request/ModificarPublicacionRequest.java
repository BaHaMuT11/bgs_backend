package cl.bahatech.bahagamesbackend.model.request;

import lombok.Data;
@Data
public class ModificarPublicacionRequest {
    private Long publicacion;
    private String plataforma;
    private String formato;
}
