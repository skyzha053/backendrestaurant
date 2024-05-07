package backendrestaurant.com.example.backendrestaurant.Controller;
import backendrestaurant.com.example.backendrestaurant.Entiteit.Allergie;
import backendrestaurant.com.example.backendrestaurant.Service.AllergieService;
import backendrestaurant.com.example.backendrestaurant.dtos.AllergieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/allergies")
public class AllergieController {

    @Autowired
    private AllergieService allergieService;

    @GetMapping
    public ResponseEntity<List<AllergieDTO>> getAllergies() {
        List<Allergie> allergies = allergieService.getAllAllergieen();
        List<AllergieDTO> allergieDTOs = allergies.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(allergieDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AllergieDTO> getAllergieById(@PathVariable Long id) {
        Allergie allergie = allergieService.getAllergieById(id);
        if (allergie != null) {
            AllergieDTO allergieDTO = mapToDTO(allergie);
            return ResponseEntity.ok().body(allergieDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<AllergieDTO> createAllergie(@RequestBody AllergieDTO allergieDTO) {
        Allergie allergie = mapToEntity(allergieDTO);
        Allergie createdAllergie = allergieService.createAllergie(allergie);
        AllergieDTO createdAllergieDTO = mapToDTO(createdAllergie);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAllergieDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AllergieDTO> updateAllergie(@PathVariable Long id, @RequestBody AllergieDTO allergieDTO) {
        Allergie allergie = mapToEntity(allergieDTO);
        Allergie updatedAllergie = allergieService.updateAllergie(id, allergie);
        if (updatedAllergie != null) {
            AllergieDTO updatedAllergieDTO = mapToDTO(updatedAllergie);
            return ResponseEntity.ok().body(updatedAllergieDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    private AllergieDTO mapToDTO(Allergie allergie) {
        return new AllergieDTO(allergie.getId(), allergie.getNaam());
    }

    private Allergie mapToEntity(AllergieDTO allergieDTO) {
        Allergie allergie = new Allergie();
        allergie.setId(allergieDTO.getId());
        allergie.setNaam(allergieDTO.getNaam());
        return allergie;
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {

        e.printStackTrace();


        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Er is een interne serverfout opgetreden. Probeer het later opnieuw.");
    }
}
