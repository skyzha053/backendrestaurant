package backendrestaurant.com.example.backendrestaurant.Entiteit;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private BigDecimal price;

    private boolean available;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Allergie> allergenen = new HashSet<>();

    // Constructors, getters, setters, and other necessary methods

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

    public Set<Allergie> getAllergenen() {
        return allergenen;
    }

    public void setAllergenen(Set<Allergie> allergenen) {
        this.allergenen = allergenen;
    }

    // Additional methods added
    public void addAllergie(Allergie allergie) {
        if (allergie != null) {
            allergenen.add(allergie);
            allergie.getMenuItems().add(this);
        }
    }

    public void removeAllergie(Allergie allergie) {
        if (allergie != null) {
            allergenen.remove(allergie);
            allergie.getMenuItems().remove(this);
        }
    }
}
