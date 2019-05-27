
package produto;

import java.util.List;


public interface ProdutoRepository {
    
    public boolean save(Produto produto);

    public boolean update(Produto produto);

    public boolean delete(Produto produto);

    public Produto get(int id);

    public List<Produto> getAll();
}
