package país;

import java.util.List;

public interface PaísRepository {

    public boolean save(País país);

    public boolean update(País país);

    public boolean delete(País país);

    public País get(int id);

    public List<País> getAll();
}
