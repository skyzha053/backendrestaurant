### Installatiehandleiding voor mijn Spring Boot Applicatie

Welkom bij de installatiehandleiding voor mijn Spring Boot applicatie! Volg deze eenvoudige stappen om de applicatie te installeren en te gebruiken.

#### Vereisten

Voordat je begint, zorg ervoor dat je de volgende vereisten hebt geïnstalleerd:

1. Java Development Kit (JDK) 8 of hoger.
2. Apache Maven.
3. Een SQL-database zoals MySQL of PostgreSQL.

#### Stappen voor Installatie

1. **Clone de Repository:**
   Clone de repository van mijn Spring Boot applicatie vanuit de bron waar deze is gehost, bijvoorbeeld GitHub.

   ```bash
   git clone https://github.com/skyzha053/backendrestaurant.git
   ```

2. **Build de Applicatie:**
   Navigeer naar de hoofdmap van het gekloonde project en bouw de applicatie met Maven.

   ```bash
   cd <project_map>
   mvn clean package
   ```

3. **Configure de Database:**
   Maak een nieuwe database aan in jouw SQL-databaseomgeving en update de databaseconfiguratie in het `application.properties` bestand met de juiste database-URL, gebruikersnaam en wachtwoord.

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/mijn_database
   spring.datasource.username=jouw_gebruikersnaam
   spring.datasource.password=jouw_wachtwoord
   ```

4. **Voer de Applicatie uit:**
   Voer de Spring Boot applicatie uit met behulp van het volgende Maven-commando.

   ```bash
   java -jar target/<applicatie_naam>.jar
   ```

5. **Controleer de Toepassing:**
   Open een webbrowser en ga naar `http://localhost:8080` om te controleren of de applicatie correct is gestart.

6. **Gebruik de Applicatie:**
   Gefeliciteerd! De applicatie is nu geïnstalleerd en klaar voor gebruik. Volg de instructies op het scherm om de gewenste functionaliteit te gebruiken.





7. **overige commando's**

**Maven clean:**
   - Commando: `mvn clean`
   - Beschrijving: Verwijdert alle tijdelijke bestanden en gegenereerde artefacten, zoals de `target`-map.

**Maven install:**
   - Commando: `mvn install`
   - Beschrijving: Installeert het gebouwde artefact in het lokale Maven-repository, zodat het beschikbaar is voor andere projecten.

**Maven compile:**
   - Commando: `mvn compile`
   - Beschrijving: Compileert de broncode van het project.

**Maven test:**
   - Commando: `mvn test`
   - Beschrijving: Voert alle testcases in het project uit.

**Maven package:**
   - Commando: `mvn package`
   - Beschrijving: Verpakt de applicatie in een JAR- of WAR-bestand zonder het in het lokale Maven-repository te installeren.

**Starten van de Spring Boot-applicatie:**
   - Commando: `java -jar target/<project-naam>-<versie>.jar`
   - Beschrijving: Start de Spring Boot-applicatie met het gegenereerde JAR-bestand.


### Testgebruikers:

| Gebruikersnaam | Wachtwoord  | Rol                |
|----------------|-------------|--------------------|
| Remon          | AylaRemon05!| baas               |
| Yente          | Novi123     | restaurantmanager  |
| Emma           | Novi123     | serveerster        |
| Chikit         | Novi123     | Chefkok            |


### REST-endpoints:


