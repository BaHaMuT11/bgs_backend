package cl.bahatech.bahagamesbackend.model;

import lombok.Data;

@Data
public class Ganador {
    private String nombre;
    private Long idUsuario;
    private Boolean esGanador;
    private String fechaCompra;
    private Long publicacion;
}
