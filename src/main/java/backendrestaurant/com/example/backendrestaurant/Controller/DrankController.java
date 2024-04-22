package backendrestaurant.com.example.backendrestaurant.Controller;

import backendrestaurant.com.example.backendrestaurant.Entiteit.Drank;
import backendrestaurant.com.example.backendrestaurant.Service.DrankService;
import backendrestaurant.com.example.backendrestaurant.dtos.DrankDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/drinks")
public class DrankController {

    @Autowired
    private DrankService drankService;

    @GetMapping
    public ResponseEntity<List<DrankDTO>> getAllDranken() {
        List<Drank> dranken = drankService.getAllDranken();
        List<DrankDTO> drankDTOs = dranken.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(drankDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DrankDTO> getDrankById(@PathVariable Long id) {
        Drank drank = drankService.getDrankById(id);
        if (drank != null) {
            DrankDTO drankDTO = mapToDto(drank);
            return ResponseEntity.ok().body(drankDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<DrankDTO> createDrank(@RequestBody DrankDTO drankDTO) {
        Drank createdDrank = drankService.createDrank(mapToEntity(drankDTO));
        DrankDTO createdDrankDTO = mapToDto(createdDrank);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDrankDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DrankDTO> updateDrank(@PathVariable Long id, @RequestBody DrankDTO updatedDrankDTO) {
        Drank updatedDrank = mapToEntity(updatedDrankDTO);
        updatedDrank.setId(id);
        Drank drank = drankService.updateDrank(id, updatedDrank);
        if (drank != null) {
            DrankDTO drankDTO = mapToDto(drank);
            return ResponseEntity.ok().body(drankDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDrank(@PathVariable Long id) {
        drankService.deleteDrank(id);
        return ResponseEntity.noContent().build();
    }

    // Helper method to map from DTO to entity
    private Drank mapToEntity(DrankDTO drankDTO) {
        Drank drank = new Drank();
        drank.setNaam(drankDTO.getNaam());
        drank.setPrijs(drankDTO.getPrijs());
        return drank;
    }

    // Helper method to map from entity to DTO
    private DrankDTO mapToDto(Drank drank) {
        DrankDTO drankDTO = new DrankDTO();
        drankDTO.setId(drank.getId());
        drankDTO.setNaam(drank.getNaam());
        drankDTO.setPrijs(drank.getPrijs());
        return drankDTO;
    }
}
