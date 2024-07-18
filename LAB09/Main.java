public class Main {
    public static void main(String[] args) {
        Node<Integer> raiz = new Node<>(10);
        raiz.inserirOrdenado(5);
        raiz.inserirOrdenado(15);
        raiz.inserirOrdenado(3);
        raiz.inserirOrdenado(7);
        raiz.inserirOrdenado(12);
        raiz.inserirOrdenado(18);
        raiz.inserirOrdenado(1);
        raiz.inserirOrdenado(4);
        raiz.inserirOrdenado(6);
        raiz.inserirOrdenado(8);
        raiz.inserirOrdenado(11);
        raiz.inserirOrdenado(14);
        raiz.inserirOrdenado(16);
        raiz.inserirOrdenado(20);

        System.out.println("Imprimindo em ordem:");
        raiz.imprimirInOrdem(); 
        System.out.println("\nImprimindo em pre-ordem:");
        raiz.imprimeEmPreordem();
        System.out.println("\nImprimindo em pos-ordem:");
        raiz.imprimeEmPosordem();
        System.out.println("\nImprimindo em largura:");
        raiz.imprimeEmLargura();
        System.out.println("\n\n");
    }
}
