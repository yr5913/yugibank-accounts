package com.yugibank.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
/*@ComponentScans({ @ComponentScan("com.yugibank.accounts.controller") })
@EnableJpaRepositories("com.yugibank.accounts.repository")
@EntityScan("com.yugibank.accounts.model")*/
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
        info = @Info(
                title = "Accounts microservice REST API ",
                description = "YugiBank Accounts microservice",
                version = "v1",
                contact = @Contact(
                        name = "Yugesh Reganti",
                        email = "regantiyugesh@gmail.com",
                        url = "https://yugeshreganti.com/"),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://yugeshreganti.com/"
                ))
        , externalDocs = @ExternalDocumentation(
        description = "YugiBank Accounts microservice REST API Documentation",
        url = "https://yugeshreganti.com/swagger-ui.html")
)
public class AccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }

}
