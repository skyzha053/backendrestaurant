Installatiehandleiding voor Spring Boot-applicatie
1. Inleiding

Deze handleiding biedt stapsgewijze instructies voor het installeren en configureren van een Spring Boot-applicatie.

2. Benodigdheden

    JDK (Java Development Kit)
    Maven 3.2.4
    Teksteditor (bijv. IntelliJ IDEA, Eclipse, Visual Studio Code)
    Een database naar keuze (bijvoorbeeld MySQL, PostgreSQL, H2)
    Postman, Insomnia (optioneel, voor het testen van REST-endpoints)

3. Installatie instructies (in de vorm van een stappenplan)

    Projectstructuur opzetten:
        Maak een nieuwe map voor je project.
        Creëer de voorgestelde mappenstructuur zoals beschreven in de handleiding.

    Maven POM-bestand aanmaken:
        Maak een pom.xml-bestand aan in de hoofdmap van je project.
        Voeg de benodigde Maven-configuratie toe, inclusief de vereiste afhankelijkheden voor je Spring Boot-applicatie.
        Zorg ervoor dat Maven 3.2.4 wordt gespecificeerd als de versie.

    Database-configuratie in application.properties aanpassen:
        Open het application.properties-bestand onder src/main/resources/.
        Pas de databaseconfiguratie aan volgens de specificaties van je gekozen database. Bijvoorbeeld:

        spring.datasource.url=jdbc:mysql://localhost:3306/database_naam
        spring.datasource.username=gebruikersnaam
        spring.datasource.password=wachtwoord
        spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

    Implementeer de Spring Boot-applicatie:
        Schrijf de hoofdklasse Application.java om de Spring Boot-applicatie te starten.
        Implementeer de benodigde functionaliteit van de applicatie.

    Voeg tests toe (optioneel):
        Implementeer testklassen onder src/test/java/ om de functionaliteit van de applicatie te testen.

    Project importeren in je IDE:
        Open je favoriete IDE.
        Importeer het project door de hoofdmap te selecteren.

    Bouw en voer de applicatie uit:
        Bouw de applicatie met Maven (mvn clean package).
        Voer de Spring Boot-applicatie uit (java -jar target/<project-naam>-<versie>.jar).

4. Testgebruikers

| Gebruikersnaam | Wachtwoord  | Rol                |
|----------------|-------------|--------------------|
| Remon          | AylaRemon05!| baas               |
| Yente          | Novi123     | restaurantmanager  |
| Emma           | Novi123     | serveerster        |
| Chikit         | Novi123     | Chefkok            |

5. Postman collecties
   
