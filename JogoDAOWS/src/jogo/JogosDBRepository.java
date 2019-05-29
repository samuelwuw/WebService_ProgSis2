package jogo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JogosDBRepository implements JogoRepository {

    private Connection getConnection() {
        String url = "url aqui";
        String user = "usuario";
        String password = "senha";
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean save(Jogo jogos) {
        try {
            Connection conn = getConnection();

            String sql = "INSERT INTO jogos (id, nometimeA, nometimeB, golstimeA, golstimeB) VALUES (?,?,?,?,?)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, jogos.getId());
            pstm.setString(2, jogos.getNomeTimeA());
            pstm.setString(3, jogos.getNomeTimeB());
            pstm.setInt(4, jogos.getGolsTimeA());
            pstm.setInt(5, jogos.getGolsTimeB());

            pstm.executeUpdate();

            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean update(Jogo jogos) {
        try {
            String sql = "UPDATE produto SET nometimeA=?, nometimeB=?, golstimeA=?, golstimeB=? WHERE id=?";

            Connection conn = getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, jogos.getNomeTimeA());
            pstm.setString(2, jogos.getNomeTimeB());
            pstm.setInt(3, jogos.getGolsTimeA());
            pstm.setInt(4, jogos.getGolsTimeB());
            pstm.setInt(5, jogos.getId());

            int rowsUpdated = pstm.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Um produto existente foi atualizado com sucesso");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Jogo jogos) {
        try {
            Connection conn = getConnection();

            String sql = "DELETE FROM jogos WHERE id=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, jogos.getId());
            pstm.executeUpdate();

            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Jogo get(int id) {
        try {
            Jogo resposta = new Jogo();
            Connection conn = getConnection();

            String sql = "SELECT * FROM jogos WHERE id=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();

            if (!rs.next()) {
                return null;
            }

            resposta.setId(id);
            resposta.setNomeTimeA(rs.getString("nometimea"));
            resposta.setNomeTimeB(rs.getString("nometimeb"));
            resposta.setGolsTimeA(rs.getInt("golstimea"));
            resposta.setGolsTimeB(rs.getInt("golstimeb"));

            conn.close();
            return resposta;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Jogo> getAll() {
                try {
            ArrayList<Jogo> jogos = new ArrayList();
            Jogo resposta = new Jogo();
            Connection conn = getConnection();

            String sql = ("SELECT * FROM jogo");
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                resposta.setId(rs.getInt("id"));
                resposta.setNomeTimeA(rs.getString("nometimeA"));
                resposta.setNomeTimeB(rs.getString("nometimeB"));
                resposta.setGolsTimeA(rs.getInt("golstimeA"));
                resposta.setGolsTimeB(rs.getInt("golstimeB"));
                jogos.add(resposta);
            }

            conn.close();
            return jogos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
