package backendrestaurant.com.example.backendrestaurant.Controller;

import backendrestaurant.com.example.backendrestaurant.Entiteit.Drank;
import backendrestaurant.com.example.backendrestaurant.Service.DrankService;
import backendrestaurant.com.example.backendrestaurant.dtos.DrankDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/drinks")
public class DrankController {

    @Autowired
    private DrankService drankService;

    @GetMapping
    public ResponseEntity<List<DrankDTO>> getAllDranken() {
        try {
            List<Drank> dranken = drankService.getAllDranken();
            List<DrankDTO> drankDTOs = dranken.stream()
                    .map(this::mapToDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok().body(drankDTOs);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Er is een interne serverfout opgetreden. Probeer het later opnieuw.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DrankDTO> getDrankById(@PathVariable Long id) {
        try {
            Drank drank = drankService.getDrankById(id);
            if (drank != null) {
                DrankDTO drankDTO = mapToDto(drank);
                return ResponseEntity.ok().body(drankDTO);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Er is een interne serverfout opgetreden. Probeer het later opnieuw.");
        }
    }

    @PostMapping
    public ResponseEntity<DrankDTO> createDrank(@RequestBody DrankDTO drankDTO) {
        try {
            Drank createdDrank = drankService.createDrank(mapToEntity(drankDTO));
            DrankDTO createdDrankDTO = mapToDto(createdDrank);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDrankDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Er is een interne serverfout opgetreden. Probeer het later opnieuw.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DrankDTO> updateDrank(@PathVariable Long id, @RequestBody DrankDTO updatedDrankDTO) {
        try {
            Drank updatedDrank = mapToEntity(updatedDrankDTO);
            updatedDrank.setId(id);
            Drank drank = drankService.updateDrank(id, updatedDrank);
            if (drank != null) {
                DrankDTO drankDTO = mapToDto(drank);
                return ResponseEntity.ok().body(drankDTO);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Er is een interne serverfout opgetreden. Probeer het later opnieuw.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDrank(@PathVariable Long id) {
        try {
            drankService.deleteDrank(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Er is een interne serverfout opgetreden. Probeer het later opnieuw.");
        }
    }


    private Drank mapToEntity(DrankDTO drankDTO) {
        Drank drank = new Drank();
        drank.setNaam(drankDTO.getNaam());
        drank.setPrijs(drankDTO.getPrijs());
        return drank;
    }

    private DrankDTO mapToDto(Drank drank) {
        DrankDTO drankDTO = new DrankDTO();
        drankDTO.setId(drank.getId());
        drankDTO.setNaam(drank.getNaam());
        drankDTO.setPrijs(drank.getPrijs());
        return drankDTO;
    }
}
