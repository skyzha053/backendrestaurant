package backendrestaurant.com.example.backendrestaurant.Repository;

import backendrestaurant.com.example.backendrestaurant.Entiteit.Allergie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AllergieRepository extends JpaRepository<Allergie, Long> {
    List<Allergie> findByNaam(String naam);
}
