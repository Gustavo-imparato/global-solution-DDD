package model.domain.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("/")
public class IndexResource {

    @GET
    public Response index() {
        return Response.ok("Seja Bem-vindo a Vitalidade").build();
    }

    @GET
    @Path("/{nome}")
    public Response index(@PathParam("nome") String nome) {
        return Response.ok("Bom dia " + nome +"!\n Seja Bem-vindo a Vitalidade").build();
    }

}
