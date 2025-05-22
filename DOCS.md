# Documentation du Projet Formation

## Introduction

Ce projet contient plusieurs sous-projets Java et Kotlin, ainsi qu'une architecture microservices. Il est conçu pour démontrer des concepts fondamentaux et avancés de la programmation, ainsi que des pratiques modernes de développement logiciel.

---

## Structure du Projet

### Sous-projets

1. **java_intro** :
   - Contient des démonstrations et des exercices sur les concepts fondamentaux de Java.
   - Organisation :
     - `src/basics` : Concepts de base (collections, exceptions, etc.).
     - `src/demos` : Démonstrations pratiques (ex. : `CalculatorDemo`, `CarDemo`).
     - `src/models` : Modèles utilisés dans les démonstrations.
     - `src/utils` : Utilitaires pour les démonstrations.

2. **kotlin_intro** :
   - Contient des démonstrations et des exercices sur les concepts fondamentaux de Kotlin.
   - Organisation :
     - `src/basics` : Concepts de base (ex. : `VariablesDemo.kt`).
     - `src/demos` : Démonstrations pratiques (ex. : `BookDemo.kt`, `PersonDemo.kt`).
     - `src/models` : Modèles utilisés dans les démonstrations.

3. **microservices** :
   - Contient une architecture microservices avec plusieurs services Spring Boot.
   - Services :
     - `api-gateway` : Passerelle API.
     - `eureka-server` : Serveur de découverte.
     - `order-service` : Service de gestion des commandes.
     - `product-service` : Service de gestion des produits.
     - `shared-soap-models` : Modèles partagés pour SOAP.

4. **springboot_demo** :
   - Démonstration d'une application Spring Boot avec des fonctionnalités comme l'authentification JWT et la gestion des utilisateurs.

---

## CI/CD

### Pipeline GitLab CI/CD

Le fichier `.gitlab-ci.yml` configure un pipeline CI/CD pour automatiser les étapes suivantes :

1. **Build** :
   - Nettoyage et compilation des projets Java et Kotlin.
   - Exemple de commande :

     ```bash
     make clean && make all
     ```

2. **Test** :
   - Placeholder pour les tests Java et Kotlin (frameworks à intégrer).

### Étapes du Pipeline

- **Image Docker utilisée** : `eclipse-temurin:17` (Java 17).
- **Scripts avant exécution** :
  - Installation de `make`, `unzip`, et `wget`.
  - Téléchargement et installation du compilateur Kotlin.

## Pipeline CI/CD : Concepts et Avantages

Un Pipeline CI/CD (Continuous Integration/Continuous Deployment ou Delivery) est un ensemble automatisé de processus qui permet de construire, tester, et déployer une application de manière efficace et fiable. Voici une explication des deux concepts principaux :

1. **CI (Continuous Integration)** :
   - **Objectif** : Intégrer fréquemment les modifications de code dans une branche principale.
   - **Étapes typiques** :
     - Compilation du code.
     - Exécution des tests unitaires et d'intégration.
     - Vérification de la qualité du code (linting, analyse statique).
   - **Avantages** :
     - Détecte rapidement les erreurs dans le code.
     - Facilite la collaboration entre les développeurs.

2. **CD (Continuous Deployment ou Delivery)** :
   - **Continuous Delivery** :
     - Prépare automatiquement l'application pour le déploiement (par exemple, création d'un fichier JAR ou d'une image Docker).
     - Le déploiement en production nécessite une validation manuelle.
   - **Continuous Deployment** :
     - Étend le concept de Continuous Delivery en déployant automatiquement en production après validation.
   - **Étapes typiques** :
     - Création d'artefacts (JAR, Docker image, etc.).
     - Déploiement sur un environnement (staging, production, etc.).

### Exemple d'un Pipeline CI/CD

Un pipeline CI/CD peut inclure les étapes suivantes :

- **Build** : Compilation du code source.
- **Test** : Exécution des tests pour valider le code.
- **Analyse** : Vérification de la qualité du code.
- **Package** : Création d'un artefact (JAR, WAR, Docker image).
- **Deploy** : Déploiement sur un serveur ou un cloud.

### Avantages d'un Pipeline CI/CD

- **Automatisation** : Réduit les tâches manuelles et les erreurs humaines.
- **Fiabilité** : Garantit que le code est testé et validé avant d'être déployé.
- **Rapidité** : Accélère le cycle de développement et de livraison.

---

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
