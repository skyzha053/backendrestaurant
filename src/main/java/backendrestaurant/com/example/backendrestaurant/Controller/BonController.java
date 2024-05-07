package backendrestaurant.com.example.backendrestaurant.Controller;

import backendrestaurant.com.example.backendrestaurant.Service.BonService;
import backendrestaurant.com.example.backendrestaurant.dtos.BonDTO;
import backendrestaurant.com.example.backendrestaurant.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bonnen")
public class BonController {

    @Autowired
    private BonService bonService;

    @GetMapping
    public ResponseEntity<String> getAllBonnen() {
        String bonnen = bonService.getAllBonnen();
        return new ResponseEntity<>(bonnen, HttpStatus.OK);
    }

    @GetMapping("/{tafelId}")
    public ResponseEntity<String> generateBon(@PathVariable Long tafelId) {
        String bonText = bonService.generateBon(tafelId);

        if (bonText.startsWith("Geen bestellingen gevonden") || bonText.startsWith("Tafel niet gevonden")) {
            throw new ResourceNotFoundException(bonText);
        }

        boolean isPaid = bonService.isPaid(tafelId);
        bonText += "\nIs Paid: " + isPaid;
        bonService.saveBon(tafelId, isPaid);
        return new ResponseEntity<>(bonText, HttpStatus.OK);
    }

    @PutMapping("/{tafelId}/betaald")
    public ResponseEntity<String> markAsPaid(@PathVariable Long tafelId) {
        bonService.setPaidStatus(tafelId, true);
        return new ResponseEntity<>("Order for Tafel with ID " + tafelId + " is marked as paid.", HttpStatus.OK);
    }

    @PostMapping("/generate")
    public ResponseEntity<String> generateBon(@RequestBody BonDTO bonDTO) {
        Long tafelId = bonDTO.getTafelId();
        String bonText = bonService.generateBon(tafelId);

        if (bonText.startsWith("Geen bestellingen gevonden") || bonText.startsWith("Tafel niet gevonden")) {
            throw new ResourceNotFoundException(bonText);
        }

        boolean isPaid = bonService.isPaid(tafelId);
        bonText += "\nIs Paid: " + isPaid;

        bonService.saveBon(tafelId, isPaid);

        return new ResponseEntity<>(bonText, HttpStatus.OK);
    }
}
