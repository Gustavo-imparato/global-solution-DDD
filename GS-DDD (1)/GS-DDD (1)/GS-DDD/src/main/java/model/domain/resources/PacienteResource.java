package model.domain.resources;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import model.domain.entity.Paciente;
import model.domain.service.PacienteService;

import java.net.URI;
import java.util.List;
import java.util.Objects;


@Path("/paciente")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PacienteResource implements Resource<Paciente, Long> {

    @Context
    UriInfo uriInfo;

    PacienteService service = new PacienteService();

    @GET
    @Override
    public Response findAll() {
        List<Paciente> all = service.findAll();
        return Response.ok( all ).build();
    }


    @GET
    @Path("/{id}")
    @Override
    public Response findById(@PathParam("id") Long id) {

        Paciente paciente = service.findById( id );

        if (Objects.isNull(paciente)) return Response.status( 404 ).build();

        return Response.ok(paciente).build();
    }

    @POST
    @Override
    public Response persist(Paciente paciente) {
        paciente.setId( null );
        Paciente c = service.persist(paciente);

        if (Objects.isNull( paciente.getId() ))
            return Response.notModified( "Não foi possível persistir: " + paciente).build();

        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        URI uri = uriBuilder.path( String.valueOf( c.getId() ) ).build();

        return Response.created( uri ).entity( c ).build();

    }

    @Override
    public Response update(Long id, Paciente paciente) {
        return null;
    }

    @Override
    public Response delete(Long id) {
        return null;
    }

}
