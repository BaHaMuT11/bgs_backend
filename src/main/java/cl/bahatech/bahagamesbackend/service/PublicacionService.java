package cl.bahatech.bahagamesbackend.service;

import cl.bahatech.bahagamesbackend.model.Favorito;
import cl.bahatech.bahagamesbackend.model.Ganador;
import cl.bahatech.bahagamesbackend.model.Interesado;
import cl.bahatech.bahagamesbackend.model.Publicacion;
import cl.bahatech.bahagamesbackend.model.request.*;
import cl.bahatech.bahagamesbackend.model.response.*;
import cl.bahatech.bahagamesbackend.repository.PublicacionRepo;
import cl.bahatech.bahagamesbackend.util.CustomRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicacionService {

    @Autowired
    private PublicacionRepo repo;

    public AgregarPublicacionResponse agregarPublicacion(AgregarPublicacionRequest req) {
        boolean resultado = repo.agregarPublicacion(req);
        return new AgregarPublicacionResponse();
    }
    public ObtenerPublicacionesResponse obtenerPublicaciones() {

        ObtenerPublicacionesResponse resp = new ObtenerPublicacionesResponse();
        resp.setPublicaciones(repo.obtenerPublicaciones());

        return resp;
    }
    public DeshabilitarPublicacionResponse deshabilitarPublicacion(DeshabilitarPublicacionRequest req) {
        boolean resultado = repo.deshabilitarPublicacion(req);
        return new DeshabilitarPublicacionResponse();
    }
    public AgregarCalificacionResponse calificarPublicacion (AgregarCalificacionRequest req) {
        boolean resultado = repo.calificarPublicacion(req);
        return new AgregarCalificacionResponse();
    }
    public ObtenerPublicacionIdResponse obtenerPublicacionPorId(Long id) {
        ObtenerPublicacionIdResponse resp = new ObtenerPublicacionIdResponse();

        Publicacion publicacion = repo.obtenerPublicacion(id);

        if (publicacion != null) {
            resp.setPublicacion(publicacion);
        } else {
            throw new CustomRuntimeException("Publicación no encontrada");
        }
        return resp;
    }
    public AgregarFavoritoResponse agregarFavorito (AgregarFavoritoRequest req) {
        boolean resultado = repo.agregarFavorito(req);
        return new AgregarFavoritoResponse();
    }
    public ObtenerFavoritosResponse obtenerFavsUsuario(Long id) {
        ObtenerFavoritosResponse resp = new ObtenerFavoritosResponse();

        List<Favorito> favs = repo.obtenerFavoritosUsuario(id);
        resp.setFavoritos(favs);
        return resp;
    }
    public EliminarFavoritoResponse eliminarFavorito(Long usuario, Long publicacion) {
        boolean resultado = repo.eliminarFavorito(usuario, publicacion);
        return new EliminarFavoritoResponse();
    }
    public MostrarInteresResponse mostrarInteres (MostrarInteresRequest req) {
        boolean resultado = repo.mostrarInteres(req);
        return new MostrarInteresResponse();
    }
    public ObtenerInteresadosResponse obtenerInteresados(Long id) {
        ObtenerInteresadosResponse resp = new ObtenerInteresadosResponse();

        List<Interesado> ints = repo.obtenerInteresadosPublicacion(id);
        resp.setInteresados(ints);
        return resp;
    }
    public CerrarVentaResponse cerrarVenta(CerrarVentaRequest req) {
        boolean resultado = repo.cerrarVenta(req);
        return new CerrarVentaResponse();
    }
    public ObtenerGanadorResponse obtenerGanador(Long id) {
        ObtenerGanadorResponse resp = new ObtenerGanadorResponse();

        Ganador ganador = repo.obtenerGanador(id);
        resp.setGanador(ganador);

        return resp;
    }

}
