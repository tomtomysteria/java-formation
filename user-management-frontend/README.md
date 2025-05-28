# Frontend de Gestion des Utilisateurs

Ce projet a été généré en utilisant [Angular CLI](https://github.com/angular/angular-cli) version 19.2.13.

## Serveur de Développement

Pour démarrer un serveur de développement local, exécutez :

```bash
ng serve
```

Une fois le serveur en cours d'exécution, ouvrez votre navigateur et accédez à `http://localhost:4200/`. L'application se rechargera automatiquement à chaque modification des fichiers sources.

## Génération de Code

Angular CLI inclut des outils puissants pour la génération de code. Pour générer un nouveau composant, exécutez :

```bash
ng generate component nom-du-composant
```

Pour une liste complète des schémas disponibles (tels que `components`, `directives` ou `pipes`), exécutez :

```bash
ng generate --help
```

## Construction

Pour construire le projet, exécutez :

```bash
ng build
```

Cela compilera votre projet et stockera les artefacts de construction dans le répertoire `dist/`. Par défaut, la construction en mode production optimise votre application pour les performances et la vitesse.

## Exécution des Tests Unitaires

Pour exécuter les tests unitaires avec le test runner [Karma](https://karma-runner.github.io), utilisez la commande suivante :

```bash
ng test
```

## Exécution des Tests End-to-End

Pour les tests end-to-end (e2e), exécutez :

```bash
ng e2e
```

Angular CLI ne fournit pas de framework de test end-to-end par défaut. Vous pouvez choisir celui qui correspond à vos besoins.

Pour plus d'informations sur le backend `springboot_demo`, consultez son [README](../springboot_demo/README.md).

## Autres Commandes Utiles

### Nettoyer le Cache Angular

Si vous rencontrez des problèmes liés au cache, vous pouvez exécuter la commande suivante pour nettoyer le cache Angular :

```bash
ng cache clean
```

### Vérifier la Version d'Angular

Pour vérifier la version d'Angular CLI installée, utilisez :

```bash
ng version
```

### Lancer le Serveur SSR

Pour démarrer le serveur de rendu côté serveur (SSR), exécutez :

```bash
npm run serve:ssr:user-management-frontend
```

### Mettre à Jour les Dépendances Angular

Pour mettre à jour Angular CLI et les dépendances associées, exécutez :

```bash
ng update @angular/cli @angular/core
```

## Intégration Backend

Ce projet utilise le backend `springboot_demo` pour gérer les utilisateurs. Assurez-vous que le backend est en cours d'exécution avant de démarrer le frontend.

## Ressources Supplémentaires

Pour plus d'informations sur l'utilisation de l'Angular CLI, y compris des références détaillées sur les commandes, visitez la page [Angular CLI Overview and Command Reference](https://angular.dev/tools/cli).
