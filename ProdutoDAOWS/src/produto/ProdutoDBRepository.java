package produto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDBRepository implements ProdutoRepository {

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
    public boolean save(Produto produto) {
        try {
            Connection conn = getConnection();

            String sql = "INSERT INTO produto (id, descrição, marca, preço) VALUES (?,?,?,?)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, produto.getId());
            pstm.setString(2, produto.getDescrição());
            pstm.setString(3, produto.getMarca());
            pstm.setDouble(4, produto.getPreço());

            pstm.executeUpdate();

            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean update(Produto produto) {
        try {
            String sql = "UPDATE produto SET descrição=?, marca=?, preço=? WHERE id=?";

            Connection conn = getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, produto.getDescrição());
            pstm.setString(2, produto.getMarca());
            pstm.setDouble(3, produto.getPreço());
            pstm.setInt(4, produto.getId());

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
        public boolean delete(Produto produto) {
        try {
                Connection conn = getConnection();

                String sql = "DELETE FROM produto WHERE id=?";
                PreparedStatement pstm = conn.prepareStatement(sql);
                pstm.setInt(1, produto.getId());
                pstm.executeUpdate();

                conn.close();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        public Produto get(int id) {
        try {
                Produto resposta = new Produto();
                Connection conn = getConnection();

                String sql = "SELECT * FROM produto WHERE id=?";
                PreparedStatement pstm = conn.prepareStatement(sql);
                pstm.setInt(1, id);
                ResultSet rs = pstm.executeQuery();

                if (!rs.next()) {
                    return null;
                }

                resposta.setId(id);
                resposta.setDescrição(rs.getString("descrição"));
                resposta.setMarca(rs.getString("marca"));
                resposta.setPreço(rs.getDouble("preço"));

                conn.close();
                return resposta;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        public List<Produto> getAll() {
                try {
            ArrayList<Produto> produtos = new ArrayList();
            Produto resposta = new Produto();
            Connection conn = getConnection();

            String sql = ("SELECT * FROM produtos");
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                resposta.setId(rs.getInt("id"));
                resposta.setDescrição(rs.getString("descrição"));
                resposta.setMarca(rs.getString("marca"));
                resposta.setPreço(rs.getDouble("preço"));
                produtos.add(resposta);
            }

            conn.close();
            return produtos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        }
    }
