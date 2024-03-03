package backendrestaurant.com.example.backendrestaurant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    // Je kunt hier aanvullende query-methoden toevoegen indien nodig.
}
