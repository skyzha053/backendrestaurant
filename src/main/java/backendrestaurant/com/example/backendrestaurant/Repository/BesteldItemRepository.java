package backendrestaurant.com.example.backendrestaurant.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import backendrestaurant.com.example.backendrestaurant.Entiteit.BesteldItem;

@Repository
public interface BesteldItemRepository extends JpaRepository<BesteldItem, Long> {
    // You can add custom query methods here if needed
}
