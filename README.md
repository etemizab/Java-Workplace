Pet Classifier - Java Web Application
Java
Spring Boot
Docker
Azure

Eine KI-basierte Webanwendung zur Klassifizierung von Haustierbildern (Hunde/Katzen) mit Java, Maven und Docker, deployed auf Azure.

Live-Demo: https://petclassifier1-h7apb0fdfsd7f7gt.southindia-01.azurewebsites.net/
Daten kopiert von Kaggle: https://www.kaggle.com/c/dogs-vs-cats/data

Inhaltsverzeichnis
1. Funktionen
2. Technologien
3. Voraussetzungen
4. Installation
5. Lokal ausführen
6. Mit Docker ausführen
7. Azure Deployment
8. API-Endpunkte
9. Beitragen
10. Lizenz

1. Funktionen
  -  Upload von Haustierbildern
  -  KI-Modell zur Klassifizierung (Hund/Katze)

  -  Wahrscheinlichkeitsanzeige in %

  -  Beschreibungen der erkannten Rassen

  -  Docker-Container-Unterstützung

  -  Deployment auf Azure Web App


2. Technologien
Backend:

Java 17

Spring Boot 3.1

Maven

Frontend:

HTML/CSS/JS

Bootstrap 5

KI-Modell:

TensorFlow (über Model Asset Exchange)

Infrastruktur:

Docker

Azure Container Registry (ACR)

Azure App Service

3. Voraussetzungen
JDK 17+

Maven 3.8+

Docker (optional)

Azure CLI (für Deployment)

4. Installation
5 . Lokal ausführen
bash
# Klone das Repository
git clone https://github.com/etemizab/Java-Workplace.git
cd Java-Workplace/pet-classifier

# Baue die Anwendung
mvn clean package

# Starte den Server
java -jar target/petclassifier-0.0.1-SNAPSHOT.jar
→ Öffne http://localhost:8080

6. Mit Docker
bash
# Baue das Image
docker build -t petclassifier .

# Führe den Container aus
docker run -p 8080:8080 petclassifier
☁️ 7. Azure Deployment
Das Projekt wurde als Docker-Container auf Azure bereitgestellt:

Schritte:
Image in Azure Container Registry (ACR) pushen:

bash
az acr login --name <ACR-NAME>
docker tag petclassifier <ACR-NAME>.azurecr.io/petclassifier:latest
docker push <ACR-NAME>.azurecr.io/petclassifier:latest
Azure Web App erstellen:

bash
az webapp create --resource-group Javapetclassifier \
                 --plan myAppServicePlan \
                 --name petclassifier1 \
                 --deployment-container-image-name <ACR-NAME>.azurecr.io/petclassifier:latest
WEBSITES_PORT setzen (falls nicht 80):

bash
az webapp config appsettings set --name petclassifier1 \
                                --resource-group Javapetclassifier \
                                --settings WEBSITES_PORT=8080
Live-URL: https://petclassifier1-h7apb0fdfsd7f7gt.southindia-01.azurewebsites.net/

8. API-Endpunkte
Endpoint	Methode	Beschreibung
/analyze	POST	Bildanalyse (multipart/form-data)
/labels	GET	Liste aller erkennbaren Labels
/models	GET	Verfügbare KI-Modelle
9. Beitragen
Fork das Repository

Erstelle einen Feature-Branch (git checkout -b feature/AmazingFeature)

Commite deine Änderungen (git commit -m 'Add some AmazingFeature')

Pushe den Branch (git push origin feature/AmazingFeature)

Öffne einen Pull Request

10. Lizenz
MIT

📸 Screenshots
![image](https://github.com/user-attachments/assets/69ddefca-3f0b-4204-981d-100249a1d0f6)
![image](https://github.com/user-attachments/assets/6af8fd48-7105-415a-9c59-e60a41e9c478)

 Hinweis: Bei Problemen mit dem Azure-Deployment siehe Troubleshooting Guide.
