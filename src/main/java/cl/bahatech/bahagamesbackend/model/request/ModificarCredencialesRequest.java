package cl.bahatech.bahagamesbackend.model.request;

import lombok.Data;

@Data
public class ModificarCredencialesRequest {
    private String login;
    private String correo;
    private String pass;
}
