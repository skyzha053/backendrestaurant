package backendrestaurant.com.example.backendrestaurant.Repository;

import backendrestaurant.com.example.backendrestaurant.Drank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrankRepository extends JpaRepository<Drank, Long> {
    // Aanvullende querymethoden kunnen hier worden toegevoegd indien nodig
}
