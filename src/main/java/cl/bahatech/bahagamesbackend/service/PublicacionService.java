package cl.bahatech.bahagamesbackend.service;

import cl.bahatech.bahagamesbackend.model.request.AgregarPublicacionRequest;
import cl.bahatech.bahagamesbackend.model.response.AgregarPublicacionResponse;
import cl.bahatech.bahagamesbackend.repository.PublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicacionService {

    @Autowired
    private PublicacionRepository repo;


    public AgregarPublicacionResponse agregarPublicacion(AgregarPublicacionRequest req) {
        boolean resultado = repo.agregarPublicacion(req);
        return new AgregarPublicacionResponse();
    }
}
