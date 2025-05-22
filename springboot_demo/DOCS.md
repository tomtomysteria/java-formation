# Documentation du projet Spring Boot Demo

## Introduction

Le projet **Spring Boot Demo** est une application backend conçue pour démontrer l'utilisation de Spring Boot avec des fonctionnalités d'authentification basées sur JWT et de gestion des utilisateurs via une base de données PostgreSQL.

### Technologies principales

- **Spring Boot** : Framework pour construire des applications backend robustes et maintenables.
- **Spring Security** : Pour gérer l'authentification et l'autorisation.
- **JWT (JSON Web Tokens)** : Pour l'authentification stateless.
- **PostgreSQL** : Base de données relationnelle pour stocker les utilisateurs.
- **JPA (Java Persistence API)** : Pour la gestion des entités et des opérations sur la base de données.

---

## Authentification et sécurité (Structure du projet)

### 1. **SecurityConfig**

**Rôle :** Configure la sécurité globale de l'application.

**Fonctionnalités principales :**

- Définit les règles d'accès aux différentes routes (`/auth/**`, `/admin/**`, `/users/**`, etc.).
- Désactive la protection CSRF (non nécessaire pour une API REST stateless).
- Configure la gestion des sessions en mode `STATELESS` (aucune session côté serveur, tout repose sur les JWT).
- Ajoute un filtre personnalisé (`JwtRequestFilter`) pour valider les JWT avant l'authentification.
- Fournit un `UserDetailsService` pour charger les utilisateurs depuis la base de données.
- Définit un encodeur de mots de passe (`BCryptPasswordEncoder`) pour sécuriser les mots de passe.

### 2. **AuthController**

**Rôle :** Gère l'authentification des utilisateurs.

**Fonctionnalités principales :**

