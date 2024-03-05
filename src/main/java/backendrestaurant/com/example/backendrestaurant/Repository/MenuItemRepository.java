package backendrestaurant.com.example.backendrestaurant.Repository;

import backendrestaurant.com.example.backendrestaurant.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    // Aanvullende methoden kunnen hier worden toegevoegd indien nodig
}
