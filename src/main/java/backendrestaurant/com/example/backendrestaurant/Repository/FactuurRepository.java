package backendrestaurant.com.example.backendrestaurant.Repository;

import backendrestaurant.com.example.backendrestaurant.Factuur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactuurRepository extends JpaRepository<Factuur, Long> {
    // Aanvullende querymethoden kunnen hier worden toegevoegd indien nodig
}
