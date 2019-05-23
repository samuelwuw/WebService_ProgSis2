
package produto;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ProdutoDAO {
    private PreparedStatement stmC;
    private PreparedStatement stmR;
    private PreparedStatement stmU;
    private PreparedStatement stmD;
    
    private Connection conn;
   
    
    public ProdutoDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost/NomeDoGrupo","root","");
            
            this.stmC = this.conn.prepareStatement("INSERT INTO produto(descrição, marca, preço) VALUES(?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            this.stmR = this.conn.prepareStatement("SELECT * FROM produto");
            this.stmU = this.conn.prepareStatement("UPDATE produto SET descrição=?, marca=?, preço=?, WHERE id=?");
            this.stmD = this.conn.prepareStatement("DELETE FROM produto WHERE id=?");
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void close(){
        try{
            this.conn.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Produto> read() {
        try {
            ResultSet rs = this.stmR.executeQuery();
            
            List<Produto> Produtos = new ArrayList<>();
            
            while(rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setDescrição(rs.getString("descrição"));
                p.setMarca(rs.getString("marca"));
                p.setPreço(rs.getDouble("preço"));
                
                Produtos.add(p);
            }
            
            return Produtos;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
 
    public Produto create(Produto novoProduto) {
        try {
            this.stmC.setString(1, novoProduto.getDescrição());
            this.stmC.setString(2, novoProduto.getMarca());
            this.stmC.setDouble(3, novoProduto.getPreço());
            this.stmC.executeUpdate();
            
            ResultSet rs = this.stmC.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            novoProduto.setId(id);
            
            return novoProduto;
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean update(int id, Produto novoProduto){
        try{
            this.stmU.setString(1, novoProduto.getDescrição());
            this.stmU.setString(2, novoProduto.getMarca());
            this.stmU.setDouble(3, novoProduto.getPreço());
            this.stmU.setInt(5, novoProduto.getId());
            
            int rowsUpdated = this.stmU.executeUpdate();
            
            if (rowsUpdated>0){
                System.out.println("Update feito com sucesso");
            }
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean delete(Produto p){
        try{
        this.stmD.setInt(1, p.getId());
        this.stmD.executeUpdate();
        
        return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}