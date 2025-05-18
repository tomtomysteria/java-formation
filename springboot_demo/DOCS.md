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
   spring.datasource.username=postgres
   spring.datasource.password=postgres
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

## Lien vers le README

Pour une vue d'ensemble rapide, consultez le fichier [README.md](./README.md).

---
