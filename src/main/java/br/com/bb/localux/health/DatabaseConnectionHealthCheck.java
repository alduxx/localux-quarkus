package br.com.bb.localux.health;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.net.Socket;

@Readiness
@ApplicationScoped
public class DatabaseConnectionHealthCheck implements HealthCheck {
    
    @ConfigProperty(name="quarkus.datasource.url")
    String datasource_url;
    
    @Override
    public HealthCheckResponse call(){
        HealthCheckResponseBuilder builder = HealthCheckResponse.named("Database connection health check");
        
        try {
            serverListening(datasource_url);
            builder.up();
        } catch (Exception e){
            builder.down().withData("error", e.getMessage());
        }
        
        return builder.build();
    }


    private void serverListening(String datasource_url) throws IOException {
        int posStartHost = datasource_url.indexOf("//") + 2;
        int posStartPort = datasource_url.indexOf(":", posStartHost);

        String host = datasource_url.substring(posStartHost, posStartPort);
        String port = datasource_url.substring(posStartPort+1, datasource_url.indexOf("/", posStartPort));

        Socket s = new Socket(host, Integer.parseInt(port));
        s.close();
    }
}
