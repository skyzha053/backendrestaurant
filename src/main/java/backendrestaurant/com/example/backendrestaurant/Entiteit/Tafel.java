package backendrestaurant.com.example.backendrestaurant.Entiteit;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Tafel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naam;

    private boolean paid;

    @OneToMany(mappedBy = "tafel", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<BesteldItem> besteldeItems;



    public Tafel() {

    }

    public Tafel(String naam) {
        this.naam = naam;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public List<BesteldItem> getBesteldeItems() {
        return besteldeItems;
    }

    public void setBesteldeItems(List<BesteldItem> besteldeItems) {
        this.besteldeItems = besteldeItems;
    }

    private BigDecimal totalePrijs;

    public BigDecimal getTotalePrijs() {
        return totalePrijs;
    }

    public void setTotalePrijs(BigDecimal totalePrijs) {
        this.totalePrijs = totalePrijs;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

}
