package backendrestaurant.com.example.backendrestaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dranken")
public class DrankController {

    @Autowired
    private DrankService drankService;

    @GetMapping
    public List<Drank> getAllDranken() {
        return drankService.getAllDranken();
    }

    @GetMapping("/{id}")
    public Drank getDrankById(@PathVariable Long id) {
        return drankService.getDrankById(id);
    }

    @PostMapping
    public Drank createDrank(@RequestBody Drank drank) {
        return drankService.createDrank(drank);
    }

    @PutMapping("/{id}")
    public Drank updateDrank(@PathVariable Long id, @RequestBody Drank updatedDrank) {
        return drankService.updateDrank(id, updatedDrank);
    }

    @DeleteMapping("/{id}")
    public void deleteDrank(@PathVariable Long id) {
        drankService.deleteDrank(id);
    }
}
