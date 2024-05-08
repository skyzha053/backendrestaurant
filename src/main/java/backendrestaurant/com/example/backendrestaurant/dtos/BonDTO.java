package backendrestaurant.com.example.backendrestaurant.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public class BonDTO {
    @NotNull(message = "Tafel ID mag niet leeg zijn")
    @Positive(message = "Tafel ID moet een positief getal zijn")
    private Long tafelId;

    private boolean paid;

    @NotNull(message = "Totale prijs mag niet leeg zijn")
    @Positive(message = "Totale prijs moet een positief getal zijn")
    private BigDecimal totalPrijs;

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
