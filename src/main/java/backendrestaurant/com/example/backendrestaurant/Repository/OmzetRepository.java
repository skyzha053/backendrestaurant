package backendrestaurant.com.example.backendrestaurant.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import backendrestaurant.com.example.backendrestaurant.Entiteit.Omzet;
public interface OmzetRepository extends JpaRepository<Omzet, Long> {
    // Add custom queries if needed
}