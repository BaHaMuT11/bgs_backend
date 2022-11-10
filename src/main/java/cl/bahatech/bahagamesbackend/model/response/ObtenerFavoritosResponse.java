package cl.bahatech.bahagamesbackend.model.response;

import cl.bahatech.bahagamesbackend.model.Favorito;
import cl.bahatech.bahagamesbackend.util.ResponseDetail;
import lombok.Data;

import java.util.List;

@Data
public class ObtenerFavoritosResponse {
    private List<Favorito> favoritos;
    private ResponseDetail estado = ResponseDetail.RESPUESTA_OK;
}
