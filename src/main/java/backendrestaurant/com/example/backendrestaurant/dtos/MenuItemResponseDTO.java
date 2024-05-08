package backendrestaurant.com.example.backendrestaurant.dtos;

import jakarta.validation.constraints.NotBlank;
import java.util.Set;

public class MenuItemResponseDTO {
    private Long id;

    @NotBlank(message = "Naam mag niet leeg zijn")
    private String name;

    private String description;
    private double price;
    private boolean available;
    private Set<String> allergens;


    public MenuItemResponseDTO() {
    }

    public MenuItemResponseDTO(Long id, String name, String description, double price, boolean available, Set<String> allergens) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.available = available;
        this.allergens = allergens;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Set<String> getAllergens() {
        return allergens;
    }

    public void setAllergens(Set<String> allergens) {
        this.allergens = allergens;
    }
}
