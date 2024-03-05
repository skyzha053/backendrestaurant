package backendrestaurant.com.example.backendrestaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facturen")
public class FactuurController {

    @Autowired
    private FactuurService factuurService;

    @GetMapping
    public List<Factuur> getAllFacturen() {
        return factuurService.getAllFacturen();
    }

    @GetMapping("/{id}")
    public Factuur getFactuurById(@PathVariable Long id) {
        return factuurService.getFactuurById(id);
    }

    @PostMapping
    public Factuur createFactuur(@RequestBody Factuur factuur) {
        return factuurService.createFactuur(factuur);
    }

    @PutMapping("/{id}")
    public Factuur updateFactuur(@PathVariable Long id, @RequestBody Factuur updatedFactuur) {
        return factuurService.updateFactuur(id, updatedFactuur);
    }

    @DeleteMapping("/{id}")
    public void deleteFactuur(@PathVariable Long id) {
        factuurService.deleteFactuur(id);
    }
}
