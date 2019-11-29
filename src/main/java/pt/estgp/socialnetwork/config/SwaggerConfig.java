package pt.estgp.socialnetwork.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pt.estgp.socialnetwork.security.UserPrincipal;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

// http://localhost:8080/swagger-ui.html
// https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api
// https://dzone.com/articles/spring-boot-2-restful-api-documentation-with-swagg
// https://springfox.github.io/springfox/docs/snapshot/
// https://stackoverflow.com/questions/50545286/spring-boot-swagger-ui-set-jwt-token

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    //private static final String DEFAULT_INCLUDE_PATTERN = "(/api/.*|/auth/refresh)";
    private static final String DEFAULT_INCLUDE_PATTERN = "(?!/api/user/(checkUsernameAvailability|checkEmailAvailability))(/api/.*|/auth/refresh)";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                    .apis(RequestHandlerSelectors.basePackage("pt.estgp.socialnetwork.controller"))
                    .paths(PathSelectors.any())
                .build()
                .securitySchemes(List.of(apiKey()))
                .securityContexts(List.of(securityContext()))
                .ignoredParameterTypes(UserPrincipal.class)
                .apiInfo(apiInfo());
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex(DEFAULT_INCLUDE_PATTERN))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[] {
                new AuthorizationScope("global", "accessEverything")
        };
        return List.of(new SecurityReference("JWT", authorizationScopes));
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Social Network REST API")
                .description("Social Network Management REST API")
                .version("v1")
                .build();
    }
}
