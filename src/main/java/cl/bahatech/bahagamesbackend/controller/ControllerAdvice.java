package cl.bahatech.bahagamesbackend.controller;

import cl.bahatech.bahagamesbackend.util.CustomRuntimeException;
import cl.bahatech.bahagamesbackend.util.ResponseDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ControllerAdvice {

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseDetail errorException(Throwable e) {
        log.debug("Entrando al método errorException");

        ResponseDetail detalleResponse = new ResponseDetail(ResponseDetail.RESPUESTA_ERROR);
        detalleResponse.setDetalle(e.toString());

        if (e instanceof CustomRuntimeException) {
            detalleResponse.setMensaje(e.getMessage());
        }

        log.error("Ocurrió un error al ejecutar el procedimiento:", e);
        log.debug("Saliendo del método errorException");

        return detalleResponse;
    }

}
