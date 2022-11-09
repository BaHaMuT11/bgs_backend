package cl.bahatech.bahagamesbackend.model.response;

import cl.bahatech.bahagamesbackend.model.Publicacion;
import cl.bahatech.bahagamesbackend.util.ResponseDetail;
import lombok.Data;

@Data
public class ObtenerPublicacionIdResponse {
    private Publicacion publicacion;
    private ResponseDetail estado = ResponseDetail.RESPUESTA_OK;

}
