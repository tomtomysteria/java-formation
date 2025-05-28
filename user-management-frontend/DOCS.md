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

## Commandes Angular CLI pour Générer des Éléments

### Générer un Composant

Pour créer un nouveau composant Angular :

```bash
ng generate component <nom-du-composant>
```

Alias :

```bash
ng g c <nom-du-composant>
```

Exemple :

```bash
ng g c user-profile
```

### Générer un Service

Pour créer un nouveau service Angular :

```bash
ng generate service <nom-du-service>
```

Alias :

```bash
ng g s <nom-du-service>
```

Exemple :

```bash
ng g s auth
```

### Générer un Module

Pour créer un nouveau module Angular :

```bash
ng generate module <nom-du-module>
```

Alias :

```bash
ng g m <nom-du-module>
```

Exemple :

```bash
ng g m user
```

### Générer une Directive

Pour créer une nouvelle directive Angular :

```bash
ng generate directive <nom-de-la-directive>
```

Alias :

```bash
ng g d <nom-de-la-directive>
```

Exemple :

```bash
ng g d highlight
```

### Générer un Pipe

Pour créer un nouveau pipe Angular :

```bash
ng generate pipe <nom-du-pipe>
```

Alias :

```bash
ng g p <nom-du-pipe>
```

Exemple :

```bash
ng g p date-format
```

### Générer un Guard

Pour créer un nouveau guard Angular :

```bash
ng generate guard <nom-du-guard>
```

Alias :

```bash
ng g g <nom-du-guard>
```

Exemple :

```bash
ng g g auth
```

### Générer une Interface

Pour créer une nouvelle interface TypeScript :

```bash
ng generate interface <nom-de-l-interface>
```

Alias :

```bash
ng g i <nom-de-l-interface>
```

Exemple :

```bash
ng g i user
```

### Générer une Classe

Pour créer une nouvelle classe TypeScript :

```bash
ng generate class <nom-de-la-classe>
```

Alias :

```bash
ng g cl <nom-de-la-classe>
```

Exemple :

```bash
ng g cl user
```

### Générer un Enum

Pour créer un nouvel enum TypeScript :

```bash
ng generate enum <nom-de-l-enum>
```

Alias :

```bash
ng g e <nom-de-l-enum>
```

Exemple :

```bash
ng g e user-role
```

### Générer un Resolver

Pour créer un nouveau resolver Angular :

```bash
ng generate resolver <nom-du-resolver>
```

Alias :

```bash
ng g r <nom-du-resolver>
```

Exemple :

```bash
ng g r user
```

## Ressources Supplémentaires

- [Documentation Angular CLI](https://angular.dev/tools/cli)
- [Documentation Angular Material](https://material.angular.io/)
