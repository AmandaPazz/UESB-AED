public class ListaClassificadaEncadeada<T> implements ListaClassificada<T> {

    private ListaEncadeada<T> lista;
    private int count;

    public ListaClassificadaEncadeada() {
        lista = new ListaEncadeada<>();
        count = 0;
    }

    @Override
    public T get(int offset) {
        if (offset < 0 || offset >= count)
            return null;

        ListaEncadeada<T>.Element ptr = lista.getHead();
        for (int i = 0; i < offset && ptr != null; i++)
            ptr = ptr.getNext();

        if (ptr != null)
            return ptr.getData();
        else
            return null;
    }

    @Override
    public Cursor<T> procurarPosicao(T o) {
        ListaEncadeada<T>.Element ptr = lista.getHead();
        while (ptr != null) {
            if (ptr.getData().equals(o))
                return new MeuCursor(ptr);
            ptr = ptr.getNext();
        }
        return null;
    }

    @Override
    public boolean ehMembro(T o) {
        ListaEncadeada<T>.Element ptr = lista.getHead();
        while (ptr != null) {
            if (ptr.getData().equals(o))
                return true;
            ptr = ptr.getNext();
        }
        return false;
    }

    @Override
    public void inserir(T o) {
        lista.inserirFim(o);
        count++;
    }

    @Override
    public void remover(T o) {
        lista.extrair(o);
        count--;
    }

    @Override
    public T procurar(T o) {
        ListaEncadeada<T>.Element ptr = lista.getHead();
        while (ptr != null) {
            if (ptr.getData().equals(o))
                return ptr.getData();
            ptr = ptr.getNext();
        }
        return null;
    }

    protected class MeuCursor implements Cursor<T> {
        private ListaEncadeada<T>.Element elemento;

        public MeuCursor(ListaEncadeada<T>.Element elem) {
            elemento = elem;
        }

        @Override
        public T getDado() {
            return elemento.getData();
        }

        @Override
        public void inserirDepois(T o) {
            elemento.inserirDepois(o);
            count++;
        }

        @Override
        public void inserirAntes(T o) {
            elemento.inserirAntes(o);
            count++;
        }

        @Override
        public void remover() {
            lista.extrair(elemento.getData());
            count--;
        }
    }
}