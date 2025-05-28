# Documentation pour le Frontend de Gestion des Utilisateurs

## Concepts Métiers

### Gestion des Utilisateurs

L'application est conçue pour gérer les profils des utilisateurs, y compris leurs informations personnelles et les produits associés. Elle offre une interface conviviale pour visualiser et interagir avec les données des utilisateurs.

### Association de Produits

Chaque utilisateur peut avoir des produits associés. Cette fonctionnalité permet à l'application d'afficher les détails des produits liés à un utilisateur, tels que l'ID du produit, le nom et le prix.

## Concepts Techniques

### Framework Angular

Le projet est construit avec Angular, un framework populaire pour créer des applications web dynamiques. Les fonctionnalités clés utilisées incluent :

- **Composants** : Blocs de construction modulaires de l'application, tels que `UserComponent` et `HeaderComponent`.
- **Routing** : Configuré avec `app.routes.ts` pour gérer la navigation.
- **Rendu Côté Serveur (SSR)** : Implémenté avec `@angular/ssr` pour améliorer les performances et le SEO.

### Angular Material

Angular Material est intégré pour le style et le thème. Le thème `Azure/Blue` est appliqué globalement, et la typographie Material est utilisée pour un design cohérent.

### TypeScript

L'application est développée en TypeScript, tirant parti de son typage statique et de ses fonctionnalités modernes pour une meilleure maintenabilité et une vérification des erreurs.

### Serveur Express

Le serveur backend est implémenté avec Express. Il sert des fichiers statiques et gère les requêtes SSR via `src/server.ts`.

### Modèles

Le modèle `Product` est défini dans `src/app/models/product.model.ts` avec les attributs `id`, `name` et `price`. Ce modèle est utilisé pour représenter les données des produits dans l'application.

### Tests

Les tests unitaires sont écrits avec Jasmine et Karma, comme configuré dans le fichier `angular.json`. Le `UserComponent` dispose d'une suite de tests de base dans `user.component.spec.ts`.

### Style

Les styles globaux sont définis dans `src/styles.css`, et des polices et icônes supplémentaires sont importées dans `src/index.html`.

## Chargement des Composants

Dans Angular, il existe deux principales manières de charger un composant :

1. **Chargement Statique** :

   - Le composant est directement référencé dans une route ou un autre composant via l'attribut `component`.

   - Exemple :

     ```typescript
     {
       path: 'users/add',
       component: UserFormComponent
     }
     ```

   - Cette méthode est simple et convient aux composants fréquemment utilisés.

2. **Chargement Dynamique (Lazy Loading)** :

   - Le composant est chargé dynamiquement via `loadComponent`.

   - Exemple :

     ```typescript
     {
       path: 'users/add',
       loadComponent: () => import('./components/user-form/user-form.component').then(m => m.UserFormComponent)
     }
     ```

   - Cette méthode est idéale pour optimiser les performances en réduisant la taille initiale du bundle JavaScript. Le composant est chargé uniquement lorsque nécessaire.

### Avantages du Chargement Dynamique

- Réduction du temps de chargement initial de l'application.
- Amélioration des performances pour les applications de grande taille.
- Modularité accrue, permettant de mieux isoler les fonctionnalités.

### Quand Utiliser Chaque Méthode ?

- **Statique** : Pour les composants critiques ou fréquemment utilisés.
- **Dynamique** : Pour les composants rarement utilisés ou dans des applications modulaires.

## Flux de Développement

### Lancer l'Application

Pour démarrer le serveur de développement, exécutez :

```bash
ng serve
```

Accédez à `http://localhost:4200/` pour voir l'application.

### Construire l'Application

Pour construire le projet pour la production, exécutez :

```bash
ng build
```

Les artefacts de construction seront stockés dans le répertoire `dist/`.

### Tests

Pour exécuter les tests unitaires, exécutez :

```bash
ng test
```

## Dépendance Backend

Le frontend `user-management-frontend` repose sur le backend `springboot_demo` pour la gestion des utilisateurs. Veuillez démarrer le backend avant d'utiliser le frontend.

Pour plus d'informations sur le backend `springboot_demo`, consultez son [README](../springboot_demo/README.md).

## Ressources Supplémentaires

- [Documentation Angular CLI](https://angular.dev/tools/cli)
- [Documentation Angular Material](https://material.angular.io/)
