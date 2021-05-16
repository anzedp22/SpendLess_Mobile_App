package si.uni_lj.fe.tnuv.spendless;

public class Nakup {
    private String ponudnik;
    private String datum;
    private double cena;
    private int ID_nakupa;

    public Nakup (String ponudnik, String datum, double cena, int ID_nakupa){

        this.ponudnik = ponudnik;
        this.datum = datum;
        this.cena = cena;
        this.ID_nakupa = ID_nakupa;
    }

    public String getPonudnik() {
        return ponudnik;
    }

    public void setPonudnik(String ponudnik) {
        this.ponudnik = ponudnik;
    }

    public String getDatum () {
        return datum;
    }

    public void  setDatum(String datum) {
        this.datum = datum;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public int getID_nakupa() {
        return ID_nakupa;
    }

    public void setID_nakupa(int ID_nakupa) {
        this.ID_nakupa = ID_nakupa;
    }


    public String toString() {
        return String.valueOf(ponudnik) + String.valueOf(datum) + String.valueOf(cena) + String.valueOf(ID_nakupa);
    }

}
