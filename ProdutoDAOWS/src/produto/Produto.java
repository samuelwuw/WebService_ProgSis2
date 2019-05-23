package produto;

public class Produto {

    private int id;
    private String descrição;
    private String marca;
    private double preço;

    public Produto() {
        this.id = 1;
        this.descrição = "";
        this.marca = "";
        this.preço = 0;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setDescrição(String descrição) {
        this.descrição = descrição;
    }

    public String getDescrição() {
        return this.descrição;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getMarca() {
        return this.marca;
    }

    public void setPreço(double preço) {
        this.preço = preço;
    }

    public double getPreço() {
        return preço;
    }

}
