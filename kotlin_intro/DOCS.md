# Documentation du Projet Kotlin Intro

Ce projet est une introduction aux concepts fondamentaux de la programmation en Kotlin. Il contient des exemples pratiques, des démonstrations et des utilitaires pour aider les développeurs à comprendre les bases et les concepts avancés de Kotlin.

## Structure du Projet

### 1. **Basics**
- Contient des exemples sur les concepts fondamentaux de Kotlin, comme les variables, les types, et les fonctions.
- **Sous-dossiers :**
  - `variables/` : Exemples sur les variables et les types.
  - `functions/` : Exemples sur les fonctions et les lambdas.

### 2. **Demos**
- Contient des démonstrations pratiques pour illustrer l'utilisation des concepts Kotlin dans des scénarios réels.
- **Exemples :**
  - `BookDemo.kt` : Démonstration sur la gestion des livres.
  - `CalculatorDemo.kt` : Exemple de calculatrice.
  - `CarDemo.kt` : Gestion des voitures.

### 3. **Models**
- Contient des modèles de données utilisés dans les démonstrations.
- **Sous-dossiers :**
  - `book/` : Modèles liés aux livres.
  - `car/` : Modèles liés aux voitures.
  - `person/` : Modèles liés aux personnes.

## Prérequis
- **Kotlin 1.8** ou version supérieure.
- Un IDE comme IntelliJ IDEA ou VS Code avec le plugin Kotlin.

## Compilation et Exécution

1. **Compilation** :
   ```bash
   kotlinc -d bin src/**/*.kt
   ```

2. **Exécution** :
   ```bash
   kotlin -cp bin demos.DemoLauncherKt
   ```

## Automatisation avec le Makefile

Le projet inclut un `Makefile` pour automatiser les tâches courantes comme la compilation, l'exécution et le nettoyage. Voici les commandes disponibles :

### Commandes Principales

1. **Compilation de tous les fichiers Kotlin** :

   ```bash
   make all
   ```

   Compile tous les fichiers Kotlin dans `src/` et place les fichiers `.class` dans `bin/`.

2. **Exécution d'une classe contenant un `main()`** :

   ```bash
   make run
   ```

   Affiche un menu interactif pour choisir une classe à exécuter.

3. **Suppression d'un fichier `.class` spécifique** :

   ```bash
   make clean-file
   ```

   Affiche un menu interactif pour supprimer un fichier `.class` spécifique.

4. **Nettoyage complet** :

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
- [Documentation Officielle Kotlin](https://kotlinlang.org/docs/home.html)
- [Tutoriels Kotlin](https://www.javatpoint.com/kotlin-tutorial)
