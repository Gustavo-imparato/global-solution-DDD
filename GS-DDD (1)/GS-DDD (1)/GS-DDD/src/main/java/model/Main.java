package model;


import model.infra.ConnectionFactory;
import model.infra.configuration.cors.CORSFilter;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

public class Main {

    private static final String BASE_URI = "http://localhost/";

    public static HttpServer startServer() {
        final ResourceConfig rc = new ResourceConfig()
                .register(CORSFilter.class)
                .register(ConnectionFactory.build())
                .packages("model.domain.resources");
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) {
        var server = startServer();
        System.out.println(String.format(
                "App Vitalidade  🤓👍  started with endpoints available " +
                        "as %s%nHit Ctrl-C to stop it....", BASE_URI));
        try {
            System.in.read();
            server.stop();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}