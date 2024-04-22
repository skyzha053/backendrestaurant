package backendrestaurant.com.example.backendrestaurant.dtos;

import java.math.BigDecimal;

public class BonDTO {
    private Long tafelId;
    private boolean paid;
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
