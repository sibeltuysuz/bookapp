package firma.bookapp;

public class Kitap {

    private String ad,kategori,sayfa_sayisi,deger;
    private int id;

    public Kitap(String name, String kategori, int id, String sayfa_sayisi,String deger) {
        this.ad = name;
        this.kategori = kategori;
        this.id = id;
        this.sayfa_sayisi = sayfa_sayisi;
        this.deger = deger;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getDeger() {
        return deger;
    }

    public void setDeger(String deger) {
        this.deger = deger;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getSayfa_sayisi() {
        return sayfa_sayisi;
    }

    public void setSayfa_sayisi(String sayfa_sayisi) {
        this.sayfa_sayisi = sayfa_sayisi;
    }
}
