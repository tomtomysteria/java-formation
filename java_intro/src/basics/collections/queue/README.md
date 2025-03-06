# Files d'Attente en Java

En Java, une **file d'attente** est une structure de données de type FIFO (First-In, First-Out). Les implémentations courantes sont :

## 1. `LinkedList`

### Caractéristiques

- Implémente `Queue` et `Deque`.
- Permet l'ajout et la suppression aux deux extrémités.
- Permet des opérations en `O(1)` pour l'ajout et la suppression.

### Exemple d'utilisation

```java
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        queue.add("Alice");
        queue.add("Bob");
        queue.add("Charlie");

        System.out.println(queue.poll()); // Alice
    }
}
```

## 2. `PriorityQueue`

### Caractéristiques

- Implémente une **file de priorité**.
- Trie les éléments selon l'ordre naturel ou un `Comparator`.
- Complexité `O(log n)` pour l'insertion et la suppression.

### Exemple d'utilisation

```java
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(10);
        pq.add(5);
        pq.add(20);

        System.out.println(pq.poll()); // 5 (élément avec la plus haute priorité)
    }
}
```

## 3. `BlockingQueue`
### Caractéristiques
- File d'attente sécurisée pour le multithreading.
- Implémentations courantes : `ArrayBlockingQueue`, `LinkedBlockingQueue`.

### Exemple d'utilisation
```java
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);
        queue.put(1);
        queue.put(2);
        System.out.println(queue.take()); // 1
    }
}
```

## 4. `BlockingDeque`
### Caractéristiques
- Version thread-safe de `Deque`, permet des accès concurrentiels sécurisés.
- Implémentation courante : `LinkedBlockingDeque`.

### Exemple d'utilisation
```java
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        BlockingDeque<String> deque = new LinkedBlockingDeque<>();
        deque.put("Premier");
        deque.put("Deuxième");
        System.out.println(deque.takeFirst()); // Premier
    }
}
```
