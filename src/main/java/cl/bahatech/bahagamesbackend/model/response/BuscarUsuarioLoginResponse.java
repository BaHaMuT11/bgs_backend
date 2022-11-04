package cl.bahatech.bahagamesbackend.model.response;

import cl.bahatech.bahagamesbackend.model.Usuario;
import cl.bahatech.bahagamesbackend.util.ResponseDetail;
import lombok.Data;

@Data
public class BuscarUsuarioLoginResponse {
    private Usuario usuario;
    private ResponseDetail estado = ResponseDetail.RESPUESTA_OK;
}
