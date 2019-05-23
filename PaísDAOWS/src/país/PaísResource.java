package país;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import io.dropwizard.jersey.params.*;
import java.util.*;

@Path("/país")
@Produces(MediaType.APPLICATION_JSON)
public class PaísResource {    

    private PaísDAO dao;
    
    public PaísResource(PaísDAO dao) {
        this.dao = dao;
    }
    
    @GET
    public List<País> read() {
        return dao.read();
    }
    
    @POST
    public País create(País p) {
        return this.dao.create(p);
    }
    
    @GET
    @Path("{id}")
    public País readOne(@PathParam("id") IntParam id) {
        int idPaís = id.get();
        return null;
    }
    

    @PUT
    @Path("{id}")
    public País update(@PathParam("id") IntParam id, País p) {
        List<País> Países = new ArrayList();
        Países = read();
        for (País País: Países) {
            if (País.getId() == id.get()) {
                País.setNome(p.getNome());
                País.setContinente(p.getContinente());
                País.setPopulação(p.getPopulação());
                return País;
            }
        }
        return null;
    }
    
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") IntParam id) {
        List<País> Países = new ArrayList();
        Paíss = read();
        País p = null;
        for (País País: Países) {
            if (País.getId() == id.get()) {
                p = País;
                break;
            }
        }
        if (p != null) { 
            Países.remove(p); 
        }
        else {
            throw new WebApplicationException("País com id=" + id.get() 
                                              + " não encontrado!", 404);
        }
        return Response.ok().build();
    }
}