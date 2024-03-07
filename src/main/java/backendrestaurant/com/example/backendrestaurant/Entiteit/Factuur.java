package backendrestaurant.com.example.backendrestaurant.Entiteit;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Factuur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String klantNaam;
    private String artikelNaam;
    private int aantal;
    private double totaalBedrag;

    // Constructors, getters, setters, en andere benodigde methoden

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKlantNaam() {
        return klantNaam;
    }

    public void setKlantNaam(String klantNaam) {
        this.klantNaam = klantNaam;
    }

    public String getArtikelNaam() {
        return artikelNaam;
    }

    public void setArtikelNaam(String artikelNaam) {
        this.artikelNaam = artikelNaam;
    }

    public int getAantal() {
        return aantal;
    }

    public void setAantal(int aantal) {
        this.aantal = aantal;
    }

    public double getTotaalBedrag() {
        return totaalBedrag;
    }

    public void setTotaalBedrag(double totaalBedrag) {
        this.totaalBedrag = totaalBedrag;
    }
}
