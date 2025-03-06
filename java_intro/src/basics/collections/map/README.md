# Tables Clé-Valeur en Java

En Java, une **table clé-valeur** est une structure de données qui stocke des paires `(clé, valeur)`. Les implémentations les plus courantes sont :

## 1. `HashMap`

### Caractéristiques

- Basée sur une table de hachage.
- Accès rapide aux éléments (`O(1)` en moyenne).
- Ne garantit pas l'ordre des éléments.
- Accepte `null` comme clé et valeur.

### Exemple d'utilisation

```java
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap<String, Integer> ages = new HashMap<>();
        ages.put("Alice", 25);
        ages.put("Bob", 30);
        ages.put("Charlie", 22);

        System.out.println(ages.get("Alice")); // 25
    }
}
```

## 2. `TreeMap`

### Caractéristiques

- Basé sur un **arbre rouge-noir**.
- Trie les clés en ordre naturel (`O(log n)`).
- N'autorise pas de clé `null`.

### Exemple d'utilisation

```java
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        TreeMap<String, Integer> ages = new TreeMap<>();
        ages.put("Alice", 25);
        ages.put("Bob", 30);
        ages.put("Charlie", 22);

        System.out.println(ages.firstKey()); // Alice
    }
}
```

## 3. `NavigableMap`
### Caractéristiques
- Étend `SortedMap` et offre des méthodes de navigation avancées (`floorEntry()`, `ceilingEntry()`, etc.).
- Trie les clés dans l'ordre naturel.
- Implémentation courante : `TreeMap`.

### Exemple d'utilisation
```java
import java.util.NavigableMap;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        NavigableMap<Integer, String> map = new TreeMap<>();
        map.put(10, "Dix");
        map.put(20, "Vingt");
        map.put(30, "Trente");

        System.out.println(map.floorEntry(25)); // 20=Vingt
    }
}
```

## 4. `ConcurrentMap`
### Caractéristiques
- Optimisée pour le multithreading, offre une gestion efficace des accès concurrents.
- Implémentation courante : `ConcurrentHashMap`.

### Exemple d'utilisation
```java
import java.util.concurrent.ConcurrentHashMap;

public class Main {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        map.put("Alice", 25);
        map.put("Bob", 30);

        System.out.println(map.get("Alice")); // 25
    }
}
```
