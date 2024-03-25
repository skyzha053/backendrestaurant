package backendrestaurant.com.example.backendrestaurant.Repository;

import backendrestaurant.com.example.backendrestaurant.Entiteit.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUserName(String username);

    Optional<User> findByUserNameAndPassword(String username, String password);
}
