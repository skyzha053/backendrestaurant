package backendrestaurant.com.example.backendrestaurant.Service;

import backendrestaurant.com.example.backendrestaurant.Allergie;
import backendrestaurant.com.example.backendrestaurant.Repository.AllergieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllergieService {

    @Autowired
    private AllergieRepository allergieRepository;

    public List<Allergie> getAllAllergieen() {
        return allergieRepository.findAll();
    }

    public Allergie getAllergieById(Long id) {
        return allergieRepository.findById(id).orElse(null);
    }

    public Allergie createAllergie(Allergie allergie) {
        return allergieRepository.save(allergie);
    }

    public Allergie updateAllergie(Long id, Allergie updatedAllergie) {
        if (allergieRepository.existsById(id)) {
            updatedAllergie.setId(id);
            return allergieRepository.save(updatedAllergie);
        }
        return null;
    }

    public void deleteAllergie(Long id) {
        allergieRepository.deleteById(id);
    }
}
