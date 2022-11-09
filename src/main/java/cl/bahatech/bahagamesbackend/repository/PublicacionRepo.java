package cl.bahatech.bahagamesbackend.repository;

import cl.bahatech.bahagamesbackend.model.Publicacion;
import cl.bahatech.bahagamesbackend.model.request.AgregarCalificacionRequest;
import cl.bahatech.bahagamesbackend.model.request.AgregarPublicacionRequest;
import cl.bahatech.bahagamesbackend.model.request.DeshabilitarPublicacionRequest;
import cl.bahatech.bahagamesbackend.util.Constante;
import cl.bahatech.bahagamesbackend.util.CustomRuntimeException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PublicacionRepo {

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

    public List<Publicacion> obtenerPublicaciones() {

        Publicacion publicacion;
        List<Publicacion> publicaciones = new ArrayList<>();

        try {
            Connection conn = jdbcTemplate.getDataSource().getConnection();
            conn.setAutoCommit(false); //Debe quedar asī para postgre

            CallableStatement st = conn.prepareCall("CALL " + Constante.SP_OBTENER_PUBLICACIONES + "(?)");
            //Call sin curly braces para postgre

            st.registerOutParameter(1, Types.REF_CURSOR);
            st.execute();

            ResultSet rs = (ResultSet) st.getObject(1);

            while (rs.next()) {
                publicacion = new Publicacion();

                publicacion.setId(rs.getLong("id"));
                publicacion.setUsuario(rs.getLong("usuario"));
                publicacion.setJuego(rs.getString("juego"));
                publicacion.setDescripcion(rs.getString("descripcion"));
                publicacion.setPrecio(rs.getInt("precio"));
                publicacion.setPlataforma(rs.getString("plataforma"));
                publicacion.setFormato(rs.getString("formato"));
                publicacion.setImagen(rs.getString("imagen"));
                publicacion.setEstado(rs.getString("estado"));
                publicacion.setFechaCreacion(rs.getString("fecha_creacion"));
                publicacion.setRating(rs.getInt("rating"));

                publicaciones.add(publicacion);
            }

            conn.close();

            return publicaciones;

        } catch (SQLException e) {
            throw new CustomRuntimeException(e);
        }
    }

    public boolean deshabilitarPublicacion(DeshabilitarPublicacionRequest req) {

        try {
            Connection conn = jdbcTemplate.getDataSource().getConnection();

            CallableStatement st = conn.prepareCall("CALL " + Constante.SP_DESHABILITAR_PUBLICACION + "(?)");

            st.setLong(1, req.getId());

            st.execute();
            conn.close();

            return true;

        } catch (SQLException e) {
            throw new CustomRuntimeException(e);
        }
    }

    public boolean calificarPublicacion(AgregarCalificacionRequest req) {
        try {
            Connection conn = jdbcTemplate.getDataSource().getConnection();
            //conn.setAutoCommit(false); Debe quedar asī para postgre en lectura de datos

            CallableStatement st = conn.prepareCall("CALL " + Constante.SP_AGREGAR_CALIFICACION + "(?,?,?)");
            //Call sin curly braces para postgre

            st.setLong(1, req.getUsuario());
            st.setLong(2, req.getPublicacion());
            st.setInt( 3, req.getEstrellas());

            st.execute();

            conn.close();

            return true;

        } catch (SQLException e) {
            throw new CustomRuntimeException(e);
        }
    }

    public Publicacion obtenerPublicacion(Long id) {

        Publicacion publicacion = null;

        try {
            Connection conn = jdbcTemplate.getDataSource().getConnection();
            conn.setAutoCommit(false); //Debe quedar asī para postgre

            CallableStatement st = conn.prepareCall("CALL " + Constante.SP_OBTENER_PUBLICACION_ID + "(?,?)");
            //Call sin curly braces para postgre
            st.setLong(1, id);
            st.registerOutParameter(2, Types.REF_CURSOR);
            st.execute();

            ResultSet rs = (ResultSet) st.getObject(2);

            while (rs.next()) {
                publicacion = new Publicacion();

                publicacion.setId(rs.getLong("id"));
                publicacion.setUsuario(rs.getLong("usuario"));
                publicacion.setJuego(rs.getString("juego"));
                publicacion.setDescripcion(rs.getString("descripcion"));
                publicacion.setPrecio(rs.getInt("precio"));
                publicacion.setPlataforma(rs.getString("plataforma"));
                publicacion.setFormato(rs.getString("formato"));
                publicacion.setImagen(rs.getString("imagen"));
                publicacion.setEstado(rs.getString("estado"));
                publicacion.setFechaCreacion(rs.getString("fecha_creacion"));
                publicacion.setRating(rs.getInt("rating"));
            }

            conn.close();

            return publicacion;

        } catch (SQLException e) {
            throw new CustomRuntimeException(e);
        }
    }


}
