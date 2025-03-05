package basics;

public class LoopsDemo {
  public static void main(String[] args) {
    System.out.println("Boucle for :");
    for (int i = 0; i < 5; i++) {
      System.out.println("Iteration " + i);
    }

    System.out.println("Boucle while :");
    int j = 0;
    while (j < 5) {
      System.out.println("Iteration " + j);
      j++;
    }
  }
}
