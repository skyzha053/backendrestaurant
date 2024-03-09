package backendrestaurant.com.example.backendrestaurant.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import backendrestaurant.com.example.backendrestaurant.Entiteit.BestellingRequest;
import backendrestaurant.com.example.backendrestaurant.Entiteit.Tafel;
import backendrestaurant.com.example.backendrestaurant.Repository.DrankRepository;
import backendrestaurant.com.example.backendrestaurant.Repository.MenuItemRepository;
import backendrestaurant.com.example.backendrestaurant.Repository.TafelRepository;
import backendrestaurant.com.example.backendrestaurant.Service.BestellingService; // Import your service

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


}
