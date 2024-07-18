public class FilaEncadeada<T> implements Fila<T> {
    private ListaEncadeada<T> lista;

    public FilaEncadeada() {
        lista = new ListaEncadeada<>();
    }

    @Override
    public void fazVazia() {
        lista.fazVazia();
    }

    @Override
    public boolean estaVazia() {
        return lista.estaVazia();
    }

    @Override
    public T getPrimeiro() {
        return lista.getPrimeiro();
    }

    @Override
    public void enfileirar(T objeto) {
        lista.inserirFim(objeto);
    }

    @Override
    public T desenfileirar() {
        T primeiro = lista.getPrimeiro();
        lista.extrair(primeiro);
        return primeiro;
    }

    public void imprimir() {
        ListaEncadeada<T>.Element ptr = lista.getHead();
        while (ptr != null) {
            System.out.print(ptr.getData() + " ");
            ptr = ptr.getNext();
        }
        System.out.println();
    }
}
