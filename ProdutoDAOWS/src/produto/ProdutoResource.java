package produto;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import io.dropwizard.jersey.params.*;
import java.util.*;

@Path("/produto")
@Produces(MediaType.APPLICATION_JSON)
public class ProdutoResource {    

    private ProdutoDAO dao;
    
    public ProdutoResource(ProdutoDAO dao) {
        this.dao = dao;
    }
    
    @GET
    public List<Produto> read() {
        return dao.read();
    }
    
    @POST
    public Produto create(Produto p) {
        return this.dao.create(p);
    }
    
    @GET
    @Path("{id}")
    public Produto readOne(@PathParam("id") IntParam id) {
        int idProduto = id.get();
        return null;
    }
    

    @PUT
    @Path("{id}")
    public Produto update(@PathParam("id") IntParam id, Produto p) {
        List<Produto> Produtos = new ArrayList();
        Produtos = read();
        for (Produto Produto: Produtos) {
            if (Produto.getId() == id.get()) {
                Produto.setDescrição(p.getDescrição());
                Produto.setMarca(p.getMarca());
                Produto.setPreço(p.getPreço());
                return Produto;
            }
        }
        return null;
    }
    
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") IntParam id) {
        List<Produto> Produtos = new ArrayList();
        Produtos = read();
        Produto p = null;
        for (Produto Produto: Produtos) {
            if (Produto.getId() == id.get()) {
                p = Produto;
                break;
            }
        }
        if (p != null) { 
            Produtos.remove(p); 
        }
        else {
            throw new WebApplicationException("Produto com id=" + id.get() 
                                              + " não encontrado!", 404);
        }
        return Response.ok().build();
    }
}