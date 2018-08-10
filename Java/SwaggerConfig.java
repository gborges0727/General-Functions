import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@SpringBootApplication
public class SwaggerConfig extends WebMvcConfigurerAdapter {
	
	@Autowired
	ServletContext servletContext;
	
	@Autowired
	private Environment env;
	
	@Bean
	public Docket api() {
		String host = env.getProperty("localhost:8080");
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
			.paths(PathSelectors.any())
			.build()
			.host(host)
			.pathProvider(new RelativePathProvider(servletContext) {
		        @Override
		        public String getApplicationBasePath() {
		        	return "/";
		        }
		    })
			.apiInfo(apiInfo());
	}
	
	ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("API Name")
            .description("API Descrip")
            .license("")
            .licenseUrl("")
            .termsOfServiceUrl("")
            .version("1.0.0")
            .build();
    }
}

/* Maven dependendies: 
<dependency>
	<groupId>io.springfox</groupId>
	<artifactId>springfox-swagger2</artifactId>
	<version>${swaggerVersion}</version>
</dependency>
<dependency>
	<groupId>io.springfox</groupId>
	<artifactId>springfox-swagger-ui</artifactId>
	<version>${swaggerVersion}</version>
</dependency>

<swaggerVersion>2.6.1</swaggerVersion>
*/