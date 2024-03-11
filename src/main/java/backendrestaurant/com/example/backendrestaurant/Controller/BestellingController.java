package backendrestaurant.com.example.backendrestaurant.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

import backendrestaurant.com.example.backendrestaurant.Entiteit.BestellingRequest;
import backendrestaurant.com.example.backendrestaurant.Entiteit.Tafel;
import backendrestaurant.com.example.backendrestaurant.Repository.DrankRepository;
import backendrestaurant.com.example.backendrestaurant.Repository.MenuItemRepository;
import backendrestaurant.com.example.backendrestaurant.Repository.TafelRepository;
import backendrestaurant.com.example.backendrestaurant.Service.BestellingService; // Import your service
import backendrestaurant.com.example.backendrestaurant.Entiteit.TafelUpdateRequest;
@RestController
public class BestellingController {

    @Autowired
    private TafelRepository tafelRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private DrankRepository drankRepository;

    @Autowired
    private BestellingService bestellingService; // Inject your service

    @PostMapping("/plaats-bestelling")
    public ResponseEntity<String> plaatsBestelling(@RequestBody BestellingRequest bestellingRequest) {
        Tafel tafel = bestellingService.createOrUpdateTafel(bestellingRequest.getTafelNaam());

        bestellingService.processMenuItems(bestellingRequest.getBesteldeMenuItems(), tafel);
        bestellingService.processDranken(bestellingRequest.getBesteldeDranken(), tafel);

        return new ResponseEntity<>("Bestelling geplaatst", HttpStatus.CREATED);
    }
    @PostMapping("/tafel-verplaatsen")
    public ResponseEntity<String> updateTafelNaam(@RequestBody TafelUpdateRequest tafelUpdateRequest) {
        Tafel tafel = bestellingService.updateTafel(
                tafelUpdateRequest.getTafelNaam(),
                tafelUpdateRequest.getNieuweNaam()
        );

        if (tafel != null) {
            return new ResponseEntity<>("Tafel bijgewerkt", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Tafel niet gevonden", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update-bestelling")
    public ResponseEntity<String> updateBestelling(@RequestBody Map<String, Object> payload) {
        String tafelNaam = (String) payload.get("tafelNaam");
        String itemName = (String) payload.get("itemName");
        int hoeveelheid = (int) payload.get("hoeveelheid");

        return bestellingService.updateBestelling(tafelNaam, itemName, hoeveelheid);
    }


}
