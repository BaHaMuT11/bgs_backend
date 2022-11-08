package cl.bahatech.bahagamesbackend.model.response;

import cl.bahatech.bahagamesbackend.util.ResponseDetail;
import lombok.Data;

@Data
public class AgregarCalificacionResponse {
    private ResponseDetail estado = ResponseDetail.RESPUESTA_OK;
}
