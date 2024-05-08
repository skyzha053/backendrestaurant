package backendrestaurant.com.example.backendrestaurant.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public class DrankDTO {
    private Long id;

    @NotBlank(message = "Naam mag niet leeg zijn")
    private String naam;

    @NotNull(message = "Prijs mag niet leeg zijn")
    @Positive(message = "Prijs moet een positief getal zijn")
    private BigDecimal prijs;

    // Getters and Setters
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

    public BigDecimal getPrijs() {
        return prijs;
    }

    public void setPrijs(BigDecimal prijs) {
        this.prijs = prijs;
    }
}
