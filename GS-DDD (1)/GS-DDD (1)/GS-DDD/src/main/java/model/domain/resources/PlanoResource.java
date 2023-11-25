package model.domain.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import model.domain.entity.Dependente;
import model.domain.entity.Plano;
import model.domain.service.PlanoService;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/plano")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlanoResource implements Resource<Plano, Long>{
    @Context
    UriInfo uriInfo;

    PlanoService service = new PlanoService();

    @GET
    @Override
    public Response findAll() {
        List<Plano> all = service.findAll();
        return Response.ok( all ).build();
    }


    @GET
    @Path("/{id}")
    @Override
    public Response findById(@PathParam("id") Long id) {

        Plano plano = service.findById( id );

        if (Objects.isNull(plano)) return Response.status( 404 ).build();

        return Response.ok(plano).build();
    }

    @POST
    @Override
    public Response persist(Plano plano) {
        plano.setId( null );
        Plano p = service.persist(plano);

        if (Objects.isNull( p.getId() ))
            return Response.notModified( "Não foi possível persistir: " + plano).build();

        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        URI uri = uriBuilder.path( String.valueOf( plano.getId() ) ).build();

        return Response.created( uri ).entity(plano).build();

    }

    @Override
    public Response update(Long id, Plano plano) {
        return null;
    }

    @Override
    public Response delete(Long id) {
        return null;
    }
}
