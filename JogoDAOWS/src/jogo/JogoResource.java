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

    @GET
    public List<Jogo> readAll() {
        return dao.read();
    }

    @PUT
    @Path("{id}")
    public Jogo update(@PathParam("id") IntParam id, Jogo j) {
        for (Jogo jogo : dao.read()) {
            if (jogo.getId() == id.get()) {
                jogo.setNomeTimeA(j.getNomeTimeA());
                jogo.setNomeTimeB(j.getNomeTimeB());
                jogo.setGolsTimeA(j.getGolsTimeA());
                jogo.setGolsTimeB(j.getGolsTimeB());
                jogo.setId(j.getId());
                dao.update(id.get(), jogo);
                return jogo;
            }
        }
        return null;
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") IntParam id) {
                Jogo j = null;
        for (Jogo jogo : dao.read()) {
            if (jogo.getId() == id.get()) {
                j = jogo;
                break;
            }
        }
        if (j != null) {
            dao.delete(j);
        } else {
            throw new WebApplicationException("jogo com id=" + id.get()
                    + " não encontrado!", 404);
        }
        return Response.ok().build();
    }
}
