package backendrestaurant.com.example.backendrestaurant.Repository;

import backendrestaurant.com.example.backendrestaurant.Entiteit.Tafel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TafelRepository extends JpaRepository<Tafel, Long> {
    Optional<Tafel> findByNaam(String naam);
}
