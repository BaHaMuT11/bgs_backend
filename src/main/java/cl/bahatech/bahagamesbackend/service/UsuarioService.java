package cl.bahatech.bahagamesbackend.service;

import cl.bahatech.bahagamesbackend.model.request.AgregarUsuarioRequest;
import cl.bahatech.bahagamesbackend.model.response.AgregarUsuarioResponse;
import cl.bahatech.bahagamesbackend.repository.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepo repo;

    public AgregarUsuarioResponse agregarUsuario(AgregarUsuarioRequest req) {
        repo.agregarUsuario(req);
        return new AgregarUsuarioResponse();
    }
}
