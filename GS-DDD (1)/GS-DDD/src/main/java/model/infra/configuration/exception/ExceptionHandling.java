package model.infra.configuration.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ExceptionHandling implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        String errorMessage = "Erro na aplicação: " + exception.getMessage();
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(errorMessage)
                .type("application/json")
                .build();
    }
}