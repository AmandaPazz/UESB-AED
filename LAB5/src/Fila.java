public interface Fila<T> {
    void fazVazia();

    boolean estaVazia();

    T getPrimeiro();

    void enfileirar(T objeto);

    T desenfileirar();

    void imprimir();
}