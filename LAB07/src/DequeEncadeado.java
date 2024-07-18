public class DequeEncadeado<T> implements Deque<T> {
    private ListaEncadeada<T> lista;

    public DequeEncadeado() {
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
    public T getUltimo() {
        return lista.getUltimo();
    }

    @Override
    public void enfileirarInicio(T objeto) {
        lista.inserirInicio(objeto);
    }

    @Override
    public void enfileirarFim(T objeto) {
        lista.inserirFim(objeto);
    }

    @Override
    public T desenfileirarInicio() {
        T result =  lista.getPrimeiro();
        lista.extrair(result);
        return result;
    }

    @Override
    public T desenfileirarFim() {
        T result = lista.getUltimo();
        lista.extrair(result);
        return result;
    }

    public void imprimirDeque() {
        @SuppressWarnings("rawtypes")
        ListaEncadeada.Element ptr = lista.getHead();
        while (ptr != null) {
            System.out.print(ptr.getData() + " ");
            ptr = ptr.getNext();
        }
        System.out.println();
    }
}
