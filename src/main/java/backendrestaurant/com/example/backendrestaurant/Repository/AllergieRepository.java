package backendrestaurant.com.example.backendrestaurant.Repository;

import backendrestaurant.com.example.backendrestaurant.Allergie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllergieRepository extends JpaRepository<Allergie, Long> {
    // Aanvullende querymethoden kunnen hier worden toegevoegd indien nodig
}
