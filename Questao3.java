package questao3;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;

public class Questao3 {
    
    private static Integer FazerLanche() {
        /* Criando uma variável lanche */
        Random rand = new Random();
        Integer lanche = rand.nextInt(10)+1;
        

        try {
            /* Delay para simular o lanche sendo preparado */
            Thread.sleep(rand.nextInt(20)+1);
        } catch (InterruptedException ex) {
            Logger.getLogger(Questao3.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lanche;
    }  
            
    public static void main(String args[]) throws InterruptedException, ExecutionException {

        System.out.println("Pedido anotado ...");
        
        /* Utilizando o CompletableFuture para realizar as tarefas em segundo plano */
        CompletableFuture<Integer> refrigerante = CompletableFuture.supplyAsync(() -> FazerLanche());
        CompletableFuture<Integer> pipoca = CompletableFuture.supplyAsync(() -> FazerLanche());
        CompletableFuture<Integer> docinho = CompletableFuture.supplyAsync(() -> FazerLanche());
      
        /* Enquanto os pedidos não estiverem prontos (Apenas visualização) */
        while (!(refrigerante.isDone() && pipoca.isDone() && docinho.isDone())) {
            System.out.println("\nO lanche está sendo preparado!");
            System.out.println("Refrigerante: " + refrigerante.isDone());
            System.out.println("Pipoca: " + pipoca.isDone());
            System.out.println("Doce: " + docinho.isDone());
            Thread.sleep(5);
        }

        System.out.println("\nLanche Pronto!");
        System.out.println("Refrigerante: " + refrigerante.isDone());
        System.out.println("Pipoca: " + pipoca.isDone());
        System.out.println("Doce: " + docinho.isDone());

        /* Obtendo os valores dos pedidos */
        System.out.println("\nPreço dos itens:");
        System.out.println("Refrigerante: " + refrigerante.get());
        System.out.println("Pipoca: " + pipoca.get());
        System.out.println("Doce: " + docinho.get());
        System.out.println("Total: " + (refrigerante.get() + pipoca.get() + docinho.get()) + " Reais");
        
    }
}
