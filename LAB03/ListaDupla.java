import java.util.Scanner;

public class ListaDupla<T> {
    private Element head;
    private Element tail;

    public final class Element {
        T data;
        Element next, prev;

        Element(T data, Element prev, Element next) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }

        public void inserirDepois(T item) {
            Element temp = new Element(item, this, this.next);

            if (this == tail) {
                tail = temp;
            } else {
                temp.next.prev = temp;
            }

            this.next = temp;
        }

        public void inserirAntes(T item) {
            Element temp = new Element(item, this.prev, this);

            if (this == head) {
                head = temp;
            } else {
                temp.prev.next = temp;
            }

            this.prev = temp;
        }

    }

    private void imprimirLista() {
        Element ptr = this.head;
        while (ptr != null) {
            System.out.print(ptr.data + " ");
            ptr = ptr.next;
        }
        System.out.println("\n");
    }

    public void inserirInicio(T item) {

        Element temp = new Element(item, null, this.head);

        if (this.head == null) {
            this.tail = temp;
        } else {
            this.head.prev = temp;
        }

        this.head = temp;
    }

    public void inserirFim(T item) {

        Element temp = new Element(item, this.tail, null);

        if (this.tail == null) {
            this.head = temp;
        } else {
            this.tail.next = temp;
        }

        this.tail = temp;
    }

    public void remover(T item) {
        Element ptr = this.head;

        while (ptr != null && !ptr.data.equals(item)) {
            ptr = ptr.next;
        }

        if (ptr == null) {
            System.out.println("ERRO");
            return;
        }

        if (ptr == this.head) {
            this.head = ptr.next;
            if (this.head != null) {
                this.head.prev = null;
            }
        } else if (ptr == this.tail) {
            this.tail = ptr.prev;
            if (this.tail != null) {
                this.tail.next = null;
            }
        } else {
            ptr.prev.next = ptr.next;
            ptr.next.prev = ptr.prev;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ListaDupla<Integer> lista = new ListaDupla<>();

        lista.inserirInicio(1);
        lista.head.inserirDepois(2);
        lista.inserirFim(4);
        lista.tail.inserirAntes(3);

        lista.imprimirLista();

        System.out.println("Digite um elemento para retirar da lista: ");
        int num = input.nextInt();

        lista.remover(num);
        lista.imprimirLista();

        ListaDupla<String> listaString = new ListaDupla<>();

        listaString.inserirInicio("A");
        listaString.head.inserirDepois("B");
        listaString.inserirFim("D");
        listaString.tail.inserirAntes("C");
        
        listaString.imprimirLista();

        System.out.println("Digite um elemento para retirar da lista de Strings: ");
        String str = input.next();
        listaString.remover(str);

        listaString.imprimirLista();

        input.close();

    }
}