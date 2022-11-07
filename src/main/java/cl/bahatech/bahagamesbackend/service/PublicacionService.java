package cl.bahatech.bahagamesbackend.service;

import cl.bahatech.bahagamesbackend.model.request.AgregarPublicacionRequest;
import cl.bahatech.bahagamesbackend.model.request.DeshabilitarPublicacionRequest;
import cl.bahatech.bahagamesbackend.model.response.AgregarPublicacionResponse;
import cl.bahatech.bahagamesbackend.model.response.DeshabilitarPublicacionResponse;
import cl.bahatech.bahagamesbackend.model.response.ObtenerPublicacionesResponse;
import cl.bahatech.bahagamesbackend.repository.PublicacionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
