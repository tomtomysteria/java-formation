package basics.collections.queue;

import java.util.LinkedList;
import java.util.Queue;

public class QueueDemo {
  public static void main(String[] args) {
    Queue<String> queue = new LinkedList<>();
    queue.add("Client 1");
    queue.add("Client 2");

    System.out.println("Prochain client : " + queue.poll()); // Retire le premier élément
  }
}
