package cl.bahatech.bahagamesbackend.repository;

import cl.bahatech.bahagamesbackend.model.*;
import cl.bahatech.bahagamesbackend.model.request.*;
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
    public boolean agregarFavorito(AgregarFavoritoRequest req) {
        try {
            Connection conn = jdbcTemplate.getDataSource().getConnection();
            //conn.setAutoCommit(false); Debe quedar asī para postgre en lectura de datos

            CallableStatement st = conn.prepareCall("CALL " + Constante.SP_AGREGAR_FAVORITO+ "(?,?)");
            //Call sin curly braces para postgre

            st.setLong(1, req.getUsuario());
            st.setLong(2, req.getPublicacion());

            st.execute();

            conn.close();

            return true;

        } catch (SQLException e) {
            throw new CustomRuntimeException(e);
        }
    }
    public List<Favorito> obtenerFavoritosUsuario(Long id) {

        List<Favorito> favoritos = new ArrayList<>();
        Favorito favorito = null;

        try {
            Connection conn = jdbcTemplate.getDataSource().getConnection();
            conn.setAutoCommit(false); //Debe quedar asī para postgre

            CallableStatement st = conn.prepareCall("CALL " + Constante.SP_OBTENER_FAVORITOS_USUARIO + "(?,?)");
            //Call sin curly braces para postgre
            st.setLong(1, id);
            st.registerOutParameter(2, Types.REF_CURSOR);
            st.execute();

            ResultSet rs = (ResultSet) st.getObject(2);

            while (rs.next()) {
                favorito = new Favorito();

                favorito.setUsuario(rs.getLong("usuario"));
                favorito.setPublicacion(rs.getLong("publicacion"));
                favorito.setImagen(rs.getString("imagen"));
                favorito.setJuego(rs.getString("juego"));

                favoritos.add(favorito);
            }

            conn.close();

            return favoritos;

        } catch (SQLException e) {
            throw new CustomRuntimeException(e);
        }
    }
    public boolean eliminarFavorito(Long usuario, Long publicacion) {
        try {
            Connection conn = jdbcTemplate.getDataSource().getConnection();
            CallableStatement st = conn.prepareCall("CALL " + Constante.SP_ELIMINAR_FAVORTIO_USUARIO + "(?,?)");
            st.setLong(1, usuario);
            st.setLong(2, publicacion);

            st.execute();
            conn.close();

            return true;

        } catch (SQLException e) {
            throw new CustomRuntimeException(e);
        }
    }
    public boolean mostrarInteres(MostrarInteresRequest req) {
        try {
            Connection conn = jdbcTemplate.getDataSource().getConnection();
            //conn.setAutoCommit(false); Debe quedar asī para postgre en lectura de datos

            CallableStatement st = conn.prepareCall("CALL " + Constante.SP_MOSTRAR_INTERES + "(?,?)");
            //Call sin curly braces para postgre

            st.setLong(1, req.getUsuario());
            st.setLong(2, req.getPublicacion());

            st.execute();

            conn.close();

            return true;

        } catch (SQLException e) {
            throw new CustomRuntimeException(e);
        }
    }
    public List<Interesado> obtenerInteresadosPublicacion(Long id) {

        List<Interesado> interesados = new ArrayList<>();
        Interesado interesado = null;

        try {
            Connection conn = jdbcTemplate.getDataSource().getConnection();
            conn.setAutoCommit(false); //Debe quedar asī para postgre

            CallableStatement st = conn.prepareCall("CALL " + Constante.SP_OBTENER_INTERESADOS + "(?,?)");
            //Call sin curly braces para postgre
            st.setLong(1, id);
            st.registerOutParameter(2, Types.REF_CURSOR);
            st.execute();

            ResultSet rs = (ResultSet) st.getObject(2);

            while (rs.next()) {
                interesado = new Interesado();

                interesado.setIdInteresado(rs.getLong("id_interesado"));
                interesado.setInteresado(rs.getString("interesado"));
                interesado.setCorreoInteresado(rs.getString("correo_interesado"));
                interesado.setFonoInteresado(rs.getString("fono_interesado"));
                interesado.setComunaInteresado(rs.getString("comuna_interesado"));
                interesado.setRegionInteresado(rs.getString("region_interesado"));
                interesado.setIdVendedor(rs.getLong("id_vendedor"));
                interesado.setVendedor(rs.getString("vendedor"));
                interesado.setIdPublicacion(rs.getLong("id_publicacion"));
                interesado.setPublicacion(rs.getString("publicacion"));
                interesado.setFechaPublicacion(rs.getString("fecha_publicacion"));
                interesado.setPrecio(rs.getInt("precio"));
                interesado.setPlataforma(rs.getString("plataforma"));
                interesado.setFormato(rs.getString("formato"));

                interesados.add(interesado);
            }

            conn.close();

            return interesados;

        } catch (SQLException e) {
            throw new CustomRuntimeException(e);
        }
    }
    public boolean cerrarVenta(CerrarVentaRequest req) {

        try {
            Connection conn = jdbcTemplate.getDataSource().getConnection();

            CallableStatement st = conn.prepareCall("CALL " + Constante.SP_CERRAR_VENTA + "(?,?)");

            st.setLong(1, req.getPublicacion());
            st.setLong(2, req.getUsuario());

            st.execute();
            conn.close();

            return true;

        } catch (SQLException e) {
            throw new CustomRuntimeException(e);
        }
    }
    public Ganador obtenerGanador(Long id) {

        Ganador ganador = null;

        try {
            Connection conn = jdbcTemplate.getDataSource().getConnection();
            conn.setAutoCommit(false); //Debe quedar asī para postgre

            CallableStatement st = conn.prepareCall("CALL " + Constante.SP_OBTENER_GANADOR+ "(?,?)");
            //Call sin curly braces para postgre
            st.setLong(1, id);
            st.registerOutParameter(2, Types.REF_CURSOR);
            st.execute();

            ResultSet rs = (ResultSet) st.getObject(2);

            while (rs.next()) {
                ganador = new Ganador();

                ganador.setIdUsuario(rs.getLong("id_usuario"));
                ganador.setNombre(rs.getString("nombre"));
                ganador.setEsGanador(rs.getBoolean("ganador"));
                ganador.setFechaCompra(rs.getString("fecha_compra"));
                ganador.setPublicacion(rs.getLong("publicacion"));
            }

            conn.close();

            return ganador;

        } catch (SQLException e) {
            throw new CustomRuntimeException(e);
        }
    }
    public boolean modificarPublicacion(ModificarPublicacionRequest req) {

        try {
            Connection conn = jdbcTemplate.getDataSource().getConnection();

            CallableStatement st = conn.prepareCall("CALL " + Constante.SP_MODIFICAR_PUBLICACION + "(?,?,?)");

            st.setLong(1, req.getPublicacion());
            st.setString(2, req.getPlataforma());
            st.setString(3, req.getFormato());

            st.execute();
            conn.close();

            return true;

        } catch (SQLException e) {
            throw new CustomRuntimeException(e);
        }
    }
    public List<Adquisicion> obtenerAdquisicionesUsuario(Long id) {

        List<Adquisicion> adquisiciones = new ArrayList<>();
        Adquisicion adquisicion = null;

        try {
            Connection conn = jdbcTemplate.getDataSource().getConnection();
            conn.setAutoCommit(false); //Debe quedar asī para postgre

            CallableStatement st = conn.prepareCall("CALL " + Constante.SP_OBTENER_ADQUISICIONES + "(?,?)");
            //Call sin curly braces para postgre
            st.setLong(1, id);
            st.registerOutParameter(2, Types.REF_CURSOR);
            st.execute();

            ResultSet rs = (ResultSet) st.getObject(2);

            while (rs.next()) {
                adquisicion = new Adquisicion();

                adquisicion.setIdPublicacion(rs.getLong("id_publicacion"));
                adquisicion.setJuego(rs.getString("juego"));
                adquisicion.setFechaCompra(rs.getString("fecha_compra"));
                adquisicion.setVendedor(rs.getString("vendedor"));
                adquisicion.setComprador(rs.getString("comprador"));

                adquisiciones.add(adquisicion);
            }

            conn.close();

            return adquisiciones;

        } catch (SQLException e) {
            throw new CustomRuntimeException(e);
        }
    }
}