- Expose une route `/auth/login` pour permettre aux utilisateurs de se connecter.
- Vérifie les identifiants fournis (nom d'utilisateur et mot de passe) en les comparant avec ceux stockés dans la base de données.
- Génère un JWT si les identifiants sont valides.
- Retourne le JWT au client pour qu'il puisse l'utiliser dans les requêtes futures.

### 3. **JwtUtil**

**Rôle :** Gère la création et la validation des JWT.

**Fonctionnalités principales :**

- Génère un JWT avec une clé secrète et une durée d'expiration.
- Extrait le nom d'utilisateur d'un JWT.
- Valide un JWT en vérifiant sa signature et sa date d'expiration.

### 4. **JwtRequestFilter**

**Rôle :** Intercepte les requêtes HTTP pour valider les JWT.

**Fonctionnalités principales :**

- Extrait le JWT de l'en-tête `Authorization` des requêtes.
- Valide le JWT et charge les détails de l'utilisateur correspondant.
- Ajoute les informations d'authentification au contexte de sécurité de Spring.

### 5. **UserRepository**

**Rôle :** Fournit un accès à la base de données pour les entités `User`.

**Fonctionnalités principales :**

- Permet de rechercher un utilisateur par son nom d'utilisateur (`findByUsername`).
- Hérite de `JpaRepository`, ce qui offre des méthodes CRUD pour gérer les utilisateurs.

### 6. **User (Entité)**

**Rôle :** Représente un utilisateur dans la base de données.

**Fonctionnalités principales :**

- Définit les champs `id`, `username`, `password` et `role`.
- Utilise l'annotation `@Table(name = "app_user")` pour éviter les conflits avec le mot-clé réservé `user` dans PostgreSQL.

### 7. **Base de données**

**Rôle :** Stocke les informations des utilisateurs.

**Fonctionnalités principales :**

- Contient une table `app_user` avec les colonnes `id`, `username`, `password` et `role`.
- Les mots de passe sont encodés avec `BCryptPasswordEncoder` pour garantir leur sécurité.

### 8. **Configuration de la base de données**

L'application utilise PostgreSQL comme base de données principale. Voici les étapes pour configurer la base de données :

1. Assurez-vous que PostgreSQL est installé et en cours d'exécution.
2. Créez une base de données nommée `spring_demo`.
3. Mettez à jour les informations de connexion dans `application.properties` si nécessaire :

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/spring_demo
   spring.datasource.username=my_username
   spring.datasource.password=my_password
   ```

### 9. **Flux d'authentification JWT**

1. **Connexion** :
   - L'utilisateur envoie une requête POST à `/auth/login` avec ses identifiants.
   - Un JWT est généré et retourné si les identifiants sont valides.

2. **Accès aux ressources protégées** :
   - Le client inclut le JWT dans l'en-tête `Authorization` des requêtes suivantes.
   - Le filtre `JwtRequestFilter` valide le JWT et charge les détails de l'utilisateur.

3. **Déconnexion** :
   - Bien que l'application soit stateless, le client peut simplement supprimer le JWT pour se déconnecter.

### 10. **Structure des contrôleurs**

- **AuthController** :
  - Route `/auth/login` : Authentification des utilisateurs.
  - Retourne un JWT en cas de succès.

- **UserController** :
  - Routes CRUD pour gérer les utilisateurs.

- **ProductController** :
  - Routes CRUD pour gérer les produits.

### 11. **Gestion des entités**

- **User** :
  - Champs : `id`, `username`, `password`, `role`.
  - Encodage des mots de passe avec `BCryptPasswordEncoder`.

- **Product** :
  - Champs : `id`, `name`, `price`, `category`.

- **Category** :
  - Champs : `id`, `name`.

### 12. **Dépôts**

- **UserRepository** :
  - Méthode `findByUsername` pour rechercher un utilisateur par son nom d'utilisateur.

- **ProductRepository** :
  - Méthodes CRUD pour gérer les produits.

---

## Tests Unitaires et Mocking

### Objectif et Avantages

Les tests unitaires permettent de vérifier que chaque composant de l'application fonctionne comme prévu. Le mocking permet d'isoler ces composants en simulant leurs dépendances.

### Outils Utilisés

- **JUnit 5** : Pour écrire et exécuter les tests.
- **Mockito** : Pour simuler les dépendances.
- **MockMvc** : Pour tester les contrôleurs Spring MVC.

### Exemples

#### Tests des Services

```java
@Test
void shouldCreateProductSuccessfully() {
    // Arrange
    Product product = new Product(PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_DESCRIPTION, PRODUCT_STOCK);
    when(productRepository.save(product)).thenReturn(product);

    // Act
    Product result = productService.createProduct(product);

    // Assert
    assertNotNull(result);
    assertEquals(PRODUCT_NAME, result.getName());
    verify(productRepository, times(1)).save(product);
    verifyNoMoreInteractions(productRepository);
}
```

#### Tests des Contrôleurs

```java
@Test
void shouldCreateProductSuccessfully() throws Exception {
    Product product = new Product(null, "Product B", 150.0);
    mockMvc.perform(post("/products")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(product)))
            .andExpect(status().isCreated());
}
```

### Bonnes Pratiques pour les Tests

- Écrire des tests pour tous les chemins critiques.
- Utiliser des noms de méthodes de test descriptifs.
- Simuler les dépendances externes pour isoler l'unité testée.

---

## Validation des Données

### Objectif

La validation garantit que les données fournies par les utilisateurs respectent les contraintes requises avant d'être traitées.

### Annotations Utilisées

- `@NotNull` : Assure que le champ n'est pas nul.
- `@Positive` : Assure que le champ a une valeur positive.
- `@Size` : Assure que le champ respecte les contraintes de taille.

### Exemple

#### Modèle Product

```java
public class Product {
    @NotNull
    private String name;

    @Positive
    private Double price;

