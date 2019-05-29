package jogo;

public class Jogo {

    private int id;
    private String nomeTimeA;
    private String nomeTimeB;
    private int golsTimeA;
    private int golsTimeB;

    public Jogo() {
        this.id = 1;
        this.nomeTimeA = "";
        this.nomeTimeB = "";
        this.golsTimeA = 0;
        this.golsTimeB = 0;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setNomeTimeA(String nomeTimeA) {
        this.nomeTimeA = nomeTimeA;
    }

    public String getNomeTimeA() {
        return this.nomeTimeA;
    }

    public void setNomeTimeB(String nomeTimeB) {
        this.nomeTimeB = nomeTimeB;
    }

    public String getNomeTimeB() {
        return this.nomeTimeB;
    }

    public void setGolsTimeA(int golsTimeA) {
        this.golsTimeA = golsTimeA;
    }

    public int getGolsTimeA() {
        return golsTimeA;
    }

    public void setGolsTimeB(int golsTimeB) {
        this.golsTimeB = golsTimeB;
    }

    public int getGolsTimeB() {
        return golsTimeB;
    }

}
