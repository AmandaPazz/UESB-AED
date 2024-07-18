import java.util.*;

public class Main {
    public static void main(String[] args) {
        Node<String> raiz = new Node<>("10");
        raiz.inserirOrdenado("05");
        raiz.inserirOrdenado("15");
        raiz.inserirOrdenado("03");
        raiz.inserirOrdenado("07");
        raiz.inserirOrdenado("12");
        raiz.inserirOrdenado("18");
        raiz.inserirOrdenado("01");
        raiz.inserirOrdenado("04");
        raiz.inserirOrdenado("06");
        raiz.inserirOrdenado("08");
        raiz.inserirOrdenado("11");
        raiz.inserirOrdenado("14");
        raiz.inserirOrdenado("16");
        raiz.inserirOrdenado("20");


        System.out.println("Imprimindo em ordem:");
        raiz.imprimirInOrdem();
        System.out.println("\nImprimindo em pre-ordem:");
        raiz.imprimeEmPreordem();
        System.out.println("\nImprimindo em pos-ordem:");
        raiz.imprimeEmPosordem();
        System.out.println("\nImprimindo em largura:");
        raiz.imprimeEmLargura();
        System.out.println("\nImprimindo em largura recursiva:");
        Queue<Node<String>> fila = new LinkedList<>();
        fila.offer(raiz);
        raiz.imprimeEmLarguraRecursivo(fila);
        System.out.println("\nImprimindo em largura invertido:");
        raiz.imprimeEmLarguraInvertido();


        System.out.println("\nCalculando a altura por recursividade:");
        System.out.print(raiz.calculaAltura());
        System.out.println("\nCalculando a altura por largura:");
        System.out.print(raiz.calculaAlturaEmLargura());


        System.out.println("\nPesquisando valor (7):");
        System.out.print(raiz.pesquisarValor("07").getValor());
        System.out.println("\nPesquisando valor (34):");
        System.out.print(raiz.pesquisarValor("34"));


        System.out.println("\nCalculando o total de nos:");
        System.out.print(raiz.calculaToTalNodes());
        System.out.println("\nCalculando o total de folhas:");
        System.out.print(raiz.calculaToTalNodesFolhas());


        System.out.println("\nRemovendo o (7):");
        System.out.println(raiz.removerNode("07", raiz.acharPai(raiz.pesquisarValor("07"))));
        raiz.imprimeEmLargura();


        System.out.println("\nNovo pai do (6)");
        System.out.print(raiz.acharPai(raiz.pesquisarValor("06")).getValor());
        System.out.println("\nNovo pai do (8)");
        System.out.print(raiz.acharPai(raiz.pesquisarValor("08")).getValor());


        System.out.println("\nCalculando a profundidade do 01");
        System.out.print(raiz.calcularProfundidade(raiz.pesquisarValor("01")));
        System.out.println("\nCalculando a altura do 05");
        System.out.print(raiz.calcularAltura(raiz.pesquisarValor("05")));


        System.out.println("\nCalculando o comprimento do caminho entre o 10 e o 01:");
        System.out.print(raiz.calcularComprimentoCaminho(raiz.pesquisarValor("01")));

        
        System.out.println("\nCVerificando se o 01 eh interno ou externo:");
        if (raiz.pesquisarValor("01").ehInterno()) {
            System.out.println("Eh interno");
        } else {
            System.out.println("Eh externo");
        }
        System.out.println("\nCVerificando se o 05 eh interno ou externo:");
        if (raiz.pesquisarValor("05").ehInterno()) {
            System.out.println("Eh interno");
        } else {
            System.out.println("Eh externo");
        }




        System.out.println("\n\n");
    }
}
