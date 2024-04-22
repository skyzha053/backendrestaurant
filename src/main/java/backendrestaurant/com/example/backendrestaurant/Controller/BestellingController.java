package backendrestaurant.com.example.backendrestaurant.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import backendrestaurant.com.example.backendrestaurant.Entiteit.TafelUpdateRequest;
import backendrestaurant.com.example.backendrestaurant.Entiteit.BestellingRequest;
import backendrestaurant.com.example.backendrestaurant.Entiteit.Tafel;
import backendrestaurant.com.example.backendrestaurant.Repository.DrankRepository;
import backendrestaurant.com.example.backendrestaurant.Repository.MenuItemRepository;
import backendrestaurant.com.example.backendrestaurant.Repository.TafelRepository;
import backendrestaurant.com.example.backendrestaurant.Service.BestellingService;

@RestController
public class BestellingController {

    @Autowired
    private TafelRepository tafelRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private DrankRepository drankRepository;

    @Autowired
    private BestellingService bestellingService;

    @PostMapping("/bestelling/plaatsen")
    public ResponseEntity<String> plaatsBestelling(@RequestBody BestellingRequest bestellingRequest) {
        try {
            ResponseEntity<String> response = bestellingService.plaatsBestelling(bestellingRequest);
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Er is een interne serverfout opgetreden. Probeer het later opnieuw.");
        }
    }

    @PostMapping("/bestelling/tafel-verplaatsen")
    public ResponseEntity<String> updateTafelNaam(@RequestBody TafelUpdateRequest tafelUpdateRequest) {
        try {
            Tafel tafel = bestellingService.updateTafel(
                    tafelUpdateRequest.getTafelNaam(),
                    tafelUpdateRequest.getNieuweNaam()
            );
            if (tafel != null) {
                return new ResponseEntity<>("Tafel bijgewerkt", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Tafel niet gevonden", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Er is een interne serverfout opgetreden. Probeer het later opnieuw.");
        }
    }

    @PutMapping("/bestelling/update-bestelling")
    public ResponseEntity<String> updateBestelling(@RequestBody Map<String, Object> payload) {
        try {
            String tafelNaam = (String) payload.get("tafelNaam");
            String itemName = (String) payload.get("itemName");
            int hoeveelheid = (int) payload.get("hoeveelheid");

            ResponseEntity<String> response = bestellingService.updateBestelling(tafelNaam, itemName, hoeveelheid);
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Er is een interne serverfout opgetreden. Probeer het later opnieuw.");
        }
    }
}
