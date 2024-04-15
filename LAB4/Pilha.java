public interface Pilha<T> {

    void fazVazia();

    boolean estaVazia();

    T getTop();

    void push(T o);

    T pop();
}
