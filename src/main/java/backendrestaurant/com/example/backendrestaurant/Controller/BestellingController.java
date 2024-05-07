package backendrestaurant.com.example.backendrestaurant.Controller;

import backendrestaurant.com.example.backendrestaurant.Service.BestellingService;
import backendrestaurant.com.example.backendrestaurant.Service.BonService;
import backendrestaurant.com.example.backendrestaurant.Entiteit.TafelUpdateRequest;
import backendrestaurant.com.example.backendrestaurant.Entiteit.BestellingRequest;
import backendrestaurant.com.example.backendrestaurant.Entiteit.Tafel;
import backendrestaurant.com.example.backendrestaurant.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
public class BestellingController {

    @Autowired
    private BestellingService bestellingService;

    @Autowired
    private BonService bonService;

    @PostMapping("/bestelling/plaatsen")
    public ResponseEntity<String> plaatsBestelling(@RequestBody BestellingRequest bestellingRequest) {
        ResponseEntity<String> response;
        try {
            response = bestellingService.plaatsBestelling(bestellingRequest);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Fout bij het plaatsen van bestelling.");
        }
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @PutMapping("/bestelling/tafel-verplaatsen")
    public ResponseEntity<String> updateTafelNaam(@RequestBody TafelUpdateRequest tafelUpdateRequest) {
        try {
            Tafel tafel = bestellingService.updateTafel(
                    tafelUpdateRequest.getTafelNaam(),
                    tafelUpdateRequest.getNieuweNaam()
            );
            if (tafel != null) {
                return new ResponseEntity<>("Tafel bijgewerkt", HttpStatus.OK);
            } else {
                throw new ResourceNotFoundException("Tafel niet gevonden.");
            }
        } catch (Exception e) {
            throw new ResourceNotFoundException("Fout bij het bijwerken van tafelnaam.");
        }
    }

    @PutMapping("/bestelling/update-bestelling")
    public ResponseEntity<String> updateBestelling(@RequestBody Map<String, Object> payload) {
        ResponseEntity<String> response;
        try {
            String tafelNaam = (String) payload.get("tafelNaam");
            String itemName = (String) payload.get("itemName");
            int hoeveelheid = (int) payload.get("hoeveelheid");
            response = bestellingService.updateBestelling(tafelNaam, itemName, hoeveelheid);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Fout bij het bijwerken van bestelling.");
        }
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @GetMapping("bestelling/{tafelId}")
    public ResponseEntity<String> generateBon(@PathVariable Long tafelId) {
        String bonText = bonService.generateBon(tafelId);

        if (bonText.startsWith("Geen bestellingen gevonden") || bonText.startsWith("Tafel niet gevonden")) {
            throw new ResourceNotFoundException(bonText);
        } else {
            return new ResponseEntity<>(bonText, HttpStatus.OK);
        }
    }
}
