# Documentation du Projet Java Intro

Ce projet est une introduction aux concepts fondamentaux de la programmation en Java. Il contient des exemples pratiques, des démonstrations et des utilitaires pour aider les développeurs à comprendre les bases et les concepts avancés de Java.

## Structure du Projet

### 1. **Basics**
- Contient des exemples sur les concepts fondamentaux de Java, comme les collections, les exceptions et les bases du langage.
- **Sous-dossiers :**
  - `collections/` : Exemples sur les listes, les maps, les queues, etc.
  - `exceptions/` : Gestion des exceptions en Java.
  - `fundamentals/` : Concepts de base comme les types de données, les boucles, etc.

### 2. **Demos**
- Contient des démonstrations pratiques pour illustrer l'utilisation des concepts Java dans des scénarios réels.
- **Exemples :**
  - `BookDemo.java` : Démonstration sur la gestion des livres.
  - `CalculatorDemo.java` : Exemple de calculatrice.
  - `CarDemo.java` : Gestion des voitures.

### 3. **Models**
- Contient des modèles de données utilisés dans les démonstrations.
- **Sous-dossiers :**
  - `book/` : Modèles liés aux livres.
  - `car/` : Modèles liés aux voitures.
  - `employee/` : Modèles liés aux employés.

### 4. **Utils**
- Contient des utilitaires réutilisables pour les démonstrations et les exemples.

## Prérequis
- **Java 17** ou version supérieure.
- Un IDE comme IntelliJ IDEA, Eclipse ou VS Code.

## Compilation et Exécution

1. **Compilation** :
   ```bash
   javac -d bin src/**/*.java
   ```

2. **Exécution** :
   ```bash
   java -cp bin demos.DemoLauncher
   ```

## Automatisation avec le Makefile

Le projet inclut un `Makefile` pour automatiser les tâches courantes comme la compilation, l'exécution et le nettoyage. Voici les commandes disponibles :

### Commandes Principales

1. **Compilation de tous les fichiers Java** :

   ```bash
   make all
   ```

   Compile tous les fichiers Java dans `src/` et place les fichiers `.class` dans `bin/`.

2. **Compilation d'un fichier spécifique** :

   ```bash
   make compile
   ```

   Affiche un menu interactif pour sélectionner un fichier Java à compiler.

3. **Exécution d'une classe spécifique** :

   ```bash
   make run
   ```

   Affiche un menu interactif pour choisir une classe à exécuter (exclut les classes dans `demos/` et `models/`).

4. **Exécution de la démo principale** :

   ```bash
   make run-demo
   ```

   Exécute directement la classe `demos.DemoLauncher`.

5. **Suppression d'un fichier `.class` spécifique** :

   ```bash
   make clean-file
   ```

   Affiche un menu interactif pour supprimer un fichier `.class` spécifique.

6. **Nettoyage complet** :

   ```bash
   make clean
   ```

   Supprime tout le contenu du dossier `bin/`.

### Prérequis pour le Makefile

- Assurez-vous que `make` est installé sur votre système.
- Utilisez un terminal compatible avec les commandes `bash`.

## Contribution

Les contributions sont les bienvenues. Veuillez suivre les étapes ci-dessous :
1. Forker le projet.
2. Créer une branche pour vos modifications.
3. Soumettre une pull request.

## Ressources Supplémentaires
- [Documentation Officielle Java](https://docs.oracle.com/en/java/)
- [Tutoriels Java](https://www.javatpoint.com/java-tutorial)
