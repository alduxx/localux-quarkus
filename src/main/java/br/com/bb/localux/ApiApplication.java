package br.com.bb.localux;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.core.Application;

@OpenAPIDefinition(
        info = @Info(
                title="Lights API",
                version = "1.0.1",
                contact = @Contact(
                        name = "Aldo Monteiro",
                        url = "https://humanograma.intranet.bb.com.br/F0411579",
                        email = "aldomonteiro@bb.com.br"),
                license = @License(
                        name = "All rights reserved",
                        url = "http://www.bb.com.br"))
)
public class ApiApplication extends Application {
    // Empty class to provide details to OpenAPI
}
