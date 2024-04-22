package backendrestaurant.com.example.backendrestaurant.Controller;

import backendrestaurant.com.example.backendrestaurant.Entiteit.Drank;
import backendrestaurant.com.example.backendrestaurant.Service.DrankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/drinks")
public class DrankController {

    @Autowired
    private DrankService drankService;

    @GetMapping
    public ResponseEntity<List<Drank>> getAllDranken() {
        List<Drank> dranken = drankService.getAllDranken();
        return ResponseEntity.ok().body(dranken);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Drank> getDrankById(@PathVariable Long id) {
        Drank drank = drankService.getDrankById(id);
        if (drank != null) {
            return ResponseEntity.ok().body(drank);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Drank> createDrank(@RequestBody Drank drank) {
        Drank createdDrank = drankService.createDrank(drank);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDrank);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Drank> updateDrank(@PathVariable Long id, @RequestBody Drank updatedDrank) {
        Drank drank = drankService.updateDrank(id, updatedDrank);
        if (drank != null) {
            return ResponseEntity.ok().body(drank);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDrank(@PathVariable Long id) {
        drankService.deleteDrank(id);
        return ResponseEntity.noContent().build();
    }
}

