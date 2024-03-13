package backendrestaurant.com.example.backendrestaurant.Controller;

import backendrestaurant.com.example.backendrestaurant.Entiteit.Omzet;
import backendrestaurant.com.example.backendrestaurant.Service.OmzetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/omzet")
public class OmzetController {

    @Autowired
    private OmzetService omzetService;

    // Endpoint om de totale omzet op te halen
    @GetMapping("/totaal")
    public double getTotaalOmzet() {
        return omzetService.getTotaalOmzet();
    }
    @PostMapping("/bereken-en-opslaan")
    public void berekenEnSlaOp() {
        omzetService.berekenEnSlaOp();
    }

}
