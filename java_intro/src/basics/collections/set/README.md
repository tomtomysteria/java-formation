# Ensembles sans doublons en Java

En Java, un **ensemble** est une collection qui ne contient pas de doublons. Les implémentations les plus courantes sont :

## 1. `HashSet`

### Caractéristiques

- Basé sur une **table de hachage**.
- Accès rapide (`O(1)` en moyenne).
- N'assure aucun ordre spécifique.

### Exemple d'utilisation

```java
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        HashSet<String> set = new HashSet<>();
        set.add("Alice");
        set.add("Bob");
        set.add("Alice"); // Ignoré

        System.out.println(set); // [Alice, Bob]
    }
}
```

## 2. `TreeSet`

### Caractéristiques

- Basé sur un **arbre rouge-noir**.
- Trie les éléments en ordre croissant (`O(log n)` pour insertion/recherche).
- Ne permet pas d'élément `null`.

### Exemple d'utilisation

```java
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        TreeSet<String> set = new TreeSet<>();
        set.add("Charlie");
        set.add("Alice");
        set.add("Bob");

        System.out.println(set); // [Alice, Bob, Charlie]
    }
}
```

## 3. `NavigableSet`
### Caractéristiques
- Étend `SortedSet` et permet la navigation efficace dans un ensemble trié.
- Implémentation courante : `TreeSet`.

### Exemple d'utilisation
```java
import java.util.NavigableSet;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        NavigableSet<Integer> set = new TreeSet<>();
        set.add(10);
        set.add(20);
        set.add(30);

        System.out.println(set.floor(25)); // 20
    }
}
```
