public class NodeN<T extends Comparable<T>> {

    private NodeN<T>[] p; 
    private T[] k;         
    private int n_atual;   

    @SuppressWarnings("unchecked")
    public NodeN(int n) {
        n_atual = 0;
        p = new NodeN[n];             
        k = (T[]) new Comparable[n];  
    }

    protected boolean estahCheio() {
        return n_atual == k.length;
    }

    private int buscaBinariaP(T valor, int minvetor, int maxvetor) {
        while (minvetor <= maxvetor) {
            int meiovetor = (minvetor + maxvetor) / 2;
            int comparacao = valor.compareTo(k[meiovetor]);
            if (comparacao == 0) {
                return meiovetor; // Valor encontrado.
            } else if (comparacao < 0) {
                maxvetor = meiovetor - 1; // Procurar na metade esquerda.
            } else {
                minvetor = meiovetor + 1; // Procurar na metade direita.
            }
        }
        return minvetor; // Retorna o ponto de inserção.
    }

    private void insereOrdenadoNOVetor(T valor) {
        int index = buscaBinariaP(valor, 0, n_atual - 1);
        System.arraycopy(k, index, k, index + 1, n_atual - index);
        k[index] = valor;
        n_atual++;
    }

    public void inserir(T valor) {
        if (estahCheio()) {
            int index = buscaBinariaP(valor, 0, n_atual - 1);
            if (index < p.length) {
                if (p[index] != null) {
                    p[index].inserir(valor);
                } else {
                    p[index] = new NodeN<>(p.length);
                    p[index].insereOrdenadoNOVetor(valor);
                }
            }
        } else {
            insereOrdenadoNOVetor(valor);
        }
    }

    public void remover(T valor) {
        int index = buscaBinariaP(valor, 0, n_atual - 1);
        if (index < n_atual && k[index].equals(valor)) {
            removerDoNo(index);
        } else if (index < p.length && p[index] != null) {
            p[index].remover(valor);
        }
    }

    private void removerDoNo(int index) {
        if (p[index] != null) {
            NodeN<T> maiorNoFilhoEsquerdo = p[index];
            while (maiorNoFilhoEsquerdo.p[maiorNoFilhoEsquerdo.n_atual - 1] != null) {
                maiorNoFilhoEsquerdo = maiorNoFilhoEsquerdo.p[maiorNoFilhoEsquerdo.n_atual - 1];
            }
            k[index] = maiorNoFilhoEsquerdo.k[maiorNoFilhoEsquerdo.n_atual - 1];
            maiorNoFilhoEsquerdo.remover(maiorNoFilhoEsquerdo.k[maiorNoFilhoEsquerdo.n_atual - 1]);
        } else {
            System.arraycopy(k, index + 1, k, index, n_atual - index - 1);
            k[n_atual - 1] = null;
            n_atual--;
        }
    }

    protected NodeN<T> pesquisar(T valor) {
        int index = buscaBinariaP(valor, 0, n_atual - 1);
        if (index < n_atual && k[index].equals(valor)) {
            return this;
        } else if (index < p.length && p[index] != null) {
            return p[index].pesquisar(valor);
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        NodeN<String> nodeN = new NodeN<>(5);

        nodeN.inserir("10");
        nodeN.inserir("05");
        nodeN.inserir("15");
        nodeN.inserir("03");
        nodeN.inserir("07");
        nodeN.inserir("12");
        nodeN.inserir("18");

        System.out.println("Buscando valores na estrutura:");
        System.out.println("Busca por 10: " + (nodeN.pesquisar("10") != null));
        System.out.println("Busca por 5: " + (nodeN.pesquisar("05") != null));
        System.out.println("Busca por 15: " + (nodeN.pesquisar("15") != null));
        System.out.println("Busca por 20: " + (nodeN.pesquisar("20") != null));

        System.out.println("\nValores armazenados na estrutura:");
        imprimirEstrutura(nodeN);
    }

    private static void imprimirEstrutura(NodeN<String> node) {
        imprimirEstruturaRecursivo(node, "");
    }

    private static void imprimirEstruturaRecursivo(NodeN<?> node, String indent) {
        if (node != null) {
            for (int i = 0; i < node.n_atual; i++) {
                System.out.print(indent + node.k[i].toString() + " ");
            }
            System.out.println();
            for (int i = 0; i < node.p.length; i++) {
                imprimirEstruturaRecursivo(node.p[i], indent + "  ");
            }
        }
    }
}