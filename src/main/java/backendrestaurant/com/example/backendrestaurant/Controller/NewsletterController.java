package backendrestaurant.com.example.backendrestaurant.Controller;

import backendrestaurant.com.example.backendrestaurant.Entiteit.Newsletter;
import backendrestaurant.com.example.backendrestaurant.Service.NewsletterService;
import backendrestaurant.com.example.backendrestaurant.dtos.NewsletterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/newsletter")
public class NewsletterController {
    @Autowired
    private NewsletterService newsletterService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadNewsletter(@RequestParam("nieuwsbrief") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("Upload bestand is leeg.");
            }
            NewsletterDTO newsletterDTO = new NewsletterDTO();
            newsletterDTO.setFileName(file.getOriginalFilename());
            newsletterDTO.setData(file.getBytes());
            newsletterService.uploadNewsletter(mapToEntity(newsletterDTO));
            return ResponseEntity.ok("Nieuwsbrief succesvol geüpload.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Fout bij het uploaden van de nieuwsbrief.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Er is een interne serverfout opgetreden. Probeer het later opnieuw.");
        }
    }

    @GetMapping("/download/latest")
    public ResponseEntity<byte[]> downloadLatestNewsletter() {
        try {
            Optional<Newsletter> latestNewsletter = newsletterService.getLatestNewsletter();
            if (latestNewsletter.isPresent()) {
                Newsletter newsletter = latestNewsletter.get();
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_PDF);
                headers.setContentDispositionFormData("attachment", "nieuwsbrief.pdf");
                headers.setContentLength(newsletter.getData().length);
                return ResponseEntity.ok()
                        .headers(headers)
                        .body(newsletter.getData());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    private Newsletter mapToEntity(NewsletterDTO newsletterDTO) {
        Newsletter newsletter = new Newsletter();
        newsletter.setFileName(newsletterDTO.getFileName());
        newsletter.setData(newsletterDTO.getData());
        return newsletter;
    }
}
