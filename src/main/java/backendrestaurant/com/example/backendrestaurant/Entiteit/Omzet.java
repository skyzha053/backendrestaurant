package backendrestaurant.com.example.backendrestaurant.Entiteit;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "omzet")
public class Omzet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "omzet_bedrag")
    private double omzetBedrag;

    @Column(name = "datum")
    private LocalDateTime datum; // Attribuut voor de datum

    // Constructors
    public Omzet() {
    }

    public Omzet(double omzetBedrag, LocalDateTime datum) {
        this.omzetBedrag = omzetBedrag;
        this.datum = datum;
    }

    // Getters en setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getOmzetBedrag() {
        return omzetBedrag;
    }

    public void setOmzetBedrag(double omzetBedrag) {
        this.omzetBedrag = omzetBedrag;
    }

    public LocalDateTime getDatum() {
        return datum;
    }

    public void setDatum(LocalDateTime datum) {
        this.datum = datum;
    }

    // toString method
    @Override
    public String toString() {
        return "Omzet{" +
                "id=" + id +
                ", omzetBedrag=" + omzetBedrag +
                ", datum=" + datum +
                '}';
    }
}
