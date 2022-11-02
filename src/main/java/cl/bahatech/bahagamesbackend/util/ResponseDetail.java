package cl.bahatech.bahagamesbackend.util;

import org.springframework.http.HttpStatus;

public class ResponseDetail {
    public static final ResponseDetail RESPUESTA_OK = new ResponseDetail(HttpStatus.OK.value(), "OK");
    public static final ResponseDetail RESPUESTA_ERROR = new ResponseDetail(HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Ocurrió un error al ejecutar el procedimiento.");

    private int codigo;
    private String mensaje;
    private String detalle;

    public ResponseDetail(ResponseDetail detalleResponse) {
        this.codigo = detalleResponse.getCodigo();
        this.mensaje = detalleResponse.getMensaje();
        this.detalle = detalleResponse.getDetalle();
    }

    /**
     * Útil cuando son mensajes informativos y el mensaje es el mismo que el
     * detalle.
     *
     * @param codigo         int para asignar un valor
     * @param mensajeDetalle texto que setteará el mensaje y detalle
     */
    public ResponseDetail(int codigo, String mensajeDetalle) {
        this.codigo = codigo;
        this.mensaje = mensajeDetalle;
        this.detalle = mensajeDetalle;
    }

    public ResponseDetail(int codigo, String mensaje, String detalle) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.detalle = detalle;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    @Override
    public String toString() {
        return "DetalleResponse [codigo=" + codigo + ", mensaje=" + mensaje + ", detalle=" + detalle + "]";
    }
}
