package backendrestaurant.com.example.backendrestaurant;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AllergieRepository extends JpaRepository<Allergie, Long> {
    // Aanvullende querymethoden kunnen hier worden toegevoegd indien nodig
}
