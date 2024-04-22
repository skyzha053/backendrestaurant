package backendrestaurant.com.example.backendrestaurant.dtos;

import java.math.BigDecimal;
import java.util.Set;

public class MenuItemDTO {
    private String name;
    private String description;
    private BigDecimal price;
    private boolean available;
    private Set<String> allergenen; // In de DTO gebruiken we alleen de namen van de allergenen

    // Constructors, getters, and setters

    public MenuItemDTO() {
    }

    public MenuItemDTO(String name, String description, BigDecimal price, boolean available, Set<String> allergenen) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.available = available;
        this.allergenen = allergenen;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Set<String> getAllergenen() {
        return allergenen;
    }

    public void setAllergenen(Set<String> allergenen) {
        this.allergenen = allergenen;
    }
}
