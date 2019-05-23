package país;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConexaoJavaDB {
    private Connection conn;
    private String usuario;
    private String senha;
    private String url;
    
    public ConexaoJavaDB(String usuario, String senha, String host, int porta, String banco) {
        this.usuario = usuario;
        this.senha = senha;
        this.url = host + ":" + porta + "/" + banco;
    }

    /**
     * @return the conn
     */
    public Connection getConexao() {
        if (conn != null)
            return conn;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, usuario, senha);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
    
    public void close() {
        if (conn != null) {
            try {
                conn.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
            conn = null;
        }
    }
}