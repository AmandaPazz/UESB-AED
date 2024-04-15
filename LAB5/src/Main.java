public class Main {
    public static void main(String[] args) {

        //Testando Fila com array
        Fila<String> fila = new FilaArray<>(5);
        
        System.out.println("\nA fila esta vazia? " + fila.estaVazia());
        
        fila.enfileirar("Elemento 1");
        fila.enfileirar("Elemento 2");
        fila.enfileirar("Elemento 3");

        fila.imprimir();
        
        System.out.println("A fila esta vazia? " + fila.estaVazia());
        
        System.out.println("Primeiro elemento da fila: " + fila.getPrimeiro());
        
        System.out.println("Desenfileirando: " + fila.desenfileirar());

        fila.imprimir();
        
        System.out.println("Primeiro elemento da fila: " + fila.getPrimeiro());
        
        fila.fazVazia();
        System.out.println("A fila esta vazia? " + fila.estaVazia());

        System.out.println("\n");


         //Testando Fila com Lista Encadeada
        System.out.println("--------------------------------------------------");

        FilaEncadeada<String> filaEncadeada = new FilaEncadeada<>();

        filaEncadeada.enfileirar("A");
        filaEncadeada.enfileirar("B");
        filaEncadeada.enfileirar("C");

        System.out.println("Primeiro elemento da fila: " + filaEncadeada.getPrimeiro());

        filaEncadeada.imprimir();

        while (!filaEncadeada.estaVazia()) {
            System.out.println("Desenfileirando: " + filaEncadeada.desenfileirar());
        }

        System.out.println("A fila esta vazia? " + filaEncadeada.estaVazia());
    }
    }

