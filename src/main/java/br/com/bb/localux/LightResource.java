package br.com.bb.localux;

import br.com.bb.localux.models.Light;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.concurrent.CompletionStage;


import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

@Path("/lights")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LightResource {

    @Inject
    io.vertx.axle.mysqlclient.MySQLPool client;

    @Inject
    @ConfigProperty(name = "localux-quarkus.schema.create", defaultValue = "true")
    boolean schemaCreate;


    @PostConstruct
    void config() {
        if (schemaCreate) {
            //initdb();
        }
    }

    private void initdb() {
        client.query("DROP TABLE IF EXISTS lights")
                .thenCompose(r -> client.query("CREATE TABLE lights (id INT NOT NULL, name VARCHAR(255) NOT NULL, status TINYINT NOT NULL, PRIMARY KEY (id))"))
                .thenCompose(r -> client.query("INSERT INTO lights (id, name, status) VALUES (15, 'Antesala', 1)"))
                .thenCompose(r -> client.query("INSERT INTO lights (id, name, status) VALUES (13, 'Varanda', 1)"))
                .thenCompose(r -> client.query("INSERT INTO lights (id, name, status) VALUES (14, 'Central', 0)"))
                .thenCompose(r -> client.query("INSERT INTO lights (id, name, status) VALUES (12, 'Sala Lateral', 0)"))
                .toCompletableFuture()
                .join();
    }

    @ConfigProperty(name = "quarkus.datasource.url")
    String message;

    @GET
    public CompletionStage<Response> get() {
        System.out.println("#### Aldux ");
        System.out.println(message);

        return Light.findAll(client)
                .thenApply(Response::ok)
                .thenApply(Response.ResponseBuilder::build);
    }

    // 4265872.1 saldo cons
    // 3963992.1 lista matri
    // 4.9031
    // centralizador 2.59


    @GET
    @Path("{id}")
    public CompletionStage<Response> getSingle(@PathParam Integer id) {
        return Light.findById(client, id)
                .thenApply(light -> light != null ? Response.ok(light) : Response.status(Response.Status.NOT_FOUND))
                .thenApply(Response.ResponseBuilder::build);
    }


    @PUT
    public CompletionStage<Response> toggleLightStatus(@QueryParam Integer id) {
        Light.toggleLightStatus(client, id);
        return getSingle(id);
    }

}
