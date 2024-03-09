package backendrestaurant.com.example.backendrestaurant.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import backendrestaurant.com.example.backendrestaurant.Entiteit.BesteldItem;
import backendrestaurant.com.example.backendrestaurant.Entiteit.Tafel;
import java.util.Optional;

@Repository
public interface BesteldItemRepository extends JpaRepository<BesteldItem, Long> {
    Optional<BesteldItem> findByTafelAndItemNaam(Tafel tafel, String itemNaam);
}
