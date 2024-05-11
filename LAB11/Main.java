public class Main {
    public static void main(String[] args) {
        AVL<String> tree = new AVL<String>();
        tree.inserir("50");
        tree.inserir("01");
        tree.inserir("64");
        tree.inserir("12");
        tree.inserir("18");
        tree.inserir("66");
        tree.inserir("38");
        tree.inserir("95");
        tree.inserir("58");
        tree.inserir("59");
        tree.inserir("70");
        tree.inserir("68");
        tree.inserir("39");
        tree.inserir("62");
        tree.inserir("07");
        tree.inserir("60");
        tree.inserir("43");
        tree.inserir("16");
        tree.inserir("67");
        tree.inserir("34");
        tree.inserir("35");

        System.out.println("Arvore AVL antes da remocao:");
        tree.imprimirArvore();
        System.out.println("\n");

        System.out.println("Maximo da arvore: " + tree.Maximo());
        System.out.println("Minimo da arvore: " + tree.Minimo());

        tree.remover("50");
        tree.remover("95");
        tree.remover("70");
        tree.remover("60");
        tree.remover("35");

        System.out.println("\n\nArvore AVL apos a remocao:");
        tree.imprimirArvore();
        System.out.println("\n\n");

        
        System.out.println("Maximo da arvore: " + tree.Maximo());
        System.out.println("Minimo da arvore: " + tree.Minimo());
    }
}