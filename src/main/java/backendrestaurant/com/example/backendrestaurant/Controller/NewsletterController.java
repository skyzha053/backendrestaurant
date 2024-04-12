package backendrestaurant.com.example.backendrestaurant.Controller;

import backendrestaurant.com.example.backendrestaurant.Entiteit.Newsletter;
import backendrestaurant.com.example.backendrestaurant.Service.NewsletterService;
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
            Newsletter newsletter = new Newsletter();
            newsletter.setFileName(file.getOriginalFilename());
            newsletter.setData(file.getBytes());
            newsletterService.uploadNewsletter(newsletter);
            return ResponseEntity.ok("Newsletter uploaded successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading newsletter.");
        }
    }

    @GetMapping("/download/latest")
    public ResponseEntity<byte[]> downloadLatestNewsletter() {
        Optional<Newsletter> latestNewsletter = newsletterService.getLatestNewsletter();
        if (latestNewsletter.isPresent()) {
            Newsletter newsletter = latestNewsletter.get();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "nieuwsletter.pdf");
            headers.setContentLength(newsletter.getData().length);
            return new ResponseEntity<>(newsletter.getData(), headers, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
