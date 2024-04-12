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

        Optional<Newsletter> existingNewsletter = newsletterRepository.findByTitle(newsletter.getTitle());


        existingNewsletter.ifPresent(newsletterRepository::delete);


        newsletterRepository.save(newsletter);
    }

    public Optional<Newsletter> getLatestNewsletter() {

        return newsletterRepository.findFirstByOrderByIdDesc();
    }
}
