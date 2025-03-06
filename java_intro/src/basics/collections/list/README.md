# Listes Ordonnées en Java

En Java, une **liste ordonnée** est une structure de données qui conserve l'ordre des éléments et permet la présence de doublons. Les implémentations les plus courantes sont :

## 1. `ArrayList`

### Caractéristiques

- Basée sur un tableau dynamique.
- Accès rapide aux éléments via l'indexation (`O(1)`).
- Insertion et suppression lentes en milieu de liste (`O(n)`).
- Adaptée pour des opérations majoritairement en lecture.

### Exemple d'utilisation

```java
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> liste = new ArrayList<>();
        liste.add("Pomme");
        liste.add("Banane");
        liste.add("Pomme"); // Acceptation des doublons

        System.out.println(liste); // [Pomme, Banane, Pomme]
    }
}
```

## 2. `LinkedList`

### Caractéristiques

- Basée sur une structure chaînée (chaque élément pointe vers le suivant).
- Insertion et suppression rapides en milieu de liste (`O(1)` si le pointeur est connu).
- Accès plus lent aux éléments (`O(n)`) par rapport à `ArrayList`.
- Idéale pour des opérations impliquant des ajouts/suppressions fréquents.

### Exemple d'utilisation

```java
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        LinkedList<String> liste = new LinkedList<>();
        liste.add("Chat");
        liste.add("Chien");
        liste.add("Chat"); // Acceptation des doublons

        System.out.println(liste); // [Chat, Chien, Chat]
    }
}
```

## 3. Comparaison entre `ArrayList` et `LinkedList`

| Caractéristique     | `ArrayList`         | `LinkedList`       |
|---------------------|--------------------|--------------------|
| Structure interne  | Tableau dynamique  | Liste chaînée     |
| Accès aux éléments | Rapide `O(1)`       | Lent `O(n)`       |
| Ajout en fin       | Rapide `O(1)`       | Rapide `O(1)`     |
| Ajout en milieu    | Lent `O(n)`         | Rapide `O(1)` si pointeur connu |
| Suppression        | Lent `O(n)`         | Rapide `O(1)` si pointeur connu |

### Conclusion

- Utiliser **`ArrayList`** lorsque l'accès rapide aux éléments est prioritaire.
- Utiliser **`LinkedList`** lorsque les insertions et suppressions fréquentes en milieu de liste sont nécessaires.
