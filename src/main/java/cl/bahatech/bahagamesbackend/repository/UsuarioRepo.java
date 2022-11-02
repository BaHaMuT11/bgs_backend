package cl.bahatech.bahagamesbackend.repository;

import cl.bahatech.bahagamesbackend.model.Usuario;
import cl.bahatech.bahagamesbackend.model.request.AgregarUsuarioRequest;
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
            //conn.setAutoCommit(false); Debe quedar asī para postgre en lectura de datos

            CallableStatement st = conn.prepareCall("CALL " + Constante.SP_AGREGAR_USUARIO + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            //Call sin curly braces para postgre

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
                conn.setAutoCommit(false); //Debe quedar asī para postgre

                CallableStatement st = conn.prepareCall("CALL " + Constante.SP_OBTENER_USUARIO_USERNAME + "(?,?)");
                //Call sin curly braces para postgre
                st.setString(1, username);
                st.registerOutParameter(2, Types.REF_CURSOR);

                st.execute();

                ResultSet rs = (ResultSet) st.getObject(2);

                while (rs.next()) {
                    usuario = new Usuario();

                    //usuario.setId(rs.getLong("id"));
                    //usuario.setNombre(rs.getString("nombre"));
                    usuario.setPass(rs.getString("pass"));
                    usuario.setUsuario(rs.getString("usuario"));
                    //usuario.setFechaCreacion(rs.getString("fecha_creacion"));
                }

                conn.close();
                System.out.println(usuario);

                return usuario;

            } catch (SQLException e) {
                throw new CustomRuntimeException(e);
            }

    }
}
