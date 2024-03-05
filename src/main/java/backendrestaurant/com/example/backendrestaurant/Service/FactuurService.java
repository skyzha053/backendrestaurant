package backendrestaurant.com.example.backendrestaurant.Service;

import backendrestaurant.com.example.backendrestaurant.Factuur;
import backendrestaurant.com.example.backendrestaurant.Repository.FactuurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactuurService {

    @Autowired
    private FactuurRepository factuurRepository;

    public List<Factuur> getAllFacturen() {
        return factuurRepository.findAll();
    }

    public Factuur getFactuurById(Long id) {
        return factuurRepository.findById(id).orElse(null);
    }

    public Factuur createFactuur(Factuur factuur) {
        return factuurRepository.save(factuur);
    }

    public Factuur updateFactuur(Long id, Factuur updatedFactuur) {
        if (factuurRepository.existsById(id)) {
            updatedFactuur.setId(id);
            return factuurRepository.save(updatedFactuur);
        }
        return null;
    }

    public void deleteFactuur(Long id) {
        factuurRepository.deleteById(id);
    }
}
