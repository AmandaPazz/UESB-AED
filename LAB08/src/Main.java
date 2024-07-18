public class Main {
    public static void main(String[] args) {
        ListaClassificadaEncadeada<String> lista = new ListaClassificadaEncadeada<>();

        lista.inserir("A");
        lista.inserir("B");
        lista.inserir("C");

        System.out.println("Elementos da lista:");
        for (int i = 0; i < 3; i++) {
            System.out.println(lista.get(i));
        }

        System.out.println("\n\nRemovendo o elemento 'B'");
        lista.remover("B");

        System.out.println("Elementos da lista apos remocao:");
        for (int i = 0; i < 2; i++) {
            System.out.println(lista.get(i));
        }

        System.out.println("\n\nInserindo 'D' no inicio da lista");
        Cursor<String> cursorInicio = lista.procurarPosicao("A");
        cursorInicio.inserirAntes("D");

        System.out.println("Elementos da lista apos insercao no inicio:");
        for (int i = 0; i < 3; i++) {
            System.out.println(lista.get(i));
        }

        Cursor<String> cursorC = lista.procurarPosicao("C");
        System.out.println("\n\nProcurando o elemento 'C' na lista por meio do cursor: " + cursorC.getDado());

        System.out.println("\n\nProcurando o elemento 'C' na lista: " + lista.procurar("C"));

        System.out.println("\n\nO elemento 'A' e membro da lista? " + lista.ehMembro("A"));
        System.out.println("O elemento 'X' e membro da lista? " + lista.ehMembro("X"));
        System.out.println("O elemento 'C' e membro da lista? " + lista.ehMembro("C"));
        System.out.println("O elemento 'D' e membro da lista? " + lista.ehMembro("D"));
        System.out.println("O elemento 'B' e membro da lista? " + lista.ehMembro("B"));

        System.out.println("\n\nRemovendo o elemento 'A'");
        lista.remover("A");

        System.out.println("Elementos da lista apos remocao:");
        for (int i = 0; i < 2; i++) {
            System.out.println(lista.get(i));
        }

        Cursor<String> cursorD = lista.procurarPosicao("D");
        cursorD.inserirDepois("E");

        System.out.println("Elementos da lista apos insercao apos o cursor:");
        for (int i = 0; i < 3; i++) {
            System.out.println(lista.get(i));
        }

        System.out.println("\n\nRemovendo o elemento 'D' pelo cursor");
        cursorD.remover();

        System.out.println("Elementos da lista apos remocao pelo cursor:");
        for (int i = 0; i < 2; i++) {
            System.out.println(lista.get(i));
        }
    }
}
