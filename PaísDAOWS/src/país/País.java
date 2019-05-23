package país;

public class País {

    private int id;
    private String nome;
    private String continente;
    private int população;

    public País() {
        this.id = 1;
        this.nome = "";
        this.continente = "";
        this.população = 0;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public void setContinente(String continente) {
        this.continente = continente;
    }

    public String getContinente() {
        return this.continente;
    }

    public void setPopulação(int população) {
        this.população = população;
    }

    public int getPopulação() {
        return população;
    }

}
