package backendrestaurant.com.example.backendrestaurant.Repository;

import backendrestaurant.com.example.backendrestaurant.Entiteit.Bon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
public interface BonRepository extends JpaRepository<Bon, Long> {
    Optional<Bon> findByTafelId(Long tafelId);
}