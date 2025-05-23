# Spring Boot Demo

Ce projet est une démonstration des fonctionnalités de Spring Boot, incluant l'authentification basée sur JWT et la gestion des utilisateurs avec une base de données PostgreSQL.

## Fonctionnalités principales

- **Authentification et autorisation** : Utilisation de Spring Security et JWT.
- **Gestion des utilisateurs** : CRUD sur les utilisateurs avec JPA et PostgreSQL.
- **Architecture modulaire** : Basée sur les meilleures pratiques de Spring Boot.

## Prérequis

- **Java 17** ou version supérieure.
- **Maven** pour la gestion des dépendances.
- **PostgreSQL** installé et configuré.

## Installation

1. Clonez le dépôt :

   ```bash
   git clone <url-du-repo>
   ```

2. Configurez la base de données dans `application.properties`.

3. Lancez l'application :

   ```bash
   mvn spring-boot:run
   ```

## Configuration de l'application

L'application est configurée pour utiliser une base de données PostgreSQL. Voici les paramètres par défaut :

Vous pouvez retrouver les paramètres dans le fichier `application.properties` situé dans `src/main/resources/`.

## Exécution

1. Assurez-vous que PostgreSQL est en cours d'exécution et que la base de données `spring_demo` est créée.

2. Lancez l'application :

   ```bash
   mvn spring-boot:run
   ```

3. Accédez à l'application sur [http://localhost:8080](http://localhost:8080).

## Points forts de l'application

- **Authentification JWT** : Sécurisez vos API avec des tokens JWT.
- **Gestion des utilisateurs** : CRUD complet pour les utilisateurs.
- **Base de données PostgreSQL** : Stockage des données utilisateur.

Pour plus de détails, consultez le fichier [DOCS.md](./DOCS.md).

## Contribution

Les contributions sont les bienvenues. Veuillez suivre les étapes ci-dessous :

1. Forkez le projet.
2. Créez une branche pour vos modifications.
3. Soumettez une pull request.

## Ressources supplémentaires

- [Documentation Spring Boot](https://spring.io/projects/spring-boot)
- [Tutoriels Spring Boot](https://www.baeldung.com/spring-boot)

## Structure du projet

- **Classe principale** :
  - `SpringbootDemoApplication.java` : Point d'entrée de l'application.

- **Contrôleurs** :
  - `AuthController.java` : Gère l'authentification des utilisateurs.
  - `UserController.java` : Gère les opérations CRUD sur les utilisateurs.
  - `ProductController.java` : Gère les produits.

- **Modèles** :
  - `User.java` : Représente un utilisateur.
  - `Product.java` : Représente un produit.
  - `Category.java` : Représente une catégorie de produits.

- **Dépôts** :
  - `UserRepository.java` : Accès aux données des utilisateurs.
  - `ProductRepository.java` : Accès aux données des produits.

- **Sécurité** :
  - `SecurityConfig.java` : Configure la sécurité de l'application.
  - `JwtRequestFilter.java` : Filtre les requêtes pour valider les JWT.
  - `JwtUtil.java` : Gère la création et la validation des JWT.

## Commandes Utiles

### Lancer les Tests

Pour exécuter les tests unitaires et d'intégration :

```bash
mvn test
```

### Nettoyer le Projet

Pour supprimer les fichiers générés (compilation, rapports, etc.) :

```bash
mvn clean
```

### Réinstaller les Dépendances

Pour forcer la réinstallation des dépendances Maven :

```bash
mvn clean install
```

### Lancer l'Application

Pour démarrer l'application :

```bash
mvn spring-boot:run
```

### Construire le Projet

Pour compiler et construire un fichier JAR exécutable :

```bash
mvn package
```

### Vérifier le Code

Pour analyser le code avec des outils comme Checkstyle ou SpotBugs (si configurés) :

```bash
mvn verify
```

## Qu'est-ce qu'une API ?

Une API (Application Programming Interface) est un ensemble de règles et de conventions qui permet à des applications ou services de communiquer entre eux. Elle définit comment les requêtes doivent être formulées, quelles données peuvent être échangées, et comment les réponses sont structurées.

### Exemple d'utilisation

Dans une application web, une API peut être utilisée pour :

- Récupérer des données depuis une base de données (ex. : liste des utilisateurs).
- Envoyer des données pour créer ou mettre à jour des ressources (ex. : ajouter un produit).
- Fournir des fonctionnalités spécifiques (ex. : authentification via JWT).

Les API sont souvent organisées en endpoints, chacun correspondant à une action ou une ressource spécifique.

## Swagger Documentation

Swagger est intégré dans le projet pour permettre une documentation automatique et interactive des endpoints REST des différentes API.

### Accéder à Swagger

Pour accéder à l'interface Swagger, démarrez l'application et ouvrez un navigateur à l'URL suivante :

```plaintext
http://localhost:8085/swagger-ui/index.html
```

Cela vous permettra de visualiser et de tester les différents endpoints exposés par l'application.
