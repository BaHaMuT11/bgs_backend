package cl.bahatech.bahagamesbackend.controller;

import cl.bahatech.bahagamesbackend.model.request.AgregarPublicacionRequest;
import cl.bahatech.bahagamesbackend.model.response.AgregarPublicacionResponse;
import cl.bahatech.bahagamesbackend.model.response.ObtenerPublicacionesResponse;
import cl.bahatech.bahagamesbackend.service.PublicacionService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
