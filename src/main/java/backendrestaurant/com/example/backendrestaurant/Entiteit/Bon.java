package backendrestaurant.com.example.backendrestaurant.Entiteit;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Bon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long tafelId; // Deze eigenschap heet nu 'tafelId' in plaats van 'tafel'
    private boolean paid;
    private BigDecimal totalPrijs;

    // Constructors, getters en setters
    public Bon() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTafelId() {
        return tafelId;
    }

    public void setTafelId(Long tafelId) {
        this.tafelId = tafelId;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public BigDecimal getTotalPrijs() {
        return totalPrijs;
    }

    public void setTotalPrijs(BigDecimal totalPrijs) {
        this.totalPrijs = totalPrijs;
    }
}