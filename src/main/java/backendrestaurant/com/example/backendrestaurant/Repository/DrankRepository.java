package backendrestaurant.com.example.backendrestaurant.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import backendrestaurant.com.example.backendrestaurant.Entiteit.Drank; // Correct import

import java.util.Optional;

public interface DrankRepository extends JpaRepository<Drank, Long> {
   Optional<Drank> findByNaam(String naam);
}
