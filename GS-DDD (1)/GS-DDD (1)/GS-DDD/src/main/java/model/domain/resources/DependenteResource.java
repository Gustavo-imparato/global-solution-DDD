package model.domain.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import model.domain.entity.Dependente;
import model.domain.service.DependenteService;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/dependente")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DependenteResource implements Resource<Dependente, Long>{
    @Context
    UriInfo uriInfo;

    DependenteService service = new DependenteService();

    @GET
    @Override
    public Response findAll() {
        List<Dependente> all = service.findAll();
        return Response.ok( all ).build();
    }


    @GET
    @Path("/{id}")
    @Override
    public Response findById(@PathParam("id") Long id) {

        Dependente dependente = service.findById( id );

        if (Objects.isNull(dependente)) return Response.status( 404 ).build();

        return Response.ok(dependente).build();
    }

    @POST
    @Override
    public Response persist(Dependente dependente) {
        dependente.setId( null );
        Dependente pet = service.persist(dependente);

        if (Objects.isNull( pet.getId() ))
            return Response.notModified( "Não foi possível persistir: " + dependente).build();

        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        URI uri = uriBuilder.path( String.valueOf( dependente.getId() ) ).build();

        return Response.created( uri ).entity(dependente).build();

    }

    @Override
    public Response update(Long id, Dependente dependente) {
        return null;
    }

    @Override
    public Response delete(Long id) {
        return null;
    }
}
