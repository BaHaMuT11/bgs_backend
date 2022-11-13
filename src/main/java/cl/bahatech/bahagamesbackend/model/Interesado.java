package cl.bahatech.bahagamesbackend.model;

import lombok.Data;

@Data
public class Interesado {
    private Long idInteresado;
    private String interesado;
    private String correoInteresado;
    private String fonoInteresado;
    private String regionInteresado;
    private String comunaInteresado;
    private Long idVendedor;
    private String vendedor;
    private Long idPublicacion;
    private String publicacion;
    private String fechaPublicacion;
    private Integer precio;
    private String plataforma;
    private String formato;
}