    // Getters et setters
}
```

#### Validation dans le Contrôleur

```java
@PostMapping("/products")
public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
    Product createdProduct = productService.createProduct(product);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
}
```

### Bonnes Pratiques pour la Validation

- Utiliser des messages de validation significatifs.
- Valider les entrées aux frontières (par exemple, dans les contrôleurs).
- Tester les règles de validation avec des tests unitaires.

---

## Mocks en Tests Unitaires

### Qu'est-ce qu'un Mock ?

Un **mock** est un objet simulé utilisé dans les tests unitaires pour remplacer une dépendance réelle. Il permet de tester une unité de code (comme une méthode ou une classe) de manière isolée, sans avoir à dépendre de l'implémentation réelle des autres composants.

#### Pourquoi utiliser un mock ?

1. **Isolation** : Les mocks permettent de tester une unité de code sans dépendre des autres parties du système.
2. **Contrôle** : Ils permettent de contrôler le comportement des dépendances (par exemple, en simulant des retours spécifiques ou des exceptions).
3. **Performance** : Les mocks évitent d'utiliser des ressources réelles (comme une base de données ou un service externe), ce qui rend les tests plus rapides.
4. **Fiabilité** : Ils permettent de tester des scénarios difficiles à reproduire avec des dépendances réelles (comme des erreurs réseau).

#### Exemple avec Mockito

Supposons que vous testez un service qui dépend d'un dépôt (repository) pour accéder aux données.

##### Code réel

```java
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}
```

##### Test avec un mock

```java
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void shouldReturnProductById() {
        // Arrange
        Product mockProduct = new Product(1L, "Product A", 100.0);
        when(productRepository.findById(1L)).thenReturn(Optional.of(mockProduct));

        // Act
        Product result = productService.getProductById(1L);

        // Assert
        assertNotNull(result);
        assertEquals("Product A", result.getName());
        verify(productRepository, times(1)).findById(1L);
    }
}
```

#### Fonctionnement

1. **Création du mock** : `@Mock` crée un objet simulé pour `ProductRepository`.
2. **Injection des mocks** : `@InjectMocks` injecte le mock dans `ProductService`.
3. **Définition du comportement** : `when(...).thenReturn(...)` configure le mock pour retourner une valeur spécifique.
4. **Vérification** : `verify(...)` vérifie que le mock a été utilisé comme prévu.

En résumé, un mock est un outil puissant pour tester des unités de code de manière isolée, en simulant leurs dépendances.

## Différence entre Mock et Données Factices

Dans les tests unitaires, il est important de comprendre la différence entre **mocker les dépendances** et **créer des données factices** :

### Mocker les Dépendances

- **Qu'est-ce qu'un mock ?**
  - Un **mock** est une dépendance simulée utilisée pour remplacer une implémentation réelle dans un test unitaire.
  - Par exemple, un repository, un service ou une API externe peut être mocké pour isoler l'unité de code testée.

- **Pourquoi utiliser un mock ?**
  - **Isolation** : Tester une unité de code sans dépendre des autres parties du système.
  - **Contrôle** : Simuler des comportements spécifiques (retours, exceptions, etc.).
  - **Performance** : Éviter d'utiliser des ressources réelles (comme une base de données).

- **Exemple avec Mockito** :

```java
@Mock
private ProductRepository productRepository;

@InjectMocks
private ProductService productService;

@Test
void shouldCreateProductSuccessfully() {
    // Arrange
    Product product = new Product(1L, "Chaise", 59.99, "Comfortable chair", 10);
    when(productRepository.save(product)).thenReturn(product);

    // Act
    Product result = productService.createProduct(product);

    // Assert
    assertNotNull(result);
    assertEquals("Chaise", result.getName());
    verify(productRepository, times(1)).save(product);
    verifyNoMoreInteractions(productRepository);
}
```

### Créer des Données Factices

- **Qu'est-ce que des données factices ?**
  - Ce sont des objets réels créés pour servir de données d'entrée ou de résultats attendus dans un test.
  - Par exemple, un objet `Product` ou `Category` utilisé dans un test.

- **Pourquoi utiliser des données factices ?**
  - Fournir des données nécessaires pour exécuter le test.
  - Vérifier que les résultats produits par le code testé sont corrects.

- **Exemple** :

```java
Product product = new Product(1L, "Chaise", 59.99, "Comfortable chair", 10);
```

### Résumé

- **Mocker** : Simuler une dépendance (repository, service, etc.).
- **Données factices** : Créer des objets réels pour les utiliser dans les tests.

Il est important de ne pas confondre ces deux concepts pour écrire des tests unitaires efficaces et lisibles.

---

## Modèle Arrange-Act-Assert (AAA)

Le modèle **Arrange-Act-Assert (AAA)** est une structure couramment utilisée pour organiser les tests unitaires. Il permet de rendre les tests plus lisibles et compréhensibles en divisant clairement les étapes du test.

### Étapes du Modèle AAA

1. **Arrange (Préparer)** :
   - Cette étape consiste à configurer tout ce qui est nécessaire pour exécuter le test.
   - Cela inclut la création des objets, la configuration des mocks, et la définition des données d'entrée.

2. **Act (Agir)** :
   - C'est l'étape où l'action principale est effectuée.
   - Par exemple, appeler une méthode ou exécuter une fonction.

3. **Assert (Vérifier)** :
   - Cette étape consiste à vérifier que le résultat obtenu correspond au résultat attendu.
   - On utilise des assertions pour valider le comportement.

### Exemple en Java avec JUnit et Mockito

```java
@Test
void shouldReturnProductById() {
    // Arrange
    Product mockProduct = new Product(1L, "Product A", 100.0);
    when(productRepository.findById(1L)).thenReturn(Optional.of(mockProduct));

    // Act
    Product result = productService.getProductById(1L);

    // Assert
    assertNotNull(result);
    assertEquals("Product A", result.getName());
    verify(productRepository, times(1)).findById(1L);
}
```

### Avantages du Modèle AAA

- **Lisibilité** : Les étapes sont clairement séparées, ce qui rend le test facile à comprendre.
- **Organisation** : Facilite la structuration des tests, surtout dans des scénarios complexes.
- **Débogage** : Permet d'identifier rapidement où se situe un problème (préparation, action ou vérification).

En suivant ce modèle, vos tests seront plus clairs, maintenables et faciles à comprendre pour les autres développeurs.

# Documentation Technique

Ce document fournit des détails techniques sur la configuration et l'utilisation des outils et fonctionnalités du projet.

## 1️⃣ Configuration de Log4j2

Log4j2 est utilisé pour gérer les logs de manière professionnelle dans le service `Product-Service`.

### Pourquoi utiliser Log4j2 ?

- Suivre le comportement d'une application.
- Aider au débogage.
- Diagnostiquer des bugs en production.
- Analyser les performances.

### Étapes pour configurer Log4j2

1. Ajoutez la dépendance suivante dans `pom.xml` :

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-log4j2</artifactId>
</dependency>
```

