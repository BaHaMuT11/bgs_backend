package cl.bahatech.bahagamesbackend.repository;

import cl.bahatech.bahagamesbackend.model.Usuario;
import cl.bahatech.bahagamesbackend.model.request.AgregarUsuarioRequest;
import cl.bahatech.bahagamesbackend.model.request.ModificarCredencialesRequest;
import cl.bahatech.bahagamesbackend.model.request.ModificarUsuarioRequest;
import cl.bahatech.bahagamesbackend.util.Constante;
import cl.bahatech.bahagamesbackend.util.CustomRuntimeException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.sql.*;

@Repository
public class UsuarioRepo {

    @Resource(name = "baha_game_store_ws")
    private JdbcTemplate jdbcTemplate;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public boolean agregarUsuario(AgregarUsuarioRequest usuario) {

        try {
            Connection conn = jdbcTemplate.getDataSource().getConnection();

            CallableStatement st = conn.prepareCall("CALL " + Constante.SP_AGREGAR_USUARIO + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            st.setString(1, usuario.getNombre());
            st.setString(2, usuario.getUsuario());
            st.setString(3, encoder.encode(usuario.getPass()));
            st.setString(4, usuario.getFechaNacimiento());
            st.setInt(5, usuario.getEdad());
            st.setString(6, usuario.getRut());
            st.setString(7, usuario.getFono());
            st.setString(8, usuario.getCalle());
            st.setString(9, usuario.getNumero());
            st.setString(10, usuario.getCasa());
            st.setString(11, usuario.getRegion());
            st.setString(12, usuario.getComuna());
            st.setString(13, usuario.getCorreo());
            st.setString(14, usuario.getEstado());

            st.execute();

            conn.close();

            return true;

        } catch (SQLException e) {
            throw new CustomRuntimeException(e);
        }
    }

    public Usuario obtenerUsuarioSecurityPorUsername(String username) {

            Usuario usuario = null;

            try {
                Connection conn = jdbcTemplate.getDataSource().getConnection();
                conn.setAutoCommit(false);

                CallableStatement st = conn.prepareCall("CALL " + Constante.SP_OBTENER_USUARIO_USERNAME + "(?,?)");

                st.setString(1, username);
                st.registerOutParameter(2, Types.REF_CURSOR);

                st.execute();

                ResultSet rs = (ResultSet) st.getObject(2);

                while (rs.next()) {
                    usuario = new Usuario();

                    usuario.setPass(rs.getString("pass"));
                    usuario.setUsuario(rs.getString("usuario"));

                }

                conn.close();

                return usuario;

            } catch (SQLException e) {
                throw new CustomRuntimeException(e);
            }

    }

    public Usuario buscarUsuarioPorUsername(String username) {

        Usuario usuario = null;

        try {
            Connection conn = jdbcTemplate.getDataSource().getConnection();
            conn.setAutoCommit(false);

            CallableStatement st = conn.prepareCall("CALL " + Constante.SP_BUSCAR_USUARIO_USERNAME + "(?,?)");

            st.setString(1, username);
            st.registerOutParameter(2, Types.REF_CURSOR);

            st.execute();

            ResultSet rs = (ResultSet) st.getObject(2);

            while (rs.next()) {
                usuario = new Usuario();

                usuario.setId(Long.parseLong(rs.getString("id")));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setFechaNacimiento(rs.getString("fecha_nacimiento"));
                usuario.setEdad(Integer.parseInt(rs.getString("edad")));
                usuario.setRut(rs.getString("rut"));
                usuario.setFono(rs.getString("fono"));
                usuario.setCalle(rs.getString("calle"));
                usuario.setNumero(rs.getString("numero"));
                usuario.setCasa(rs.getString("casa"));
                usuario.setRegion(rs.getString("region"));
                usuario.setComuna(rs.getString("comuna"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setEstado(rs.getString("estado"));
                usuario.setFechaCreacion(rs.getString("fecha_creacion"));
                usuario.setImagen(rs.getString("imagen"));

            }

            conn.close();

            return usuario;

        } catch (SQLException e) {
            throw new CustomRuntimeException(e);
        }

    }

    public boolean modificarUsuario(ModificarUsuarioRequest usu) {

        try {
            Connection conn = jdbcTemplate.getDataSource().getConnection();

            CallableStatement st = conn.prepareCall("CALL " + Constante.SP_MODIFICAR_USUARIO + "(?,?,?,?,?,?,?,?,?,?,?,?)");

            st.setString(1,  usu.getLogin());
            st.setString(2,  usu.getImagen());
            st.setString(3,  usu.getNombre());
            st.setString(4,  usu.getFechaNacimiento());
            st.setString(5,  usu.getRut());
            st.setString(6,  usu.getFono());
            st.setString(7,  usu.getCalle());
            st.setString(8,  usu.getNumero());
            st.setString(9,  usu.getCasa());
            st.setString(10, usu.getRegion());
            st.setString(11, usu.getComuna());
            st.setInt(12, usu.getEdad());

            st.execute();
            conn.close();

            return true;

        } catch (SQLException e) {
            throw new CustomRuntimeException(e);
        }
    }

    public boolean modificarCredenciales(ModificarCredencialesRequest creds) {

        try {
            Connection conn = jdbcTemplate.getDataSource().getConnection();

            CallableStatement st = conn.prepareCall("CALL " + Constante.SP_MODIFICAR_CREDENCIALES + "(?,?,?)");


            st.setString(1,  creds.getLogin());
            st.setString(2,  creds.getCorreo());
            st.setString(3,  creds.getPass().equals("") ? "" : encoder.encode(creds.getPass()));
            
            st.execute();
            conn.close();

            return true;

        } catch (SQLException e) {
            throw new CustomRuntimeException(e);
        }
    }
}
