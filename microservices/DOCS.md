# Documentation des Microservices

## Objectifs du Projet

Ce projet a pour but de démontrer la communication entre microservices en utilisant les protocoles REST et SOAP. Les microservices sont conçus pour être autonomes, maintenables et évolutifs, tout en communiquant efficacement entre eux.

### Concepts de Microservices

Un microservice est une application autonome qui gère un domaine métier spécifique et communique avec d’autres services via des protocoles légers comme REST ou SOAP.

**Avantages :**

- Isolation, scalabilité, maintenabilité.
- Déploiement indépendant.
- Requiert une communication bien structurée.

---

## Structure des Microservices

### 1. Product-Service

**Description :** Ce microservice gère les produits.

- **Port :** 8081
- **Modèle :** `Product`

  ```java
  public class Product {
      private Long id;
      private String name;
      private double price;
      // Getters, setters, constructeurs
  }
  ```

- **Contrôleur :** `ProductController`

  ```java
  @RestController
  @RequestMapping("/products")
  public class ProductController {
      @GetMapping("/{id}")
      public Product getProduct(@PathVariable Long id) {
          return new Product(id, "Chaise", 49.99);
      }
  }
  ```

### 2. Order-Service

**Description :** Ce microservice gère les commandes et communique avec `product-service` pour récupérer les informations produit.

- **Port :** 8082
- **Configuration de RestTemplate :**

  ```java
  @Bean
  public RestTemplate restTemplate() {
      return new RestTemplate();
  }
  ```

- **Contrôleur :** `OrderController`

  ```java
  @RestController
  @RequestMapping("/orders")
  public class OrderController {
      private final RestTemplate restTemplate;

      public OrderController(RestTemplate restTemplate) {
          this.restTemplate = restTemplate;
      }

      @GetMapping("/create/{productId}")
      public Map<String, Object> createOrder(@PathVariable Long productId) {
          Product product = restTemplate.getForObject(
              "http://localhost:8081/products/" + productId, Product.class);

          return Map.of(
              "status", "Commande créée",
              "product", product
          );
      }
  }
  ```

### 3. Shared-Soap-Models

**Description :** Ce module contient les modèles partagés pour les services SOAP. Il est utilisé pour générer et partager des classes Java à partir de schémas XML (XSD) entre différents microservices.

- **Utilisation :**
  - Les classes générées sont utilisées par les services SOAP pour sérialiser et désérialiser les messages XML.
  - Permet de centraliser les définitions des modèles pour garantir la cohérence entre les services.

- **Exemple de configuration Maven :**

  ```xml
  <plugin>
      <groupId>org.codehaus.mojo</groupId>
      <artifactId>jaxb2-maven-plugin</artifactId>
      <version>2.5.0</version>
      <executions>
          <execution>
              <goals>
                  <goal>xjc</goal>
              </goals>
          </execution>
      </executions>
      <configuration>
          <schemaDirectory>src/main/resources/xsd</schemaDirectory>
          <outputDirectory>src/main/java</outputDirectory>
      </configuration>
  </plugin>
  ```

### 4. Dépendance Locale : Shared-Soap-Models

**Description :**

`shared-soap-models` est une dépendance locale développée en interne. Contrairement aux dépendances externes téléchargées depuis des dépôts publics comme Maven Central, une dépendance locale est un module ou projet développé par l'équipe et utilisé dans d'autres projets du même environnement.

**Avantages :**

- **Cohérence :** Les modèles partagés garantissent que les services SOAP utilisent les mêmes définitions de données.
- **Réutilisabilité :** Le code commun est centralisé, évitant la duplication dans chaque microservice.
- **Facilité de maintenance :** Toute modification dans les modèles partagés est immédiatement disponible pour tous les services qui utilisent cette dépendance.

**Intégration dans les Microservices :**

1. **Développement :**
   - `shared-soap-models` contient les classes générées à partir des fichiers XSD (schémas XML).
   - Ces classes sont utilisées pour sérialiser et désérialiser les messages SOAP.

2. **Utilisation dans les Microservices :**

   - Les microservices `product-service` et `order-service` incluent `shared-soap-models` comme dépendance dans leur fichier `pom.xml`.
   - Exemple dans `order-service` :

     ```xml
     <dependency>
         <groupId>com.example</groupId>
         <artifactId>shared-soap-models</artifactId>
         <version>0.0.1-SNAPSHOT</version>
     </dependency>
     ```

3. **Installation Locale :**

   - Avant d'utiliser `shared-soap-models` dans d'autres projets, il doit être installé dans le dépôt local Maven.
   - Commande pour installer :

     ```bash
     mvn install -f shared-soap-models/pom.xml
     ```

   - Cette commande compile le projet et l'ajoute au dépôt local Maven (généralement situé dans `~/.m2/repository`).

**Pourquoi utiliser une dépendance locale ?**

- Lorsque plusieurs projets nécessitent des modèles ou des utilitaires communs, il est plus efficace de les centraliser dans un module partagé.
- Cela permet de maintenir une seule source de vérité pour les définitions de données ou les fonctionnalités communes.

**Conclusion :**

`shared-soap-models` joue un rôle clé dans la communication SOAP entre `product-service` et `order-service`. En centralisant les modèles partagés, il garantit la cohérence et simplifie la maintenance des microservices.

