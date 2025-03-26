package cinema;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;


public class Cinema {
    
    private static Integer FazerLanche() {
      /* Criando uma variável lanche */
      Integer lanche = 1;

      try {
          /* Delay para simular o lanche sendo preparado */
          Random rand = new Random();
          Thread.sleep(rand.nextInt(20));
      } catch (InterruptedException ex) {
          Logger.getLogger(Cinema.class.getName()).log(Level.SEVERE, null, ex);
      }
      return lanche;
 }  
            
    public static void main(String args[]) throws InterruptedException, ExecutionException {

        System.out.println("Pedido anotado ...");
        
        /* Utilizando o CompletableFuture para realizar as tarefas em segundo plano, sem bloquear as threads */
        CompletableFuture refrigerante = CompletableFuture.supplyAsync(() -> {
                return FazerLanche();
        });     

        CompletableFuture pipoca = CompletableFuture.supplyAsync(() -> FazerLanche());
      
        CompletableFuture docinho = CompletableFuture.supplyAsync(() -> FazerLanche());
        
        /* Enquanto os pedidos não estiverem prontos, o código vai continuar repetindo até as threads decidirem ficarem prontas */
        while (!(refrigerante.isDone() && pipoca.isDone() && docinho.isDone())) {
            System.out.println("\nO lanche está sendo preparado!");
            System.out.println("Refrigerante: " + refrigerante.isDone());
            System.out.println("Pipoca: " + pipoca.isDone());
            System.out.println("Doce: " + docinho.isDone());
            Thread.sleep(1); 
      }

      System.out.println("\nLanche Pronto!");
      System.out.println("Refrigerante: " + refrigerante.isDone());
      System.out.println("Pipoca: " + pipoca.isDone());
      System.out.println("Doce: " + docinho.isDone());
      
      if(refrigerante.get() == 1){
          System.out.println("Refrigerante: " + refrigerante.get());
      }
      
    }
    
}
