package backendrestaurant.com.example.backendrestaurant.Service;

import backendrestaurant.com.example.backendrestaurant.Entiteit.Allergie;
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
        return allergieRepository.findById(id)
                .map(existingAllergie -> {
                    updatedAllergie.setId(existingAllergie.getId());
                    return allergieRepository.save(updatedAllergie);
                })
                .orElse(null);
    }

    public Allergie getByName(String name) {
        List<Allergie> allergenList = allergieRepository.findByNaam(name);


        if (!allergenList.isEmpty()) {

            return allergenList.get(0);
        }

        return null;
    }
}
