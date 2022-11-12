package cl.bahatech.bahagamesbackend.model.response;

import cl.bahatech.bahagamesbackend.model.Interesado;
import cl.bahatech.bahagamesbackend.util.ResponseDetail;
import lombok.Data;

import java.util.List;

@Data
public class ObtenerInteresadosResponse {
    private List<Interesado> interesados;
    private ResponseDetail estado = ResponseDetail.RESPUESTA_OK;
}
