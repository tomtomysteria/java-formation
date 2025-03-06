# Deques en Java

Une **Deque** (Double-ended queue) permet d'ajouter et de retirer des éléments aux deux extrémités.

## 1. `ArrayDeque`

### Caractéristiques

- Implémentation rapide basée sur un tableau.
- Permet des ajouts/suppressions aux deux extrémités.

### Exemple d'utilisation

```java
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) {
        Deque<String> deque = new ArrayDeque<>();
        deque.addFirst("Début");
        deque.addLast("Fin");

        System.out.println(deque.removeFirst()); // Début
    }
}
```

## 2. `LinkedList`, alternative à `Deque` mais moins performant

### Caractéristiques

- Basée sur une liste chaînée.
- Peut être utilisée comme `Deque` en Java.
- Moins performant que `Deque`

### Exemple d'utilisation

```java
import java.util.Deque;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Deque<String> deque = new LinkedList<>();
        deque.addFirst("A");
        deque.addLast("B");

        System.out.println(deque.removeLast()); // B
    }
}
```
