package backendrestaurant.com.example.backendrestaurant.Service;

import backendrestaurant.com.example.backendrestaurant.Repository.NewsletterRepository;
import backendrestaurant.com.example.backendrestaurant.Entiteit.Newsletter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class NewsletterService {

    @Autowired
    private NewsletterRepository newsletterRepository;

    public void uploadNewsletter(Newsletter newsletter) {
        // Check of er al een nieuwsbrief met dezelfde titel bestaat
        Optional<Newsletter> existingNewsletter = newsletterRepository.findByTitle(newsletter.getTitle());

        // Als er al een nieuwsbrief met dezelfde titel bestaat, verwijder deze dan
        existingNewsletter.ifPresent(newsletterRepository::delete);

        // Sla de nieuwe nieuwsbrief op
        newsletterRepository.save(newsletter);
    }

    public Optional<Newsletter> getLatestNewsletter() {
        // Haal de laatste nieuwsbrief op basis van ID
        return newsletterRepository.findFirstByOrderByIdDesc();
    }
}
