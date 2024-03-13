package backendrestaurant.com.example.backendrestaurant.Controller;

import backendrestaurant.com.example.backendrestaurant.Service.BonService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bon")
public class BonController {

    @Autowired
    private BonService bonService;

    @GetMapping("/{tafelId}")
    public ResponseEntity<String> generateBon(@PathVariable Long tafelId) {
        String bonText = bonService.generateBon(tafelId);

        if (bonText.startsWith("Geen bestellingen gevonden") || bonText.startsWith("Tafel niet gevonden")) {
            return new ResponseEntity<>(bonText, HttpStatus.NOT_FOUND);
        }

        // Check if the order is paid for this specific table
        boolean isPaid = bonService.isPaid(tafelId);
        bonText += "\nIs Paid: " + isPaid;

        // Save bon in the database
        bonService.saveBon(tafelId, isPaid);

        return new ResponseEntity<>(bonText, HttpStatus.OK);
    }


    @PutMapping("/{tafelId}/betaald")
    public ResponseEntity<String> markAsPaid(@PathVariable Long tafelId) {
        bonService.setPaidStatus(tafelId, true); // Set the paid status to true for the specified table
        return new ResponseEntity<>("Order for Tafel with ID " + tafelId + " is marked as paid.", HttpStatus.OK);
    }
}
