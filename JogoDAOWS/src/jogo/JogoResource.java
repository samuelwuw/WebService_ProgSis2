package jogo;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import io.dropwizard.jersey.params.*;
import java.util.*;

@Path("/jogos")
@Produces(MediaType.APPLICATION_JSON)
public class JogoResource {    

    private JogoDAO dao;
    
    public JogoResource(JogoDAO dao) {
        this.dao = dao;
    }
    
    @GET
    public List<Jogo> read() {
        return dao.read();
    }
    
    @POST
    public Jogo create(Jogo j) {
        return this.dao.create(j);
    }
    
    @GET
    @Path("{id}")
    public Jogo readOne(@PathParam("id") IntParam id) {
        int idJogo = id.get();
        return null;
    }
    

    @PUT
    @Path("{id}")
    public Jogo update(@PathParam("id") IntParam id, Jogo j) {
        List<Jogo> Jogos = new ArrayList();
        Jogos = read();
        for (Jogo Jogo: Jogos) {
            if (Jogo.getId() == id.get()) {
                Jogo.setNomeTimeA(j.getNomeTimeA());
                Jogo.setNomeTimeB(j.getNomeTimeB());
                Jogo.setGolsTimeA(j.getGolsTimeA());
                Jogo.setGolsTimeB(j.getGolsTimeB());
                return Jogo;
            }
        }
        return null;
    }
    
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") IntParam id) {
        List<Jogo> Jogos = new ArrayList();
        Jogos = read();
        Jogo p = null;
        for (Jogo Jogo: Jogos) {
            if (Jogo.getId() == id.get()) {
                p = Jogo;
                break;
            }
        }
        if (p != null) { 
            Jogos.remove(p); 
        }
        else {
            throw new WebApplicationException("Jogo com id=" + id.get() 
                                              + " não encontrado!", 404);
        }
        return Response.ok().build();
    }
}