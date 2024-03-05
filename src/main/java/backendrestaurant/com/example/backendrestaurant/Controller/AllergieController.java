package backendrestaurant.com.example.backendrestaurant.Controller;

import backendrestaurant.com.example.backendrestaurant.Allergie;
import backendrestaurant.com.example.backendrestaurant.Service.AllergieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/allergieen")
public class AllergieController {

    @Autowired
    private AllergieService allergieService;

    @GetMapping
    public List<Allergie> getAllAllergieen() {
        return allergieService.getAllAllergieen();
    }

    @GetMapping("/{id}")
    public Allergie getAllergieById(@PathVariable Long id) {
        return allergieService.getAllergieById(id);
    }

    @PostMapping
    public Allergie createAllergie(@RequestBody Allergie allergie) {
        return allergieService.createAllergie(allergie);
    }

    @PutMapping("/{id}")
    public Allergie updateAllergie(@PathVariable Long id, @RequestBody Allergie updatedAllergie) {
        return allergieService.updateAllergie(id, updatedAllergie);
    }

    @DeleteMapping("/{id}")
    public void deleteAllergie(@PathVariable Long id) {
        allergieService.deleteAllergie(id);
    }
}
