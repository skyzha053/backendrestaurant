package backendrestaurant.com.example.backendrestaurant.Controller;

import backendrestaurant.com.example.backendrestaurant.Entiteit.Allergie;
import backendrestaurant.com.example.backendrestaurant.Service.AllergieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/allergie/allergieen")
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


}
