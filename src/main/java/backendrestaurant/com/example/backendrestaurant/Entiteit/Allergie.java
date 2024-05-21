package backendrestaurant.com.example.backendrestaurant.Entiteit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Allergie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naam;

    @JsonIgnore
    @ManyToMany(mappedBy = "allergenen")
    private Set<MenuItem> menuItems = new HashSet<>();

    // No-argument constructor
    public Allergie() {}

    // Constructor with a String argument
    public Allergie(String naam) {
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

    public Set<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(Set<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public void addMenuItem(MenuItem menuItem) {
        if (menuItem != null) {
            menuItems.add(menuItem);
            menuItem.getAllergenen().add(this);
        }
    }

    public void removeMenuItem(MenuItem menuItem) {
        if (menuItem != null) {
            menuItems.remove(menuItem);
            menuItem.getAllergenen().remove(this);
        }
    }
}
