package cl.bahatech.bahagamesbackend;

import cl.bahatech.bahagamesbackend.util.Log4jListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.naming.NamingException;
import javax.sql.DataSource;

@Configuration
public class ServiceConfig {
    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String usuario;

    @Value("${spring.datasource.password}")
    private String pass;

    @Bean
    public Log4jListener executeLog4jListener() {
        return new Log4jListener();
    }

    @Bean("baha_game_store_ws")
    public JdbcTemplate jdbcTemplate() throws NamingException {

        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();

        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url(url);
        dataSourceBuilder.username(usuario);
        dataSourceBuilder.password(pass);

        DataSource dataSource = dataSourceBuilder.build();

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute("select 1");

        return jdbcTemplate;
    }

}
