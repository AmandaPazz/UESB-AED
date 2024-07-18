public class FilaArray<T> implements Fila<T> {
    private T[] array;
    private int head, tail, count;

    @SuppressWarnings("unchecked")
    public FilaArray(int tam) {
        array = (T[]) new Object[tam];
        head = 0;
        tail = tam - 1;
        count = 0;
    }

    public void fazVazia() {
        while (count > 0) {
            array[head] = null;
            ++head;
            if (head == array.length)
                head = 0;
            --count;
        }
    }

    public boolean estaVazia() {
        return (count == 0);
    }

    public T getPrimeiro() {
        if (count == 0)
            throw new ContainerVazioException("A fila está vazia.");
        return array[head];
    }

    public void enfileirar(T objeto) {
        if (count == array.length)
            throw new ContainerCheioException("A fila está cheia.");
        ++tail;
        if (tail == array.length)
            tail = 0;
        array[tail] = objeto;
        ++count;
    }

    public T desenfileirar() {
        if (count == 0)
            throw new ContainerVazioException("A fila está vazia.");
        T result = array[head];
        array[head] = null;
        ++head;
        if (head == array.length)
            head = 0;
        --count;
        return result;
    }

    public void imprimir() {
        if (count == 0) {
            System.out.println("Fila vazia.");
            return;
        }
        int index = head;
        for (int i = 0; i < count; i++) {
            System.out.println(array[index]);
            index = (index + 1) % array.length;
        }
    }

    public static class ContainerVazioException extends RuntimeException {
        public ContainerVazioException(String message) {
            super(message);
        }
    }

    public static class ContainerCheioException extends RuntimeException {
        public ContainerCheioException(String message) {
            super(message);
        }
    }
}
