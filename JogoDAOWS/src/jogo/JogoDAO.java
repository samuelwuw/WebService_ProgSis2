
package jogo;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class JogoDAO {
    private PreparedStatement stmC;
    private PreparedStatement stmR;
    private PreparedStatement stmU;
    private PreparedStatement stmD;
    
    private Connection conn;
   
    
    public JogoDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost/NomeDoGrupo","root","");
            
            this.stmC = this.conn.prepareStatement("INSERT INTO jogos(nometimeA, nometimeB, golstimeA, golstimeB) VALUES(?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            this.stmR = this.conn.prepareStatement("SELECT * FROM jogos");
            this.stmU = this.conn.prepareStatement("UPDATE jogos SET nometimeA=?, nometimeB=?, golstimeA=?, golstimeB=? WHERE id=?");
            this.stmD = this.conn.prepareStatement("DELETE FROM jogos WHERE id=?");
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
    
    public List<Jogo> read() {
        try {
            ResultSet rs = this.stmR.executeQuery();
            
            List<Jogo> jogos = new ArrayList<>();
            
            while(rs.next()) {
                Jogo j = new Jogo();
                j.setId(rs.getInt("id"));
                j.setNomeTimeA(rs.getString("nometimeA"));
                j.setNomeTimeB(rs.getString("nometimeB"));
                j.setGolsTimeA(rs.getInt("golstimeA"));
                j.setGolsTimeB(rs.getInt("golstimeB"));
                
                jogos.add(j);
            }
            
            return jogos;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
 
    public Jogo create(Jogo novoJogo) {
        try {
            this.stmC.setString(1, novoJogo.getNomeTimeA());
            this.stmC.setString(2, novoJogo.getNomeTimeB());
            this.stmC.setInt(3, novoJogo.getGolsTimeA());
            this.stmC.setInt(4, novoJogo.getGolsTimeB());
            this.stmC.executeUpdate();
            
            ResultSet rs = this.stmC.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            novoJogo.setId(id);
            
            return novoJogo;
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean update(int id, Jogo novoJogo){
        try{
            this.stmU.setString(1, novoJogo.getNomeTimeA());
            this.stmU.setString(2, novoJogo.getNomeTimeB());
            this.stmU.setInt(3, novoJogo.getGolsTimeA());
            this.stmU.setInt(4, novoJogo.getGolsTimeB());
            this.stmU.setInt(5, novoJogo.getId());
            
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
    
    public boolean delete(Jogo j){
        try{
        this.stmD.setInt(1, j.getId());
        this.stmD.executeUpdate();
        
        return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}

