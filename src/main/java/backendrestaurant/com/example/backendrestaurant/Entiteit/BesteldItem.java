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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "drank_id")
    private Drank drank;

    @ManyToOne
    @JoinColumn(name = "menu_item_id")
    private MenuItem menuItem;



    @ManyToOne
    @JoinColumn(name = "bon_id")
    private Bon bon;

    @Column(name = "hoeveelheid")
    private int hoeveelheid;

    @Column(name = "prijs")
    private BigDecimal prijs;

    @Column(name = "item_naam")
    private String itemNaam;



    public boolean isDrank() {
        return this.drank != null;
    }

    public boolean isMenuItem() {
        return this.menuItem != null;
    }

    public int getHoeveelheid() {
        return this.hoeveelheid;
    }

    public Drank getDrank() {

        return drank;
    }

    public MenuItem getMenuItem() {

        return menuItem;
    }
    public String getItemNaam() {
        return this.itemNaam;
    }

    public void setTafel(Tafel tafel) {
        this.tafel = tafel;
    }

    public void setHoeveelheid(int hoeveelheid) {
        this.hoeveelheid = hoeveelheid;
    }

    public void setPrijs(BigDecimal prijs) {
        this.prijs = prijs;
    }

    public void setItemNaam(String itemNaam) {
        this.itemNaam = itemNaam;
    }

    public void setDrank(Drank drank) {
        this.drank = drank;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public BigDecimal getPrijs() {
        return this.prijs;
    }

    public void setBon(Bon bon) { this.bon = bon;}


}
