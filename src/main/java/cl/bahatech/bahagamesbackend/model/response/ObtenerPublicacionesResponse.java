package cl.bahatech.bahagamesbackend.model.response;

import cl.bahatech.bahagamesbackend.model.Publicacion;
import cl.bahatech.bahagamesbackend.util.ResponseDetail;
import lombok.Data;
import java.util.List;

@Data
public class ObtenerPublicacionesResponse {
    private List<Publicacion> publicaciones;
    private ResponseDetail estado = ResponseDetail.RESPUESTA_OK;
}
