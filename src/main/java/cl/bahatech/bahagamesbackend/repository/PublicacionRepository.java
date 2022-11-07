package cl.bahatech.bahagamesbackend.repository;

import cl.bahatech.bahagamesbackend.model.request.AgregarPublicacionRequest;
import cl.bahatech.bahagamesbackend.util.Constante;
import cl.bahatech.bahagamesbackend.util.CustomRuntimeException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.sql.*;

@Repository
public class PublicacionRepository {

    @Resource(name = "baha_game_store_ws")
    private JdbcTemplate jdbcTemplate;

    public boolean agregarPublicacion(AgregarPublicacionRequest req) {
        try {
            Connection conn = jdbcTemplate.getDataSource().getConnection();
            //conn.setAutoCommit(false); Debe quedar asī para postgre en lectura de datos

            CallableStatement st = conn.prepareCall("CALL " + Constante.SP_AGREGAR_PUBLICACION + "(?, ?, ?, ?, ?, ?, ?, ?)");
            //Call sin curly braces para postgre

            st.setLong(  1, req.getUsuario());
            st.setString(2, req.getJuego());
            st.setString(3, req.getDescripcion());
            st.setInt(   4, req.getPrecio());
            st.setString(5, req.getPlataforma());
            st.setString(6, req.getFormato());
            st.setString(7, req.getImagen());
            st.setString(8, req.getEstado());

            st.execute();

            conn.close();

            return true;

        } catch (SQLException e) {
            throw new CustomRuntimeException(e);
        }
    }


}
