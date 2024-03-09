package backendrestaurant.com.example.backendrestaurant.Entiteit;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class BesteldItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tafel_id")
    private Tafel tafel;

    @ManyToOne
    private MenuItem menuItem;

    @ManyToOne
    private Drank drank;

    private int hoeveelheid;
    private BigDecimal prijs;

    @Column(name = "item_naam")
    private String itemNaam;

    // Constructors, getters, setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tafel getTafel() {
        return tafel;
    }

    public void setTafel(Tafel tafel) {
        this.tafel = tafel;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public Drank getDrank() {
        return drank;
    }

    public void setDrank(Drank drank) {
        this.drank = drank;
    }

    public int getHoeveelheid() {
        return hoeveelheid;
    }

    public void setHoeveelheid(int hoeveelheid) {
        this.hoeveelheid = hoeveelheid;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }

    public void setPrijs(BigDecimal prijs) {
        this.prijs = prijs;
    }

    public String getItemNaam() {
        return itemNaam;
    }

    public void setItemNaam(String itemNaam) {
        this.itemNaam = itemNaam;
    }
}