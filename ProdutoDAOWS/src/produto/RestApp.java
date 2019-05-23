package produto;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class RestApp extends Application<Configuration> {
    
    public static void main(String[] args) throws Exception {
        new RestApp().run(new String[] { "server" });
    }
    
    @Override
    public void initialize(final Bootstrap<Configuration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/html", "/", "index.html"));
    }

    @Override
    public void run(Configuration configuration, Environment environment) {
        ConexaoJavaDB conexao = new ConexaoJavaDB("root", "", "jdbc:mysql://localhost", 3306, "NomeDoGrupo");
        ProdutoDAO ProdutoDAO = new ProdutoDAO(conexao);
        environment.jersey().register(new ProdutoResource(ProdutoDAO));
        environment.jersey().setUrlPattern("/api/*");
    }
}