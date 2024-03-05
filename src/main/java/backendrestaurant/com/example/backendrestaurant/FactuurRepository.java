package backendrestaurant.com.example.backendrestaurant;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FactuurRepository extends JpaRepository<Factuur, Long> {
    // Aanvullende querymethoden kunnen hier worden toegevoegd indien nodig
}