2. Désactivez le starter Logback par défaut :

```xml
<exclusions>
    <exclusion>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-logging</artifactId>
    </exclusion>
</exclusions>
```

3. Créez un fichier `log4j2.xml` dans `src/main/resources` :

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN">
    <Appenders>
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="%d{HH:mm:ss} [%t] %-5level %logger{36} - %msg%n"/>
        </Console>
        <File name="FileLogger" fileName="logs/app.log">
            <PatternLayout pattern="%d %p %c [%t] %m%n"/>
        </File>
    </Appenders>
    <Loggers>
        <Root level="info">
            <AppenderRef ref="Console"/>
            <AppenderRef ref="FileLogger"/>
        </Root>
    </Loggers>
</Configuration>
```

4. Exemple d’utilisation dans une classe Java :

```java
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
public class ProductController {
    private static final Logger logger = LogManager.getLogger(ProductController.class);

    @GetMapping("/products")
    public List<Product> getProducts() {
        logger.info("Fetching all products");
        return productService.getAllProducts();
    }
}
```

### Niveaux de Logs

| Niveau  | Usage                                      |
|---------|--------------------------------------------|
| TRACE   | Détail extrême (jamais en prod).           |
| DEBUG   | Infos de débogage.                        |
| INFO    | Suivi d’exécution normal.                 |
| WARN    | Avertissements sans interruption.         |
| ERROR   | Erreurs bloquantes ou critiques.          |

## 2️⃣ Intégration de SonarQube

SonarQube est utilisé pour analyser la qualité du code et détecter les bugs, failles de sécurité, et autres problèmes.

### Pourquoi utiliser SonarQube ?

- Détecter les bugs, failles de sécurité, code dupliqué, et problèmes de lisibilité.
- Améliorer la maintenabilité du code.

### Étapes pour installer et utiliser SonarQube sur Windows

1. Téléchargez et dézippez SonarQube Community Edition : [Lien](https://www.sonarsource.com/products/sonarqube/downloads/).

2. Allez dans le dossier `bin/windows-x86-64` et lancez `StartSonar.bat`.

3. Ouvrez [http://localhost:9000](http://localhost:9000) et connectez-vous avec :
   - **Login** : `admin`
   - **Mot de passe** : `admin`

4. Installez SonarScanner : [Documentation](https://docs.sonarsource.com/sonarqube/latest/analyzing-source-code/scanners/sonarscanner/).

5. Créez un fichier `sonar-project.properties` dans la racine du projet :

```properties
sonar.projectKey=demo-project
sonar.projectName=Demo Project
sonar.projectVersion=1.0
sonar.sources=src
sonar.java.binaries=target/classes
sonar.host.url=http://localhost:9000
sonar.login=TON_TOKEN
```

6. Lancez une analyse :

```bash
mvn clean install
sonar-scanner
```

### Résultats de l’analyse

Les résultats de l’analyse sont disponibles dans l’interface web de SonarQube.
