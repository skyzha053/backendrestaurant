package backendrestaurant.com.example.backendrestaurant.Controller;

import backendrestaurant.com.example.backendrestaurant.Entiteit.Allergie;
import backendrestaurant.com.example.backendrestaurant.Service.AllergieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/allergies")
public class AllergieController {

    @Autowired
    private AllergieService allergieService;

    @GetMapping
    public ResponseEntity<List<Allergie>> getAllergies() {
        List<Allergie> allergies = allergieService.getAllAllergieen();
        return ResponseEntity.ok().body(allergies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Allergie> getAllergieById(@PathVariable Long id) {
        Allergie allergie = allergieService.getAllergieById(id);
        if (allergie != null) {
            return ResponseEntity.ok().body(allergie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Allergie> createAllergie(@RequestBody Allergie allergie) {
        Allergie createdAllergie = allergieService.createAllergie(allergie);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAllergie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Allergie> updateAllergie(@PathVariable Long id, @RequestBody Allergie allergieDetails) {
        Allergie updatedAllergie = allergieService.updateAllergie(id, allergieDetails);
        if (updatedAllergie != null) {
            return ResponseEntity.ok().body(updatedAllergie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
