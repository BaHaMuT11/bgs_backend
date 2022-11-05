package cl.bahatech.bahagamesbackend.service;

import cl.bahatech.bahagamesbackend.model.Usuario;
import cl.bahatech.bahagamesbackend.model.request.AgregarUsuarioRequest;
import cl.bahatech.bahagamesbackend.model.request.ModificarCredencialesRequest;
import cl.bahatech.bahagamesbackend.model.request.ModificarUsuarioRequest;
import cl.bahatech.bahagamesbackend.model.response.AgregarUsuarioResponse;
import cl.bahatech.bahagamesbackend.model.response.BuscarUsuarioLoginResponse;
import cl.bahatech.bahagamesbackend.model.response.ModificarCredencialesResponse;
import cl.bahatech.bahagamesbackend.model.response.ModificarUsuarioResponse;
import cl.bahatech.bahagamesbackend.repository.UsuarioRepo;
import cl.bahatech.bahagamesbackend.util.CustomRuntimeException;
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

    public BuscarUsuarioLoginResponse buscarUsuarioLogin (String user) {
        BuscarUsuarioLoginResponse resp = new BuscarUsuarioLoginResponse();

        Usuario usu = repo.buscarUsuarioPorUsername(user);

        if (usu != null) {
            resp.setUsuario(repo.buscarUsuarioPorUsername(user));
        }
        else {
            throw new CustomRuntimeException("Usuario no encontrado");
        }


        return resp;
    }

    public ModificarUsuarioResponse modificarUsuario(ModificarUsuarioRequest usuario) {
        boolean resultado = repo.modificarUsuario(usuario);
        return new ModificarUsuarioResponse();
    }

    public ModificarCredencialesResponse modificarCredenciales(ModificarCredencialesRequest req) {
        boolean resultado = repo.modificarCredenciales(req);
        return new ModificarCredencialesResponse();
    }
}
