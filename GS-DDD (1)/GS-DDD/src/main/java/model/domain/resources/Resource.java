package model.domain.resources;

import jakarta.ws.rs.core.Response;

public interface Resource<T, U> {

    public Response findAll();

    public Response findById(U id);

    public Response persist(T t);
    public Response update(U id, T t);

    public Response delete(U id);

}
