package assignment.pingpong.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("프로그라피 9기 탁구 Rest API")
                        .description("프로그라피 9기 탁구과제를 위한 API입니다.")
                        .version("1.0.0"));
    }
}
