public class Main {
    public static void main(String[] args){
        DequeEncadeado<Integer> deque = new DequeEncadeado<>();
 
        System.out.println("Esta vazio? " + deque.estaVazia());

        deque.enfileirarFim(3);
        deque.enfileirarInicio(2);
        deque.enfileirarInicio(1);
        
        deque.imprimirDeque();

        System.out.println("\n\nPrimeiro elemento: " + deque.getPrimeiro());

        deque.enfileirarFim(4);
        System.out.println("\n\nInserindo o 4:");
        deque.imprimirDeque();

        System.out.println("\n\nPrimeiro elemento: " + deque.getPrimeiro());
        System.out.println("Ultimo elemento: " + deque.getUltimo());

        System.out.println("\n\nDesinfileirando o primeiro elemento: " + deque.desenfileirarInicio());
        System.out.println("Deque:");
        deque.imprimirDeque();

        System.out.println("\n\nDesenfileirando do final: " + deque.desenfileirarFim());
        System.out.println("Ultimo elemento apos desenfileirar do final: " + deque.getUltimo());
        System.out.println("Deque:");
        deque.imprimirDeque();

        System.out.println("\n\nEsta vazio? " + deque.estaVazia());
    }
}
