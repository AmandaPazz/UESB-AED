public interface Deque<T> {

    void fazVazia();

    boolean estaVazia();

    T getPrimeiro();

    T getUltimo();

    void enfileirarInicio(T objeto);

    void enfileirarFim(T objeto);

    T desenfileirarInicio();

    T desenfileirarFim();

    void imprimirDeque();

}