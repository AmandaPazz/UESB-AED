public interface Cursor<T> {
    T getDado();

    void inserirDepois(T o);

    void inserirAntes(T o);

    void remover();
}