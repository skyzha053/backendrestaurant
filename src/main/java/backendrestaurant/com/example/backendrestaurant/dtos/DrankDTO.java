package backendrestaurant.com.example.backendrestaurant.dtos;


import java.math.BigDecimal;

public class DrankDTO {
    private Long id;
    private String naam;
    private BigDecimal prijs;

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
