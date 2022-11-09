package cl.bahatech.bahagamesbackend.controller;


import cl.bahatech.bahagamesbackend.model.request.AgregarUsuarioRequest;
import cl.bahatech.bahagamesbackend.model.request.ModificarCredencialesRequest;
import cl.bahatech.bahagamesbackend.model.request.ModificarUsuarioRequest;
import cl.bahatech.bahagamesbackend.model.response.*;
import cl.bahatech.bahagamesbackend.model.security.JwtRequest;
import cl.bahatech.bahagamesbackend.model.security.JwtResponse;
import cl.bahatech.bahagamesbackend.security.JwtTokenUtil;
import cl.bahatech.bahagamesbackend.service.UsuarioService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@Slf4j
public class AuthController {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService jwtInMemoryUserDetailsService;

    @Autowired
    private UsuarioService sUsuario;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> generateAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
            throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = jwtInMemoryUserDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }
    @PostMapping("/usuarios/agregarUsuario")
    public ResponseEntity<AgregarUsuarioResponse> agregarUsuario(@RequestBody AgregarUsuarioRequest req) {
        AgregarUsuarioResponse resp =  sUsuario.agregarUsuario(req);
        log.debug("Resulado de respuesta: {}", resp);
        return ResponseEntity.ok(resp);
    }

    private void authenticate(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @GetMapping("/usuario/{login}")
    @ApiOperation( value = "Obtiene un usuario provisto el login",
            notes = "Enviar por parámetros una login de usuario que exista",
            response = BuscarUsuarioLoginResponse.class)
    public ResponseEntity<BuscarUsuarioLoginResponse> obtenerUsuarioPorLogin(@PathVariable String login) {

        BuscarUsuarioLoginResponse resp = sUsuario.buscarUsuarioLogin(login);
        log.debug("Resultado de respuesta: {} ", resp);
        return ResponseEntity.ok(resp);

    }

    @PutMapping("/usuario/modificar")
    @ApiOperation( value = "Modifica info de un usuario",
            notes = "Se debe proporcionar info del usuario como JSON",
            response = ModificarUsuarioResponse.class)
    public ResponseEntity<ModificarUsuarioResponse> modificarUsuario(@RequestBody ModificarUsuarioRequest usu) {
        ModificarUsuarioResponse resp = sUsuario.modificarUsuario(usu);
        log.debug("Resulado de respuesta: {}", resp);
        return ResponseEntity.ok(resp);
    }

    @PutMapping("/udata/modificar")
    @ApiOperation( value = "Modifica credenciales de un usuario",
            notes = "Se debe proporcionar info de las credenciales como JSON",
            response = ModificarCredencialesResponse.class)
    public ResponseEntity<ModificarCredencialesResponse> modificarUsuario(@RequestBody ModificarCredencialesRequest creds) {
        ModificarCredencialesResponse resp = sUsuario.modificarCredenciales(creds);
        log.debug("Resulado de respuesta: {}", resp);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/usuariof/{id}")
    @ApiOperation( value = "Obtiene un usuario provisto el id",
            notes = "Enviar por parámetros un id de usuario que exista",
            response = BuscarUsuarioIdResponse.class)
    public ResponseEntity<BuscarUsuarioIdResponse> obtenerUsuarioPorId(@PathVariable Long id) {

        BuscarUsuarioIdResponse resp = sUsuario.buscarUsuarioId(id);
        log.debug("Resultado de respuesta: {} ", resp);
        return ResponseEntity.ok(resp);

    }
}
