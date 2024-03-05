package backendrestaurant.com.example.backendrestaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrankService {

    @Autowired
    private DrankRepository drankRepository;

    public List<Drank> getAllDranken() {
        return drankRepository.findAll();
    }

    public Drank getDrankById(Long id) {
        return drankRepository.findById(id).orElse(null);
    }

    public Drank createDrank(Drank drank) {
        return drankRepository.save(drank);
    }

    public Drank updateDrank(Long id, Drank updatedDrank) {
        if (drankRepository.existsById(id)) {
            updatedDrank.setId(id);
            return drankRepository.save(updatedDrank);
        }
        return null;
    }

    public void deleteDrank(Long id) {
        drankRepository.deleteById(id);
    }
}
