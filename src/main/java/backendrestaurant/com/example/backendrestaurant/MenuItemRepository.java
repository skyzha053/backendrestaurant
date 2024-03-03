package backendrestaurant.com.example.backendrestaurant;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    // Aanvullende methoden kunnen hier worden toegevoegd indien nodig
}
