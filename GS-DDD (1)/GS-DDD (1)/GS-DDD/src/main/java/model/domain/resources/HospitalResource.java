package model.domain.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import model.domain.entity.Hospital;
import model.domain.service.HospitalService;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/hospital")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HospitalResource implements Resource<Hospital, Long> {

    @Context
    UriInfo uriInfo;

    HospitalService service = new HospitalService();

    @GET
    @Override
    public Response findAll() {
        List<Hospital> all = service.findAll();
        return Response.ok( all ).build();
    }


    @GET
    @Path("/{id}")
    @Override
    public Response findById(@PathParam("id") Long id) {

        Hospital hospital = service.findById( id );

        if (Objects.isNull(hospital)) return Response.status( 404 ).build();

        return Response.ok(hospital).build();
    }

    @POST
    @Override
    public Response persist(Hospital hospital) {
        hospital.setId( null );
        Hospital h = service.persist(hospital);

        if (Objects.isNull( hospital.getId() ))
            return Response.notModified( "Não foi possível persistir: " + hospital).build();

        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        URI uri = uriBuilder.path( String.valueOf( h.getId() ) ).build();

        return Response.created( uri ).entity( h ).build();

    }

    @Override
    public Response update(Long id, Hospital hospital) {
        return null;
    }

    @Override
    public Response delete(Long id) {
        return null;
    }
}
