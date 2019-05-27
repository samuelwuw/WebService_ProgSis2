package país;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

public class PaísDBRepository implements PaísRepository {

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
    public boolean save(País país) {
        try {
            Connection conn = getConnection();

            String sql = "INSERT INTO país (id, nome, continente, população) VALUES (?,?,?,?)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, país.getId());
            pstm.setString(2, país.getNome());
            pstm.setString(3, país.getContinente());
            pstm.setInt(4, país.getPopulação());

            pstm.executeUpdate();

            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean update(País país) {
        try {
            String sql = "UPDATE produto SET nome=?, continente=?, população=? WHERE id=?";

            Connection conn = getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, país.getNome());
            pstm.setString(2, país.getContinente());
            pstm.setInt(3, país.getPopulação());
            pstm.setInt(4, país.getId());

            int rowsUpdated = pstm.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Um país existente foi atualizado com sucesso");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(País país) {
        try {
            Connection conn = getConnection();

            String sql = "DELETE FROM país WHERE id=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, país.getId());
            pstm.executeUpdate();

            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public País get(int id) {
        try {
            País resposta = new País();
            Connection conn = getConnection();

            String sql = "SELECT * FROM país WHERE id=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();

            if (!rs.next()) {
                return null;
            }

            resposta.setId(id);
            resposta.setNome(rs.getString("nome"));
            resposta.setContinente(rs.getString("continente"));
            resposta.setPopulação(rs.getInt("população"));

            conn.close();
            return resposta;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<País> getAll() {
        try {
            ArrayList<País> paises = new ArrayList();
            País resposta = new País();
            Connection conn = getConnection();

            String sql = ("SELECT * FROM país");
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                resposta.setId(rs.getInt("id"));
                resposta.setNome(rs.getString("nome"));
                resposta.setContinente(rs.getString("continente"));
                resposta.setPopulação(rs.getInt("população"));
                paises.add(resposta);
            }

            conn.close();
            return paises;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