| Endpoint                    | Methode | Beschrijving                                | Body                                                                                          |
|-----------------------------|---------|---------------------------------------------|-----------------------------------------------------------------------------------------------|
| /swagger-ui.html            | -       | Swagger UI voor API-documentatie en -testen | -                                                                                             |
| /Login                      | POST    | Inloggen van gebruiker                      | {"userName": "string", "password": "string"}                                                  |
| /users                      | POST    | Aanmaken van gebruiker                      | {"userName": "string", "password": "string", "roles": ["ROLE_SERVEERSTERS"]}                  |
| /newsletter/upload          | POST    | Uploaden van nieuwsbrief                    | form-data                                                                                     |
| /newsletter/download/latest| POST    | Downloaden van nieuwsbrief                  | -                                                                                             |
| /allergies/{{id}}           | PUT     | Updaten van allergie                        | {"id": "1", "naam": "Nieuwe Allergie Naam"}                                                  |
| /allergies                  | GET     | Opvragen van alle allergieën                | -                                                                                             |
| /allergies/{{id}}           | GET     | Opvragen van allergie per id                | -                                                                                             |
| /bestelling/{{id}}          | GET     | Opvragen van bestelling                     | -                                                                                             |
| /bestelling/plaatsen        | POST    | Bestelling plaatsen                         | {"tafelNaam": "pieter", "besteldeMenuItems": [{"itemNaam": "Mediterraans Landbrood", "hoeveelheid": 3}], "besteldeDranken": [{"itemNaam": "Cola", "hoeveelheid": 1}, {"itemNaam": "Fanta", "hoeveelheid": 2}]} |
| /bestelling/tafel-verplaatsen| PUT    | Tafel verplaatsen                           | {"tafelNaam": "string", "nieuweNaam": "string"}                                               |
| /bestelling/update-bestelling| PUT    | Bestelling veranderen                       | {"tafelNaam": "string", "itemName": "Mediterraans Landbrood", "hoeveelheid": 100}             |
| /bonnen/{{id}}              | GET     | Vraag bon per tafelId                      | -                                                                                             |
| /bonnen                     | GET     | Vraag alle bonnen op                        | -                                                                                             |
| /bonnen/{{id}}/betaald      | GET     | Verander bon na betaald                     | -                                                                                             |
| /drinks                     | GET     | Vraag alle dranken op                       | -                                                                                             |
| /drinks                     | POST    | Voeg drank toe aan database                 | {"naam": "string", "type": "string", "prijs": 0}                                              |
| /drinks/{{id}}              | DEL     | Verwijder drank uit database                | -                                                                                             |
| /drinks/{{id}}              | PUT     | Bewerk drank op basis van id                | {"id": 1, "naam": "fanta", "prijs": 0}                                                       |
| /drinks/{{id}}              | GET     | Vraag drank aan op id                       | -                                                                                             |
| /menuItems/all/checkAllergies| GET    | Vraag allergie op in menuitem                | {"menuItemId": 1, "allergens": ["gluten"]}                                                   |
| /menuItems/all              | GET     | Vraag alle menu's op                        | -                                                                                             |
| /menuItems/all/{{id}}/block| PUT     | Blokkeer menu item per id                   | -                                                                                             |
| /menuItems/all/{{id}}/unblock| PUT   | Deblokkeer menu item per id                 | -                                                                                             |
| /menuItems/all/{{id}}       | DEL     | Delete menu item op id                      | -                                                                                             |
| /menuItems/all/{{id}}       | GET     | Vraag menu op op basis van id               | -                                                                                             |
| /reservations/deleteByName/{{name}}| DEL| Verwijder reservering                       | -                                                                                             |
| /reservations               | POST    | Plaats reservering                          | {"name": "piet", "numberOfPersons": 0, "reservationDate": "2024-03-27", "allergies": "string", "comments": "string", "phoneNumber": "string", "emailAddress": "string", "bedrijfsnaam": "string"} |
| /reservations               | GET     | Ophalen van alle reserveringen              | -                                                                                             |
| /reservations/updateReservationByName/{{name}}| PUT| Updaten van een bestaande reservering      | {"id": 0, "name": "string", "numberOfPersons": 0, "reservationDate": "2024-03-27", "allergies": "string", "comments": "string", "phoneNumber": "string", "emailAddress": "string", "bedrijfsnaam": "string"} |