[Backend.postman_collection.json](https://github.com/skyzha053/backendrestaurant/files/14804743/Backend.postman_collection.json)

6. REST-endpoints
    Swagger UI:
        URL: /swagger-ui.html
        Beschrijving: Swagger UI voor het bekijken en testen van de API-documentatie en -functionaliteit.

    Alle menu-items ophalen:
        Endpoint: /menuItems/all
        Methode: GET
        Beschrijving: Haalt alle menu-items op.

    Alle menu-items controleren op allergieën:
        Endpoint: /menuItems/all/checkAllergies
        Methode: GET
        Beschrijving: Controleert alle menu-items op allergieën.

    Nieuw menu-item toevoegen:
        Endpoint: /menuItems/all/create
        Methode: POST
        Beschrijving: Maakt een nieuw menu-item aan.

    Specifiek menu-item ophalen:
        Endpoint: /menuItems/all/{id}
        Methode: GET
        Beschrijving: Haalt een specifiek menu-item op aan de hand van het ID.

    Menu-item bijwerken:
        Endpoint: /menuItems/all/{id}
        Methode: PUT
        Beschrijving: Werkt een specifiek menu-item bij aan de hand van het ID.

    Menu-item verwijderen:
        Endpoint: /menuItems/all/{id}
        Methode: DELETE
        Beschrijving: Verwijdert een specifiek menu-item aan de hand van het ID.

    Menu-item blokkeren:
        Endpoint: /menuItems/all/{id}/block
        Methode: POST
        Beschrijving: Blokkeert een specifiek menu-item aan de hand van het ID.

    Menu-item deblokkeren:
        Endpoint: /menuItems/all/{id}/unblock
        Methode: POST
        Beschrijving: Deblokkeert een specifiek menu-item aan de hand van het ID.

    Alle allergieën ophalen:
        Endpoint: /allergie/allergieen
        Methode: GET
        Beschrijving: Haalt alle beschikbare allergieën op.

    Dranken ophalen:
        Endpoint: /drinks/
        Methode: GET
        Beschrijving: Haalt alle beschikbare dranken op.

    Nieuwe drank plaatsen:
        Endpoint: /drinks/plaatsen
        Methode: POST
        Beschrijving: Plaatst een nieuwe drank.

    Specifieke drank ophalen:
        Endpoint: /drinks/{id}
        Methode: GET
        Beschrijving: Haalt een specifieke drank op aan de hand van het ID.

    Drank bijwerken:
        Endpoint: /drinks/{id}
        Methode: PUT
        Beschrijving: Werkt een specifieke drank bij aan de hand van het ID.

    Drank verwijderen:
        Endpoint: /drinks/{id}
        Methode: DELETE
        Beschrijving: Verwijdert een specifieke drank aan de hand van het ID.

    Bestelling plaatsen:
        Endpoint: /bestelling/plaatsen
        Methode: POST
        Beschrijving: Plaatst een nieuwe bestelling.

    Tafel verplaatsen in bestelling:
        Endpoint: /bestelling/tafel-verplaatsen
        Methode: POST
        Beschrijving: Verplaatst een tafel in een bestelling.

    Bestelling bijwerken:
        Endpoint: /bestelling/update-bestelling
        Methode: PUT
        Beschrijving: Werkt een bestelling bij.

    Alle reserveringen ophalen:
        Endpoint: /reservering/reservations
        Methode: GET
        Beschrijving: Haalt alle reserveringen op.

    Reservering bijwerken:
        Endpoint: /reservering/reservations/updateReservationByName/{name}
        Methode: PUT
        Beschrijving: Werkt een reservering bij op naam.

    Reservering verwijderen op naam:
        Endpoint: /reservering/reservations/deleteByName/{name}
        Methode: DELETE
        Beschrijving: Verwijdert een reservering op naam.

    Bon genereren:
        Endpoint: /bon
        Methode: GET
        Beschrijving: Genereert een bon.

    Bon voor specifieke tafel genereren:
        Endpoint: /bon/{tafelId}
        Methode: GET
        Beschrijving: Genereert een bon voor een specifieke tafel.

    Bon als betaald markeren voor specifieke tafel:
        Endpoint: /bon/{tafelId}/betaald
        Methode: POST
        Beschrijving: Markeert een bon als betaald voor een specifieke tafel.

    Alle gebruikers ophalen:
        Endpoint: /users
        Methode: GET
        Beschrijving: Haalt alle gebruikers op.

    Specifieke gebruiker ophalen:
        Endpoint: /users/{id}
        Methode: GET
        Beschrijving: Haalt een specifieke gebruiker op aan de hand van het ID.

    Inloggen:
        Endpoint: /login
        Methode: POST
        Beschrijving: Verifieert de inloggegevens en geeft een token terug.

    Nieuwsbrief downloaden (laatste versie):
        Endpoint: /newsletter/download/latest
        Methode: GET
        Beschrijving: Download de laatste versie van de nieuwsbrief.

    Nieuwsbrief uploaden:
        Endpoint: /newsletter/upload
        Methode: POST
        Beschrijving: Uploadt een nieuwsbrief.

7. Overige commando’s
   
1. **Maven clean:**
   - Commando: `mvn clean`
   - Beschrijving: Verwijdert alle tijdelijke bestanden en gegenereerde artefacten, zoals de `target`-map.

2. **Maven install:**
   - Commando: `mvn install`
   - Beschrijving: Installeert het gebouwde artefact in het lokale Maven-repository, zodat het beschikbaar is voor andere projecten.

3. **Maven compile:**
   - Commando: `mvn compile`
   - Beschrijving: Compileert de broncode van het project.

4. **Maven test:**
   - Commando: `mvn test`
   - Beschrijving: Voert alle testcases in het project uit.

5. **Maven package:**
   - Commando: `mvn package`
   - Beschrijving: Verpakt de applicatie in een JAR- of WAR-bestand zonder het in het lokale Maven-repository te installeren.

6. **Starten van de Spring Boot-applicatie:**
   - Commando: `java -jar target/<project-naam>-<versie>.jar`
   - Beschrijving: Start de Spring Boot-applicatie met het gegenereerde JAR-bestand.

7. **Spring Boot Actuator endpoints:**
   - URL: `/actuator`
   - Beschrijving: Biedt verschillende endpoints voor het controleren en beheren van de Spring Boot-applicatie, zoals gezondheidscontrole, informatie over de omgeving en metrieken.

Deze commando's kunnen worden gebruikt tijdens het ontwikkelen, debuggen en beheren van de Spring Boot-applicatie om verschillende taken uit te voeren, zoals het bouwen, testen, starten en beheren van de applicatie.

