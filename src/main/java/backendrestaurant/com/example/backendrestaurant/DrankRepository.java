package backendrestaurant.com.example.backendrestaurant;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DrankRepository extends JpaRepository<Drank, Long> {
    // Aanvullende querymethoden kunnen hier worden toegevoegd indien nodig
}
