# Kotlin Intro

Ce projet est conçu pour introduire les concepts fondamentaux et avancés de la programmation en Kotlin. Il est structuré pour fournir des exemples pratiques et des démonstrations pour les développeurs débutants et intermédiaires.

## Structure du Projet

- **Basics** : Concepts fondamentaux comme les variables, les types, et les fonctions.
- **Demos** : Démonstrations pratiques pour illustrer les concepts Kotlin.
- **Models** : Modèles de données utilisés dans les démonstrations.

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

Le projet inclut un `Makefile` pour simplifier les tâches courantes. Voici un résumé des commandes disponibles :

- **Compilation de tous les fichiers Kotlin** :

  ```bash
  make all
  ```

  Compile tous les fichiers Kotlin et place les fichiers `.class` dans `bin/`.

- **Exécution d'une classe contenant un `main()`** :

  ```bash
  make run
  ```

  Affiche un menu interactif pour choisir une classe à exécuter.

- **Nettoyage complet** :

  ```bash
  make clean
  ```

  Supprime tout le contenu du dossier `bin/`.

## Fichier Principal : DemoLauncher.kt

Le fichier `DemoLauncher.kt` est le point d'entrée principal pour exécuter les démonstrations du projet. Il contient une fonction `main()` qui permet de lancer un menu interactif pour choisir et exécuter une démonstration spécifique.

## Contribution

Les contributions sont les bienvenues. Veuillez suivre les étapes ci-dessous :

1. Forker le projet.

2. Créer une branche pour vos modifications.

3. Soumettre une pull request.

## Ressources Supplémentaires

Pour plus de détails, consultez le fichier [DOCS.md](./DOCS.md).

Ressources externes :

- [Documentation Officielle Kotlin](https://kotlinlang.org/docs/home.html)
- [Tutoriels Kotlin](https://www.javatpoint.com/kotlin-tutorial)
