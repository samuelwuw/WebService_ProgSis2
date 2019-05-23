
package país;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class PaísDAO {
    private PreparedStatement stmC;
    private PreparedStatement stmR;
    private PreparedStatement stmU;
    private PreparedStatement stmD;
    
    private Connection conn;
   
    
    public PaísDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost/NomeDoGrupo","root","");
            
            this.stmC = this.conn.prepareStatement("INSERT INTO país(nome, continente, população) VALUES(?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            this.stmR = this.conn.prepareStatement("SELECT * FROM país");
            this.stmU = this.conn.prepareStatement("UPDATE país SET nome=?, continente=?, população=?, WHERE id=?");
            this.stmD = this.conn.prepareStatement("DELETE FROM país WHERE id=?");
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
    
    public List<País> read() {
        try {
            ResultSet rs = this.stmR.executeQuery();
            
            List<País> Países = new ArrayList<>();
            
            while(rs.next()) {
                País p = new País();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setContinente(rs.getString("continente"));
                p.setPopulação(rs.getInt("população"));
                
                Países.add(p);
            }
            
            return Países;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
 
    public País create(País novoPaís) {
        try {
            this.stmC.setString(1, novoPaís.getNome());
            this.stmC.setString(2, novoPaís.getContinente());
            this.stmC.setInt(3, novoPaís.getPopulação());
            this.stmC.executeUpdate();
            
            ResultSet rs = this.stmC.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            novoPaís.setId(id);
            
            return novoPaís;
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean update(int id, País novoPaís){
        try{
            this.stmU.setString(1, novoPaís.getNome());
            this.stmU.setString(2, novoPaís.getContinente());
            this.stmU.setInt(3, novoPaís.getPopulação());
            this.stmU.setInt(5, novoPaís.getId());
            
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
    
    public boolean delete(País p){
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