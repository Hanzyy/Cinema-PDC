/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cinema;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Cinema {
    
    private static Integer FazerLanche() {
      Random rand = new Random();
        Integer lanche = rand.nextInt(2);
        System.out.println("\nLanche: "+lanche);

      try {
          Thread.sleep(10);
      } catch (InterruptedException ex) {
          Logger.getLogger(Cinema.class.getName()).log(Level.SEVERE, null, ex);
      }
      return lanche;
 }  
            
    public static void main(String args[]) throws InterruptedException, ExecutionException {

      System.out.println("Processando a tarefa ...");
        CompletableFuture refrigerante = CompletableFuture.supplyAsync(() -> {
              return FazerLanche();
      });     

        CompletableFuture pipoca = CompletableFuture.supplyAsync(() -> FazerLanche());
      
        CompletableFuture docinho = CompletableFuture.supplyAsync(() -> FazerLanche());

      while (!(refrigerante.isDone() && pipoca.isDone() && docinho.isDone())) {
            System.out.println("\nO lanche está sendo preparado!");
            System.out.println("Refrigerante: " + refrigerante.isDone());
            System.out.println("Pipoca: " + pipoca.isDone());
            System.out.println("Doce: " + docinho.isDone());
            Thread.sleep(1); 
      }

      System.out.println("Lanche Pronto!");
    }
    
}
