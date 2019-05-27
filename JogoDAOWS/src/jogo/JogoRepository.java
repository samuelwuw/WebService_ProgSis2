package jogo;

import java.util.*;

public interface JogoRepository {

    public boolean save(Jogo jogos);

    public boolean update(Jogo jogos);

    public boolean delete(Jogo jogos);

    public Jogo get(int id);

    public List<Jogo> getAll();
}