---

### 5. Choix du Nom de Groupe (`groupId`)

**Description :**

Le `groupId` est un identifiant unique utilisé dans Maven pour organiser et différencier les artefacts. Dans ce projet, nous avons utilisé `com.example` comme `groupId` pour les trois services (`product-service`, `order-service`, et `shared-soap-models`).

**Points Clés :**

- **Personnalisation :** Bien que `com.example` soit suffisant pour un projet local, il est recommandé d'utiliser un `groupId` spécifique à votre organisation ou projet, comme `com.mycompany.microservices`.
- **Cohérence :** Tous les services partagent le même `groupId`, ce qui indique qu'ils appartiennent au même domaine ou projet.
- **Interopérabilité :** Si vous publiez vos artefacts dans un dépôt partagé (comme Nexus ou Artifactory), un `groupId` unique garantit qu'il n'y aura pas de conflit avec d'autres artefacts.

**Exemple de Modification :**

Pour personnaliser le `groupId`, vous pouvez modifier les fichiers `pom.xml` de chaque service. Par exemple, dans `shared-soap-models/pom.xml` :

```xml
<groupId>com.mycompany.microservices</groupId>
```

Ensuite, mettez à jour les dépendances dans les autres services pour refléter ce changement.

**Conclusion :**

Le choix d'un `groupId` clair et spécifique est essentiel pour organiser vos artefacts Maven, surtout si vous travaillez sur des projets collaboratifs ou à grande échelle.

---

### 6. Fonctionnement du Dépôt Local Maven

**Description :**

Les services trouvent la dépendance `shared-soap-models` grâce au dépôt local Maven. Voici comment cela fonctionne :

**Installation dans le Dépôt Local Maven :**

- Lorsque vous exécutez la commande `mvn install` dans le projet `shared-soap-models`, Maven compile le projet et place l'artefact (fichier JAR) dans le dépôt local Maven, généralement situé dans `~/.m2/repository`.
- L'artefact est identifié par son `groupId` (`com.example`), son `artifactId` (`shared-soap-models`), et sa `version` (`0.0.1-SNAPSHOT`).

**Référencement dans les Autres Projets :**

- Les microservices `product-service` et `order-service` incluent cette dépendance dans leur fichier `pom.xml` en utilisant les mêmes `groupId`, `artifactId`, et `version`.
- Maven recherche d'abord dans le dépôt local (`~/.m2/repository`) pour trouver l'artefact correspondant.

**Pourquoi `com.example` ?**

- Le `groupId` (`com.example`) est une convention pour identifier de manière unique les artefacts. Dans ce cas, il est utilisé pour indiquer que l'artefact est développé en interne.
- Ce `groupId` n'est pas lié à un dépôt public comme Maven Central, mais il est suffisant pour le dépôt local.

**Pas Besoin de Dépôt Externe :**

- Tant que les projets sont sur la même machine ou partagent le même dépôt local Maven, il n'est pas nécessaire de publier l'artefact sur un dépôt externe.
- Si vous souhaitez partager cette dépendance avec d'autres développeurs ou machines, vous pouvez envisager de configurer un dépôt Maven privé (comme Nexus ou Artifactory) pour centraliser les artefacts internes.

---

## Communication REST

- `order-service` utilise `RestTemplate` pour appeler l'API REST de `product-service`.
- Exemple d'appel :

  ```bash
  curl http://localhost:8082/orders/create/1
  ```

---

## Introduction à SOAP

**SOAP** est un protocole basé sur XML, encore utilisé dans certaines architectures (notamment bancaires).

- **Dépendance Maven :**

  ```xml
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web-services</artifactId>
  </dependency>
  ```

- **Exemple de classe SOAP Endpoint :**

  ```java
  @Endpoint
  public class ProductEndpoint {
      @PayloadRoot(namespace = "http://example.com/product", localPart = "GetProductRequest")
      @ResponsePayload
      public GetProductResponse getProduct(@RequestPayload GetProductRequest request) {
          GetProductResponse response = new GetProductResponse();
          response.setProduct(...); // Set les données
          return response;
      }
  }
  ```

---

## Comparaison REST vs SOAP

| Critère       | REST          | SOAP          |
|---------------|---------------|---------------|
| **Format**    | JSON, XML     | XML uniquement |
| **Flexibilité** | Élevée        | Faible         |
| **Complexité** | Simple        | Plus lourd     |
| **Cas d’usage** | Web, mobile, APIs modernes | Intégration d’entreprise |

---

## Exercices Pratiques

### 🎯 Exercice 1 : Communication REST

1. Appeler `product-service` depuis `order-service` via `RestTemplate`.
2. Afficher les informations produit dans une commande simulée.

### 🎯 Exercice 2 : Appel SOAP (Optionnel)

1. Créer un endpoint SOAP qui retourne un produit.
2. Créer un client SOAP avec Spring Boot ou SoapUI.

---

## Récapitulatif

✅ Création de deux microservices REST.
✅ Appel d’un microservice depuis un autre avec `RestTemplate`.
✅ Découverte de SOAP et du protocole XML.
✅ Comparaison entre REST et SOAP.
✅ Communication simulée entre services Spring Boot.
