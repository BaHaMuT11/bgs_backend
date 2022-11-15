package cl.bahatech.bahagamesbackend.model;

import lombok.Data;

@Data
public class Adquisicion {
    private Long idPublicacion;
    private String juego;
    private String fechaCompra;
    private String vendedor;
    private String comprador;
}
