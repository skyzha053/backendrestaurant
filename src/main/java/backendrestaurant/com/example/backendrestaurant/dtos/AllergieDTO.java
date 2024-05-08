package backendrestaurant.com.example.backendrestaurant.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.NotBlank;

public class AllergieDTO {
    @NotNull(message = "ID mag niet leeg zijn")
    @Positive(message = "ID moet een positief getal zijn")
    private Long id;

    @NotBlank(message = "Naam mag niet leeg zijn")
    private String naam;

    public AllergieDTO() {
    }

    public AllergieDTO(Long id, String naam) {
        this.id = id;
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
}
