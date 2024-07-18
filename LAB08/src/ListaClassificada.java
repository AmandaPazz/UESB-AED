public interface ListaClassificada<T> {
    T get(int i);

    Cursor<T> procurarPosicao(T o);

    boolean ehMembro(T o);

    void inserir(T o);

    void remover(T o);

    T procurar(T o);

}
