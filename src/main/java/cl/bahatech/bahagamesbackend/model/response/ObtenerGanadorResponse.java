package cl.bahatech.bahagamesbackend.model.response;

import cl.bahatech.bahagamesbackend.model.Ganador;
import cl.bahatech.bahagamesbackend.util.ResponseDetail;
import lombok.Data;

@Data
public class ObtenerGanadorResponse {
    private Ganador ganador;
    private ResponseDetail estado = ResponseDetail.RESPUESTA_OK;
}
