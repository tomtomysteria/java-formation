# Microservices Project

Ce projet démontre la communication entre microservices en utilisant Spring Cloud, Eureka, et une API Gateway. Il inclut plusieurs services indépendants qui collaborent pour fournir une architecture distribuée robuste.

## Stack Technique

### 1. **Eureka Server**

- **Rôle :** Serveur de découverte pour enregistrer et localiser les microservices.
- **Technologies :** Spring Cloud Netflix Eureka.
- **Port :** 8761.

### 2. **Product-Service**

- **Rôle :** Gère les produits.
- **Technologies :** Spring Boot, Eureka Client.
- **Port :** 8081.

### 3. **Order-Service**

- **Rôle :** Gère les commandes et communique avec `product-service` pour récupérer les informations produit.
- **Technologies :** Spring Boot, Eureka Client, RestTemplate.
- **Port :** 8082.

### 4. **API Gateway**

- **Rôle :** Point d'entrée unique pour toutes les requêtes vers les microservices.
- **Technologies :** Spring Cloud Gateway, Eureka Client.
- **Port :** 8080.

### 5. **Shared-Soap-Models**

- **Rôle :** Contient les modèles partagés pour les services SOAP.
- **Technologies :** JAXB, Maven.

## Installation et Exécution

### Prérequis

- **Java 17** ou version supérieure.
- **Maven** installé.
- **Ports requis :** 8761, 8080, 8081, et 8082 disponibles.

### Étapes

1. **Nettoyer le projet**

   ```bash
   mvn clean
   ```

2. **Installer les dépendances locales**

   ```bash
   mvn install -f shared-soap-models/pom.xml
   ```

3. **Démarrer les services**
   - **Eureka Server** :

     ```bash
     mvn spring-boot:run -f eureka-server/pom.xml
     ```

   - **Product-Service** :

     ```bash
     mvn spring-boot:run -f product-service/pom.xml
     ```

   - **Order-Service** :

     ```bash
     mvn spring-boot:run -f order-service/pom.xml
     ```

   - **API Gateway** :

     ```bash
     mvn spring-boot:run -f api-gateway/pom.xml
     ```

4. **Tester les services**
   - Accéder à Eureka Dashboard : [http://localhost:8761](http://localhost:8761)
   - Tester les endpoints via l'API Gateway :
     - `/products`
     - `/orders`

## Documentation Technique

Pour des détails approfondis sur la configuration de Log4j2, l'intégration de SonarQube, et d'autres aspects techniques, consultez le fichier [DOCS.md](./DOCS.md).
