package jogo;

public class Jogo {

    private int id;
    private String nometimeA;
    private String nometimeB;
    private int golstimeA;
    private int golstimeB;

    public Jogo() {
        this.id = 1;
        this.nometimeA = "";
        this.nometimeB = "";
        this.golstimeA = 0;
        this.golstimeB = 0;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setNomeTimeA(String nometimeA) {
        this.nometimeA = nometimeA;
    }

    public String getNomeTimeA() {
        return this.nometimeA;
    }

    public void setNomeTimeB(String nometimeB) {
        this.nometimeB = nometimeB;
    }

    public String getNomeTimeB() {
        return this.nometimeB;
    }

    public void setGolsTimeA(int golstimeA) {
        this.golstimeA = golstimeA;
    }

    public int getGolsTimeA() {
        return golstimeA;
    }

    public void setGolsTimeB(int golstimeB) {
        this.golstimeB = golstimeB;
    }

    public int getGolsTimeB() {
        return golstimeB;
    }

}
