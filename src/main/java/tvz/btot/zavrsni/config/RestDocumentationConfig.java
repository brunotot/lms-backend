package tvz.btot.zavrsni.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collection;
import java.util.Collections;

@Configuration
@EnableSwagger2
public class RestDocumentationConfig {
    private static final String TITLE = "TVZ LMS Rest API";
    private static final String DESCRIPTION = "Rest API for TVZ Final paper - consumed by Angular frontend app";
    private static final String VERSION = "1";
    private static final String TERMS_OF_SERVICE_URL = "";
    private static final String AUTHOR_NAME = "Bruno Tot";
    private static final String AUTHOR_URL = "https://www.linkedin.com/in/btot/";
    private static final String AUTHOR_EMAIL = "brunotot10000@gmail.com";
    private static final String LICENSE = "";
    private static final String LICENSE_URL = "";
    private static final Collection<VendorExtension> VENDOR_EXTENSIONS = Collections.emptyList();

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                TITLE,
                DESCRIPTION,
                VERSION,
                TERMS_OF_SERVICE_URL,
                new Contact(
                    AUTHOR_NAME,
                    AUTHOR_URL,
                    AUTHOR_EMAIL
                ),
                LICENSE,
                LICENSE_URL,
                VENDOR_EXTENSIONS);
    }
}