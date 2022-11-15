package cl.bahatech.bahagamesbackend.model.response;

import cl.bahatech.bahagamesbackend.model.Adquisicion;
import cl.bahatech.bahagamesbackend.util.ResponseDetail;
import lombok.Data;

import java.util.List;

@Data
public class ObtenerAdquisicionesResponse {
    private List<Adquisicion> adquisiciones;
    private ResponseDetail estado = ResponseDetail.RESPUESTA_OK;
}
