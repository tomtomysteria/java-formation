# Java Intro

Ce projet est conçu pour introduire les concepts fondamentaux et avancés de la programmation en Java. Il est structuré pour fournir des exemples pratiques et des démonstrations pour les développeurs débutants et intermédiaires.

## Structure du Projet

- **Basics** : Concepts fondamentaux comme les collections, les exceptions, et les bases du langage.
- **Demos** : Démonstrations pratiques pour illustrer les concepts Java.
- **Models** : Modèles de données utilisés dans les démonstrations.
- **Utils** : Utilitaires réutilisables pour les exemples et les démonstrations.

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

Le projet inclut un `Makefile` pour simplifier les tâches courantes. Voici un résumé des commandes disponibles :

- **Compilation de tous les fichiers Java** :

  ```bash
  make all
  ```

  Compile tous les fichiers Java et place les fichiers `.class` dans `bin/`.

- **Exécution de la démo principale** :

  ```bash
  make run-demo
  ```

  Lance la classe `demos.DemoLauncher`.

- **Nettoyage complet** :

  ```bash
  make clean
  ```

  Supprime tout le contenu du dossier `bin/`.

Pour plus de détails sur les commandes disponibles, consultez le fichier [DOCS.md](./DOCS.md).

## Documentation Complète

Pour plus de détails, consultez le fichier [DOCS.md](./DOCS.md).

## Contribution

Les contributions sont les bienvenues. Veuillez suivre les étapes ci-dessous :

1. Forker le projet.

2. Créer une branche pour vos modifications.

3. Soumettre une pull request.

## Ressources Supplémentaires

- [Documentation Officielle Java](https://docs.oracle.com/en/java/)
- [Tutoriels Java](https://www.javatpoint.com/java-tutorial)

## DemoLauncher.java

Le fichier `DemoLauncher.java` est le point d'entrée principal pour exécuter les démonstrations incluses dans ce projet. Il fournit un menu interactif permettant de sélectionner et d'exécuter différentes démonstrations.

### Fonctionnalités

- **Menu interactif** :
  - Affiche une liste d'options correspondant aux démonstrations disponibles.
  - Permet à l'utilisateur de choisir une démonstration à exécuter en entrant un numéro.

- **Gestion des entrées utilisateur** :
  - Vérifie que l'entrée est un entier valide.
  - Affiche un message d'erreur en cas d'entrée incorrecte.

- **Démonstrations disponibles** :
  - Démo des voitures (`CarDemo`)
  - Démo de la calculatrice (`CalculatorDemo`)
  - Démo Pair/Impair (`EvenOddDemo`)
  - Démo Employés (`EmployeeDemo`)
  - Démo des livres (`BookDemo`)
  - Démo de la calculatrice sécurisée (`SecureCalculatorDemo`)
  - Démo de l'annuaire téléphonique (`TelephoneDirectoryDemo`)

### Exemple d'utilisation

Pour exécuter le programme principal :

```bash
make run-demo
```

Une fois lancé, le programme affichera un menu interactif. L'utilisateur peut entrer un numéro pour sélectionner une démonstration ou `0` pour quitter le programme.

### Code Source

Le fichier est situé dans le répertoire `src/demos/` et peut être modifié pour ajouter de nouvelles démonstrations ou personnaliser le menu.
