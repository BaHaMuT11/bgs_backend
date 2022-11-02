package cl.bahatech.bahagamesbackend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringConfig implements WebMvcConfigurer {

    public static final String CONTROLLER_TAG = "Servicios del WS";

    @Bean
    public Docket api() {

        Docket docket = new Docket(DocumentationType.OAS_30);
        docket.select().apis(RequestHandlerSelectors.basePackage("cl.bahatech.bahagamesbackend.controller"))
                .paths(PathSelectors.any()).build().apiInfo(datosAPI())
                .tags(new Tag(CONTROLLER_TAG, "Desplegar para revisar los endpoints disponibles"));

        return docket;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.
                addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
                .resourceChain(false);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/swagger-ui/")
                .setViewName("forward:" + "/swagger-ui/index.html");
    }

    private ApiInfo datosAPI() {
        ApiInfoBuilder infoApi = new ApiInfoBuilder();

        infoApi.title("API para proyecto BaHaGames Marketplace");
        infoApi.description("Esta API lleva las operaciones backend de este proyecto");
        infoApi.contact(new Contact("Raúl Pardo Zurita", "https://www.bahatech.cl",
                "bahamut@bahatech.cl"));
        infoApi.version("V1");

        return infoApi.build();
    }
}
