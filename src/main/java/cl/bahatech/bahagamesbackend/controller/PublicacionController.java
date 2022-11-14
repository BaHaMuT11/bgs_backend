package cl.bahatech.bahagamesbackend.controller;

import cl.bahatech.bahagamesbackend.model.request.*;
import cl.bahatech.bahagamesbackend.model.response.*;
import cl.bahatech.bahagamesbackend.service.PublicacionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PublicacionController {

    @Autowired
    private PublicacionService servicio;

    @PostMapping("/publicacion/agregar")
    @ApiOperation( value = "Agrega una publicacion al sistema",
            notes = "Se debe proporcionar info del DRM como JSON",
            response = AgregarPublicacionResponse.class)
    public ResponseEntity<AgregarPublicacionResponse> agregarDRM(@RequestBody AgregarPublicacionRequest publi) {

        AgregarPublicacionResponse resp = servicio.agregarPublicacion(publi);
        log.debug("Resulado de respuesta: {}", resp);
        return ResponseEntity.ok(resp);
    }
    @GetMapping("/publicaciones")
    @ApiOperation( value = "Obtiene todas las publicaciones",
            notes = "No se necesitan parámetros u objetos de entrada",
            response = ObtenerPublicacionesResponse.class)
    public ResponseEntity<ObtenerPublicacionesResponse> obtenerPublicaciones() {
        ObtenerPublicacionesResponse resp = servicio.obtenerPublicaciones();
        log.debug("Resultado de respuesta: {} ", resp);
        return ResponseEntity.ok(resp);
    }

    @PutMapping("/publicacion/deshabilitar")
    @ApiOperation( value = "Deshabilita (elimina) una publicación del sistema",
            notes = "Se debe proporcionar id de la publicación a deshabilitar",
            response = DeshabilitarPublicacionResponse.class)
    public ResponseEntity<DeshabilitarPublicacionResponse> deshabilitar(@RequestBody DeshabilitarPublicacionRequest reqId) {
        DeshabilitarPublicacionResponse resp = servicio.deshabilitarPublicacion(reqId);
        log.debug("Resulado de respuesta: {}", resp);
        return ResponseEntity.ok(resp);
    }


    @PostMapping("/publicacion/calificar")
    @ApiOperation( value = "Califica una publicación",
            notes = "Se debe proporcionar el id de usuario que califica, el id de publicación y la calificación, " +
                    "como JSON",
            response = AgregarCalificacionResponse.class)
    public ResponseEntity<AgregarCalificacionResponse> calificarPublicacion(
            @RequestBody AgregarCalificacionRequest req) {

        AgregarCalificacionResponse resp = servicio.calificarPublicacion(req);
        log.debug("Resulado de respuesta: {}", resp);
        return ResponseEntity.ok(resp);
    }


    @GetMapping("/publicacion/get/{id}")
    @ApiOperation( value = "Obtiene una publicación provisto el id",
            notes = "Enviar por parámetros una ID de publicación que exista",
            response = ObtenerPublicacionIdResponse.class)
    public ResponseEntity<ObtenerPublicacionIdResponse> obtenerPublicacionPorId(@PathVariable Long id) {

        ObtenerPublicacionIdResponse resp = servicio.obtenerPublicacionPorId(id);
        log.debug("Resultado de respuesta: {} ", resp);
        return ResponseEntity.ok(resp);

    }


    @PostMapping("/publicacion/agregar_favorito")
    @ApiOperation( value = "Agrega a los favoritos de un usuario una publicación",
            notes = "Se debe proporcionar el id de usuario y de publicación como JSON",
            response = AgregarFavoritoResponse.class)
    public ResponseEntity<AgregarFavoritoResponse> agregarFavorito(
            @RequestBody AgregarFavoritoRequest req) {

        AgregarFavoritoResponse resp = servicio.agregarFavorito(req);
        log.debug("Resulado de respuesta: {}", resp);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/publicacion/favoritos/{id}")
    @ApiOperation( value = "Obtiene los favoritos provisto el id de usuario",
            notes = "Enviar por parámetros una ID de usuario que exista",
            response = ObtenerFavoritosResponse.class)
    public ResponseEntity<ObtenerFavoritosResponse> obtenerFavsPorId(@PathVariable Long id) {
        ObtenerFavoritosResponse resp = servicio.obtenerFavsUsuario(id);
        log.debug("Resultado de respuesta: {} ", resp);
        return ResponseEntity.ok(resp);

    }


    @DeleteMapping("/favoritos/eliminar/{usuario}/{publicacion}")
    @ApiOperation( value = "Elimina una publicación favorita de un usuario en el sistema",
            notes = "Enviar por parámetros las id de usuario y publicación",
            response = EliminarFavoritoResponse.class)
    public ResponseEntity<EliminarFavoritoResponse> eliminarFavorito
            (@ApiParam("ID del usuario") @PathVariable Long usuario,
             @ApiParam("ID de la publicaciœn") @PathVariable Long publicacion) {

        EliminarFavoritoResponse resp = servicio.eliminarFavorito(usuario, publicacion);

        log.debug("Resulado de respuesta: {}", resp);

        return ResponseEntity.ok(resp);
    }

    @PostMapping("/publicacion/mostrar_interes")
    @ApiOperation( value = "Vincula un interesado a una publicación",
            notes = "Se debe proporcionar el id de usuario y de publicación como JSON",
            response = MostrarInteresResponse.class)
    public ResponseEntity<MostrarInteresResponse> mostrarInteres(@RequestBody MostrarInteresRequest req) {
        MostrarInteresResponse resp = servicio.mostrarInteres(req);
        log.debug("Resulado de respuesta: {}", resp);
        return ResponseEntity.ok(resp);
    }


    @GetMapping("/publicacion/interesados/{id}")
    @ApiOperation( value = "Obtiene los interesados provisto el id de publicación",
            notes = "Enviar por parámetros una ID de publicación que exista",
            response = ObtenerInteresadosResponse.class)
    public ResponseEntity<ObtenerInteresadosResponse> obtenerInteresadosId(@PathVariable Long id) {
        ObtenerInteresadosResponse resp = servicio.obtenerInteresados(id);
        log.debug("Resultado de respuesta: {} ", resp);
        return ResponseEntity.ok(resp);

    }


    @PutMapping("/interesados/cerrar")
    @ApiOperation( value = "Cierra una venta",
            notes = "Se debe proporcionar info de publicación e interesado como JSON",
            response = CerrarVentaResponse.class)
    public ResponseEntity<CerrarVentaResponse> cerrarVenta(@RequestBody CerrarVentaRequest req) {
        CerrarVentaResponse resp = servicio.cerrarVenta(req);
        log.debug("Resulado de respuesta: {}", resp);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/ganador/{id}")
    @ApiOperation( value = "Obtiene el ganador de una publicación",
            notes = "Enviar por parámetros una ID de publicación que exista",
            response = ObtenerGanadorResponse.class)
    public ResponseEntity<ObtenerGanadorResponse> obtenerGanador(@PathVariable Long id) {

        ObtenerGanadorResponse resp = servicio.obtenerGanador(id);
        log.debug("Resultado de respuesta: {} ", resp);
        return ResponseEntity.ok(resp);

    }

}
