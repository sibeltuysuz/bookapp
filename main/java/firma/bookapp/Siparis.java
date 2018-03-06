package firma.bookapp;

public class Siparis {

    private String kullaniciad,adresad,adres;
    private int id;

    public Siparis(String kullaniciad, String adresad, int id, String adres) {
        this.kullaniciad = kullaniciad;
        this.adresad = adresad;
        this.id = id;
        this.adres = adres;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getKullaniciad() {
        return kullaniciad;
    }

    public void setKullaniciad(String kullaniciad) {
        this.kullaniciad = kullaniciad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdresad() {
        return adresad;
    }

    public void setAdresad(String adresad) {
        this.adresad = adresad;
    }
}