[Upload{
	"info": {
		"_postman_id": "92e9a2ab-e676-48de-9266-9d6c6caa6e5e",
		"name": "Backend",
		"description": "# 🚀 Get started here\n\nThis template guides you through CRUD operations (GET, POST, PUT, DELETE), variables, and tests.\n\n## 🔖 **How to use this template**\n\n#### **Step 1: Send requests**\n\nRESTful APIs allow you to perform CRUD operations using the POST, GET, PUT, and DELETE HTTP methods.\n\nThis collection contains each of these [request](https://learning.postman.com/docs/sending-requests/requests/) types. Open each request and click \"Send\" to see what happens.\n\n#### **Step 2: View responses**\n\nObserve the response tab for status code (200 OK), response time, and size.\n\n#### **Step 3: Send new Body data**\n\nUpdate or add new data in \"Body\" in the POST request. Typically, Body data is also used in PUT request.\n\n```\n{\n    \"name\": \"Add your name in the body\"\n}\n\n ```\n\n#### **Step 4: Update the variable**\n\nVariables enable you to store and reuse values in Postman. We have created a [variable](https://learning.postman.com/docs/sending-requests/variables/) called `base_url` with the sample request [https://postman-api-learner.glitch.me](https://postman-api-learner.glitch.me). Replace it with your API endpoint to customize this collection.\n\n#### **Step 5: Add tests in the \"Tests\" tab**\n\nTests help you confirm that your API is working as expected. You can write test scripts in JavaScript and view the output in the \"Test Results\" tab.\n\n<img src=\"https://content.pstmn.io/b5f280a7-4b09-48ec-857f-0a7ed99d7ef8/U2NyZWVuc2hvdCAyMDIzLTAzLTI3IGF0IDkuNDcuMjggUE0ucG5n\">\n\n## 💪 Pro tips\n\n- Use folders to group related requests and organize the collection.\n- Add more [scripts](https://learning.postman.com/docs/writing-scripts/intro-to-scripts/) in \"Tests\" to verify if the API works as expected and execute workflows.\n    \n\n## 💡Related templates\n\n[API testing basics](https://go.postman.co/redirect/workspace?type=personal&collectionTemplateId=e9a37a28-055b-49cd-8c7e-97494a21eb54&sourceTemplateId=ddb19591-3097-41cf-82af-c84273e56719)  \n[API documentation](https://go.postman.co/redirect/workspace?type=personal&collectionTemplateId=e9c28f47-1253-44af-a2f3-20dce4da1f18&sourceTemplateId=ddb19591-3097-41cf-82af-c84273e56719)  \n[Authorization methods](https://go.postman.co/redirect/workspace?type=personal&collectionTemplateId=31a9a6ed-4cdf-4ced-984c-d12c9aec1c27&sourceTemplateId=ddb19591-3097-41cf-82af-c84273e56719)",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json",
		"_exporter_id": "26609438"
	},
	"item": [
		{
			"name": "Reservering",
			"item": [
				{
					"name": "Reservering updaten",
					"request": {
						"method": "PUT",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\r\n  \"id\": 0,\r\n  \"name\": \"string\",\r\n  \"numberOfPersons\": 0,\r\n  \"reservationDate\": \"2024-03-27\",\r\n  \"allergies\": \"string\",\r\n  \"comments\": \"string\",\r\n  \"phoneNumber\": \"string\",\r\n  \"emailAddress\": \"string\",\r\n  \"bedrijfsnaam\": \"string\"\r\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/reservations/updateReservationByName/{name}",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"reservations",
								"updateReservationByName",
								"{name}"
							]
						}
					},
					"response": []
				},
				{
					"name": "Reserveringen opvragen",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/reservations",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"reservations"
							]
						}
					},
					"response": []
				},
				{
					"name": "Reservering plaatsen",
					"request": {
						"auth": {
							"type": "bearer",
							"bearer": [
								{
									"key": "token",
									"value": "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiYWNrZW5kcmVzdGF1cmFudCIsInN1YiI6IjE6OlJlbW9uIiwicm9sZXMiOlsiUk9MRV9CQUFTIl0sImV4cCI6MTcxNzIyMjM0NywiaWF0IjoxNzE2MzU4MzQ3fQ.oCEUD3vSTv03jEvBAM67vrgN54vu6JyR5vyaPQSCMDI",
									"type": "string"
								}
							]
						},
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\r\n  \"name\": \"piet\",\r\n  \"numberOfPersons\": 0,\r\n  \"reservationDate\": \"2024-03-27\",\r\n  \"allergies\": \"string\",\r\n  \"comments\": \"string\",\r\n  \"phoneNumber\": \"string\",\r\n  \"emailAddress\": \"string\",\r\n  \"bedrijfsnaam\": \"string\"\r\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/reservations",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"reservations"
							]
						}
					},
					"response": []
				},
				{
					"name": "Reservering verwijderen",
					"request": {
						"auth": {
							"type": "bearer",
							"bearer": [
								{
									"key": "token",
									"value": "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiYWNrZW5kcmVzdGF1cmFudCIsInN1YiI6IjE6OlJlbW9uIiwicm9sZXMiOlsiUk9MRV9CQUFTIl0sImV4cCI6MTcxNzIyMjM0NywiaWF0IjoxNzE2MzU4MzQ3fQ.oCEUD3vSTv03jEvBAM67vrgN54vu6JyR5vyaPQSCMDI",
									"type": "string"
								}
							]
						},
						"method": "DELETE",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/reservations/deleteByName/{name}",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"reservations",
								"deleteByName",
								"{name}"
							]
						}
					},
					"response": []
				}
			]
		},
		{
			"name": "Menu",
			"item": [
				{
					"name": "menu item aanvragen op id",
					"request": {
						"auth": {
							"type": "bearer",
							"bearer": [
								{
									"key": "token",
									"value": "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiYWNrZW5kcmVzdGF1cmFudCIsInN1YiI6IjE6OlJlbW9uIiwicm9sZXMiOlsiUk9MRV9CQUFTIl0sImV4cCI6MTcxNzIyMjM0NywiaWF0IjoxNzE2MzU4MzQ3fQ.oCEUD3vSTv03jEvBAM67vrgN54vu6JyR5vyaPQSCMDI",
									"type": "string"
								}
							]
						},
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/menuItems/all/{{id}}",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"menuItems",
								"all",
								"{{id}}"
							]
						}
					},
					"response": []
				},
				{
					"name": "delete menu item op id",
					"request": {
						"auth": {
							"type": "bearer",
							"bearer": [
								{
									"key": "token",
									"value": "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiYWNrZW5kcmVzdGF1cmFudCIsInN1YiI6IjE6OlJlbW9uIiwicm9sZXMiOlsiUk9MRV9CQUFTIl0sImV4cCI6MTcxNzIyMjM0NywiaWF0IjoxNzE2MzU4MzQ3fQ.oCEUD3vSTv03jEvBAM67vrgN54vu6JyR5vyaPQSCMDI",
									"type": "string"
								}
							]
						},
						"method": "DELETE",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\r\n  \"name\": \"string\",\r\n  \"description\": \"string\",\r\n  \"price\": 0,\r\n  \"available\": true,\r\n  \"allergenen\": [\r\n    {\r\n      \"naam\": \"string\"\r\n    }\r\n  ]\r\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/menuItems/all/{{id}}",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"menuItems",
								"all",
								"{{id}}"
							]
						}
					},
					"response": []
				},
				{
					"name": "unblock menu item per id",
					"request": {
						"auth": {
							"type": "bearer",
							"bearer": [
								{
									"key": "token",
									"value": "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiYWNrZW5kcmVzdGF1cmFudCIsInN1YiI6IjE6OlJlbW9uIiwicm9sZXMiOlsiUk9MRV9CQUFTIl0sImV4cCI6MTcxNzIyMjM0NywiaWF0IjoxNzE2MzU4MzQ3fQ.oCEUD3vSTv03jEvBAM67vrgN54vu6JyR5vyaPQSCMDI",
									"type": "string"
								}
							]
						},
						"method": "PUT",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\r\n  \"name\": \"string\",\r\n  \"description\": \"string\",\r\n  \"price\": 0,\r\n  \"available\": true,\r\n  \"allergenen\": [\r\n    {\r\n      \"naam\": \"string\"\r\n    }\r\n  ]\r\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/menuItems/all/{{id}}/unblock",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"menuItems",
								"all",
								"{{id}}",
								"unblock"
							]
						}
					},
					"response": []
				},
				{
					"name": "block menu item per id",
					"request": {
						"auth": {
							"type": "bearer",
							"bearer": [
								{
									"key": "token",
									"value": "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiYWNrZW5kcmVzdGF1cmFudCIsInN1YiI6IjE6OlJlbW9uIiwicm9sZXMiOlsiUk9MRV9CQUFTIl0sImV4cCI6MTcxNzIyMjM0NywiaWF0IjoxNzE2MzU4MzQ3fQ.oCEUD3vSTv03jEvBAM67vrgN54vu6JyR5vyaPQSCMDI",
									"type": "string"
								}
							]
						},
						"method": "PUT",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\r\n  \"name\": \"string\",\r\n  \"description\": \"string\",\r\n  \"price\": 0,\r\n  \"available\": true,\r\n  \"allergenen\": [\r\n    {\r\n      \"naam\": \"string\"\r\n    }\r\n  ]\r\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/menuItems/all/{{id}}/block",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"menuItems",
								"all",
								"{{id}}",
								"block"
							]
						}
					},
					"response": []
				},
				{
					"name": "vraag alle menus op",
					"protocolProfileBehavior": {
						"disableBodyPruning": true
					},
					"request": {
						"auth": {
							"type": "bearer",
							"bearer": [
								{
									"key": "token",
									"value": "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiYWNrZW5kcmVzdGF1cmFudCIsInN1YiI6IjE6OlJlbW9uIiwicm9sZXMiOlsiUk9MRV9CQUFTIl0sImV4cCI6MTcxNzIyMjM0NywiaWF0IjoxNzE2MzU4MzQ3fQ.oCEUD3vSTv03jEvBAM67vrgN54vu6JyR5vyaPQSCMDI",
									"type": "string"
								}
							]
						},
						"method": "GET",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\r\n  \"name\": \"string\",\r\n  \"description\": \"string\",\r\n  \"price\": 0,\r\n  \"available\": true,\r\n  \"allergenen\": [\r\n    {\r\n      \"naam\": \"string\"\r\n    }\r\n  ]\r\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/menuItems/all",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"menuItems",
								"all"
							]
						}
					},
					"response": []
				},
				{
					"name": "vraag allergie op",
					"protocolProfileBehavior": {
						"disableBodyPruning": true
					},
					"request": {
						"auth": {
							"type": "bearer",
							"bearer": [
								{
									"key": "token",
									"value": "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiYWNrZW5kcmVzdGF1cmFudCIsInN1YiI6IjE6OlJlbW9uIiwicm9sZXMiOlsiUk9MRV9CQUFTIl0sImV4cCI6MTcxNzIyMjM0NywiaWF0IjoxNzE2MzU4MzQ3fQ.oCEUD3vSTv03jEvBAM67vrgN54vu6JyR5vyaPQSCMDI",
									"type": "string"
								}
							]
						},
						"method": "GET",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\r\n  \"menuItemId\": 1,\r\n  \"allergens\": [\r\n    \"gluten\"\r\n  ]\r\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/menuItems/all/checkAllergies",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"menuItems",
								"all",
								"checkAllergies"
							]
						}
					},
					"response": []
				}
			]
		},
		{
			"name": "Drank",
			"item": [
				{
					"name": "vraag drank aan op id",
					"request": {
						"auth": {
							"type": "bearer",
							"bearer": [
								{
									"key": "token",
									"value": "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiYWNrZW5kcmVzdGF1cmFudCIsInN1YiI6IjE6OlJlbW9uIiwicm9sZXMiOlsiUk9MRV9CQUFTIl0sImV4cCI6MTcxNzIyMjM0NywiaWF0IjoxNzE2MzU4MzQ3fQ.oCEUD3vSTv03jEvBAM67vrgN54vu6JyR5vyaPQSCMDI",
									"type": "string"
								}
							]
						},
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/drinks/{{id}}",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"drinks",
								"{{id}}"
							]
						}
					},
					"response": []
				},
				{
					"name": "bewerk drank op id",
					"request": {
						"auth": {
							"type": "bearer",
							"bearer": [
								{
									"key": "token",
									"value": "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiYWNrZW5kcmVzdGF1cmFudCIsInN1YiI6IjE6OlJlbW9uIiwicm9sZXMiOlsiUk9MRV9CQUFTIl0sImV4cCI6MTcxNzIyMjM0NywiaWF0IjoxNzE2MzU4MzQ3fQ.oCEUD3vSTv03jEvBAM67vrgN54vu6JyR5vyaPQSCMDI",
									"type": "string"
								}
							]
						},
						"method": "PUT",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\r\n  \"id\": 1,\r\n  \"naam\": \"fanta\",\r\n  \"prijs\": 0\r\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/drinks/{{id}}",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"drinks",
								"{{id}}"
							]
						}
					},
					"response": []
				},
				{
					"name": "verwijder drank op id",
					"request": {
						"auth": {
							"type": "bearer",
							"bearer": [
								{
									"key": "token",
									"value": "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiYWNrZW5kcmVzdGF1cmFudCIsInN1YiI6IjE6OlJlbW9uIiwicm9sZXMiOlsiUk9MRV9CQUFTIl0sImV4cCI6MTcxNzIyMjM0NywiaWF0IjoxNzE2MzU4MzQ3fQ.oCEUD3vSTv03jEvBAM67vrgN54vu6JyR5vyaPQSCMDI",
									"type": "string"
								}
							]
						},
						"method": "DELETE",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/drinks/{{id}}",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"drinks",
								"{{id}}"
							]
						}
					},
					"response": []
				},
				{
					"name": "Voeg drank toe aan database",
					"request": {
						"auth": {
							"type": "bearer",
							"bearer": [
								{
									"key": "token",
									"value": "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiYWNrZW5kcmVzdGF1cmFudCIsInN1YiI6IjE6OlJlbW9uIiwicm9sZXMiOlsiUk9MRV9CQUFTIl0sImV4cCI6MTcxNzIyMjM0NywiaWF0IjoxNzE2MzU4MzQ3fQ.oCEUD3vSTv03jEvBAM67vrgN54vu6JyR5vyaPQSCMDI",
									"type": "string"
								}
							]
						},
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\r\n  \"naam\": \"string\",\r\n  \"type\": \"string\",\r\n  \"prijs\": 0\r\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/drinks",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"drinks"
							]
						}
					},
					"response": []
				},
				{
					"name": "vraag alle dranken op",
					"request": {
						"auth": {
							"type": "bearer",
							"bearer": [
								{
									"key": "token",
									"value": "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiYWNrZW5kcmVzdGF1cmFudCIsInN1YiI6IjE6OlJlbW9uIiwicm9sZXMiOlsiUk9MRV9CQUFTIl0sImV4cCI6MTcxNzIyMjM0NywiaWF0IjoxNzE2MzU4MzQ3fQ.oCEUD3vSTv03jEvBAM67vrgN54vu6JyR5vyaPQSCMDI",
									"type": "string"
								}
							]
						},
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/drinks",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"drinks"
							]
						}
					},
					"response": []
				}
			]
		},
		{
			"name": "Bon",
			"item": [
				{
					"name": "verander bon dat die betaald is",
					"request": {
						"auth": {
							"type": "bearer",
							"bearer": [
								{
									"key": "token",
									"value": "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiYWNrZW5kcmVzdGF1cmFudCIsInN1YiI6IjE6OlJlbW9uIiwicm9sZXMiOlsiUk9MRV9CQUFTIl0sImV4cCI6MTcxNzIyMjM0NywiaWF0IjoxNzE2MzU4MzQ3fQ.oCEUD3vSTv03jEvBAM67vrgN54vu6JyR5vyaPQSCMDI",
									"type": "string"
								}
							]
						},
						"method": "PUT",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/bonnen/{{id}}/betaald",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"bonnen",
								"{{id}}",
								"betaald"
							]
						}
					},
					"response": []
				},
				{
					"name": "vraag alle bonnen op",
					"request": {
						"auth": {
							"type": "bearer",
							"bearer": [
								{
									"key": "token",
									"value": "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiYWNrZW5kcmVzdGF1cmFudCIsInN1YiI6IjE6OlJlbW9uIiwicm9sZXMiOlsiUk9MRV9CQUFTIl0sImV4cCI6MTcxNzIyMjA2MiwiaWF0IjoxNzE2MzU4MDYyfQ.k6Vg2Uo_-_2PwvECUQboWJLZhQZzYgCB-Ixv_cTDiP0",
									"type": "string"
								}
							]
						},
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/bonnen",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"bonnen"
							]
						}
					},
					"response": []
				},
				{
					"name": "vraag bon per tafel id",
					"request": {
						"auth": {
							"type": "bearer",
							"bearer": [
								{
									"key": "token",
									"value": "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiYWNrZW5kcmVzdGF1cmFudCIsInN1YiI6IjE6OlJlbW9uIiwicm9sZXMiOlsiUk9MRV9CQUFTIl0sImV4cCI6MTcxNzIyMjM0NywiaWF0IjoxNzE2MzU4MzQ3fQ.oCEUD3vSTv03jEvBAM67vrgN54vu6JyR5vyaPQSCMDI",
									"type": "string"
								}
							]
						},
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/bonnen/{{id}}",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"bonnen",
								"{{id}}"
							]
						}
					},
					"response": []
				}
			]
		},
		{
			"name": "Bestelling",
			"item": [
				{
					"name": "bestelling veranderen",
					"request": {
						"method": "PUT",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\r\n  \"tafelNaam\": \"ayla\",\r\n  \"itemName\": \"Mediterraans Landbrood\",\r\n  \"hoeveelheid\": 100\r\n}\r\n",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/bestelling/update-bestelling",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"bestelling",
								"update-bestelling"
							]
						}
					},
					"response": []
				},
				{
					"name": "bestelling tafel verplaatsen",
					"request": {
						"auth": {
							"type": "bearer",
							"bearer": [
								{
									"key": "token",
									"value": "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiYWNrZW5kcmVzdGF1cmFudCIsInN1YiI6IjE6OlJlbW9uIiwicm9sZXMiOlsiUk9MRV9CQUFTIl0sImV4cCI6MTcxNzIyMjM0NywiaWF0IjoxNzE2MzU4MzQ3fQ.oCEUD3vSTv03jEvBAM67vrgN54vu6JyR5vyaPQSCMDI",
									"type": "string"
								}
							]
						},
						"method": "PUT",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\r\n  \"tafelNaam\": \"string\",\r\n  \"nieuweNaam\": \"string\"\r\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/bestelling/tafel-verplaatsen",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"bestelling",
								"tafel-verplaatsen"
							]
						}
					},
					"response": []
				},
				{
					"name": "Bestelling plaatsen",
					"request": {
						"auth": {
							"type": "bearer",
							"bearer": [
								{
									"key": "token",
									"value": "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiYWNrZW5kcmVzdGF1cmFudCIsInN1YiI6IjE6OlJlbW9uIiwicm9sZXMiOlsiUk9MRV9CQUFTIl0sImV4cCI6MTcxNzIyMjM0NywiaWF0IjoxNzE2MzU4MzQ3fQ.oCEUD3vSTv03jEvBAM67vrgN54vu6JyR5vyaPQSCMDI",
									"type": "string"
								}
							]
						},
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\r\n  \"tafelNaam\": \"pieter\",\r\n  \"besteldeMenuItems\": [\r\n    {\r\n      \"itemNaam\": \"Mediterraans Landbrood\",\r\n      \"hoeveelheid\": 3\r\n    }\r\n  ],\r\n  \"besteldeDranken\": [\r\n    {\r\n      \"itemNaam\": \"Cola\",\r\n      \"hoeveelheid\": 1\r\n    },\r\n    {\r\n      \"itemNaam\": \"Fanta\",\r\n      \"hoeveelheid\": 2\r\n    }\r\n  ]\r\n}\r\n",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/bestelling/plaatsen",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"bestelling",
								"plaatsen"
							]
						}
					},
					"response": []
				},
				{
					"name": "bestelling opvragen",
					"request": {
						"auth": {
							"type": "bearer",
							"bearer": [
								{
									"key": "token",
									"value": "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiYWNrZW5kcmVzdGF1cmFudCIsInN1YiI6IjE6OlJlbW9uIiwicm9sZXMiOlsiUk9MRV9CQUFTIl0sImV4cCI6MTcxNzIyMjM0NywiaWF0IjoxNzE2MzU4MzQ3fQ.oCEUD3vSTv03jEvBAM67vrgN54vu6JyR5vyaPQSCMDI",
									"type": "string"
								}
							]
						},
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/bestelling/{{id}}",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"bestelling",
								"{{id}}"
							]
						}
					},
					"response": []
				}
			]
		},
		{
			"name": "Allergie",
			"item": [
				{
					"name": "vraag allergie per id op",
					"request": {
						"auth": {
							"type": "bearer",
							"bearer": [
								{
									"key": "token",
									"value": "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiYWNrZW5kcmVzdGF1cmFudCIsInN1YiI6IjE6OlJlbW9uIiwicm9sZXMiOlsiUk9MRV9CQUFTIl0sImV4cCI6MTcxNzIyMjM0NywiaWF0IjoxNzE2MzU4MzQ3fQ.oCEUD3vSTv03jEvBAM67vrgN54vu6JyR5vyaPQSCMDI",
									"type": "string"
								}
							]
						},
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/allergies/{{id}}",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"allergies",
								"{{id}}"
							]
						}
					},
					"response": []
				},
				{
					"name": "vraag alle allergieen op",
					"request": {
						"auth": {
							"type": "bearer",
							"bearer": [
								{
									"key": "token",
									"value": "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiYWNrZW5kcmVzdGF1cmFudCIsInN1YiI6IjE6OlJlbW9uIiwicm9sZXMiOlsiUk9MRV9CQUFTIl0sImV4cCI6MTcxNzIyMjM0NywiaWF0IjoxNzE2MzU4MzQ3fQ.oCEUD3vSTv03jEvBAM67vrgN54vu6JyR5vyaPQSCMDI",
									"type": "string"
								}
							]
						},
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/allergies",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"allergies"
							]
						}
					},
					"response": []
				},
				{
					"name": "update allergie",
					"request": {
						"auth": {
							"type": "bearer",
							"bearer": [
								{
									"key": "token",
									"value": "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiYWNrZW5kcmVzdGF1cmFudCIsInN1YiI6IjE6OlJlbW9uIiwicm9sZXMiOlsiUk9MRV9CQUFTIl0sImV4cCI6MTcxNzIyMjM0NywiaWF0IjoxNzE2MzU4MzQ3fQ.oCEUD3vSTv03jEvBAM67vrgN54vu6JyR5vyaPQSCMDI",
									"type": "string"
								}
							]
						},
						"method": "PUT",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\r\n    \"id\": \"1\",\r\n    \"naam\": \"Nieuwe Allergie Naam\"\r\n}\r\n",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/allergies/{{id}}",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"allergies",
								"{{id}}"
							]
						}
					},
					"response": []
				}
			]
		},
		{
			"name": "Newsletter",
			"item": [
				{
					"name": "download nieuwsbrief",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/newsletter/download/latest",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"newsletter",
								"download",
								"latest"
							]
						}
					},
					"response": []
				},
				{
					"name": "uploaden van nieuwsbrief",
					"request": {
						"auth": {
							"type": "bearer",
							"bearer": [
								{
									"key": "token",
									"value": "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiYWNrZW5kcmVzdGF1cmFudCIsInN1YiI6IjE6OlJlbW9uIiwicm9sZXMiOlsiUk9MRV9CQUFTIl0sImV4cCI6MTcxMjQwNjIxMywiaWF0IjoxNzExNTQyMjEzfQ.g806ZK3fiABZiU2yKwPrADsZhh4GnVg05w2ZwAL3-1w",
									"type": "string"
								}
							]
						},
						"method": "POST",
						"header": [],
						"body": {
							"mode": "formdata",
							"formdata": [
								{
									"key": "nieuwsbrief",
									"type": "file",
									"src": "/C:/Users/cross/Downloads/nieuwsbrief.pdf"
								}
							]
						},
						"url": {
							"raw": "http://localhost:8080/newsletter/upload",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"newsletter",
								"upload"
							]
						}
					},
					"response": []
				}
			]
		},
		{
			"name": "authenticatie",
			"item": [
				{
					"name": "user aanmaken",
					"request": {
						"auth": {
							"type": "bearer",
							"bearer": [
								{
									"key": "token",
									"value": "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiYWNrZW5kcmVzdGF1cmFudCIsInN1YiI6IjE6OlJlbW9uIiwicm9sZXMiOlsiUk9MRV9CQUFTIl0sImV4cCI6MTcxMjQwMzI4NywiaWF0IjoxNzExNTM5Mjg3fQ.dIecRX7JRb2a9ntbDuxcA8GONwu6I5KmSN-JTDdDtPM",
									"type": "string"
								}
							]
						},
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\r\n  \"userName\": \"jodi\",\r\n  \"password\": \"Novi123\",\r\n  \"roles\": [\"ROLE_SERVEERSTERS\"]\r\n}\r\n",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/users",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"users"
							]
						}
					},
					"response": []
				},
				{
					"name": "Login",
					"event": [
						{
							"listen": "test",
							"script": {
								"exec": [
									"pm.test(\"Successful POST request\", function () {",
									"    pm.expect(pm.response.code).to.be.oneOf([200, 201]);",
									"});",
									""
								],
								"type": "text/javascript",
								"packages": {}
							}
						}
					],
					"request": {
						"auth": {
							"type": "bearer",
							"bearer": [
								{
									"key": "token",
									"value": "",
									"type": "string"
								}
							]
						},
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\n    \"userName\": \"Remon\",\n    \"password\": \"AylaRemon05!\"\n}\n",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/login",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"login"
							]
						},
						"description": "This is a POST request, submitting data to an API via the request body. This request submits JSON data, and the data is reflected in the response.\n\nA successful POST request typically returns a `200 OK` or `201 Created` response code."
					},
					"response": []
				}
			]
		}
	],
	"event": [
		{
			"listen": "prerequest",
			"script": {
				"type": "text/javascript",
				"exec": [
					""
				]
			}
		},
		{
			"listen": "test",
			"script": {
				"type": "text/javascript",
				"exec": [
					""
				]
			}
		}
	],
	"variable": [
		{
			"key": "id",
			"value": "1"
		},
		{
			"key": "base_url",
			"value": "https://postman-rest-api-learner.glitch.me/"
		},
		{
			"key": "server",
			"value": "http://localhost:8080/"
		}
	]
}ing Backend.postman_collectionv3…]()






#### Opmerkingen:

- Zorg ervoor dat de poort 8080 op jouw systeem beschikbaar is voor de Spring Boot applicatie.
- Voor productie-omgevingen wordt aangeraden om de applicatie te implementeren op een veilige en schaalbare infrastructuur, zoals een cloudplatform.
