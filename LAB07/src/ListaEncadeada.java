public class ListaEncadeada<T> {

    public final class Element {
        private T data;
        private Element next;

        public Element(T d, Element n) {
            this.data = d;
            this.next = n;
        }

        public T getData() {
            return this.data;
        }

        public Element getNext() {
            return this.next;
        }

        public void inserirDepois(T item) {
            this.next = new Element(item, this.next);
            if (tail == this) {
                tail = next;
            }
        }

        public void inserirAntes(T item) {
            Element temp = new Element(item, this);
            if (this == head) {
                head = temp;
            } else {
                Element ptr = head;
                while (ptr != null && ptr.next != this) {
                    ptr = ptr.next;
                }
                ptr.next = temp;
            }
        }
    }

    private Element head;
    private Element tail;

    public ListaEncadeada() {
        this.fazVazia();
    }

    public void fazVazia() {
        head = null;
        tail = null;
    }

    public Element getHead() {
        return head;
    }

    public Element getTail() {
        return tail;
    }

    public boolean estaVazia() {
        return (head == null);
    }

    public T getPrimeiro() {
        if (head == null) {
            System.err.println("Erro: Lista encadeada está vazia.");
            return null;
        }
        return head.data;
    }

    public T getUltimo() {
        if (tail == null) {
            System.err.println("Erro: Lista encadeada está vazia.");
            return null;
        }
        return tail.data;
    }

    public void inserirInicio(T item) {
        Element temp = new Element(item, head);
        if (head == null) {
            tail = temp;
        }
        head = temp;
    }

    public void inserirFim(T item) {
        Element temp = new Element(item, null);
        if (head == null) {
            head = temp;
        } else {
            tail.next = temp;
        }
        tail = temp;
    }

    public void atribuir(ListaEncadeada<T> l) {
        if (l != this) {
            this.fazVazia();
            for (Element ptr = l.head; ptr != null; ptr = ptr.next) {
                this.inserirFim(ptr.data);
            }
        }
    }

    public void extrair(T item) {
        Element ptr = head;
        Element prevPtr = null;

        while (ptr != null && !ptr.data.equals(item)) {
            prevPtr = ptr;
            ptr = ptr.next;
        }
        if (ptr == null) {
            System.err.println("Erro: Objeto não encontrado na lista encadeada.");
            return;
        }
        if (ptr == head) {
            head = ptr.next;
        } else {
            prevPtr.next = ptr.next;
        }
        if (ptr == tail) {
            tail = prevPtr;
        }
    }

    public static void main(String[] args) {
        ListaEncadeada<String> listaEncadeada = new ListaEncadeada<>();

        listaEncadeada.inserirFim("A");
        listaEncadeada.inserirFim("B");
        listaEncadeada.inserirFim("C");

        ListaEncadeada<String>.Element ptr = listaEncadeada.getHead();
        while (ptr != null) {
            System.out.print(ptr.getData() + " ");
            ptr = ptr.getNext();
        }

        System.out.println("\n");

        listaEncadeada.fazVazia();
        listaEncadeada.inserirInicio("C");
        listaEncadeada.inserirInicio("B");
        listaEncadeada.inserirInicio("A");

        ptr = listaEncadeada.getHead();
        while (ptr != null) {
            System.out.print(ptr.getData() + " ");
            ptr = ptr.getNext();
        }

        System.out.println("\n");

        ptr = listaEncadeada.getHead();
        ptr.inserirDepois("X");

        ptr = listaEncadeada.getHead();
        while (ptr != null) {
            System.out.print(ptr.getData() + " ");
            ptr = ptr.getNext();
        }
        System.out.println("\n");

        ptr = listaEncadeada.getTail();
        ptr.inserirAntes("X");

        ptr = listaEncadeada.getHead();
        while (ptr != null) {
            System.out.print(ptr.getData() + " ");
            ptr = ptr.getNext();
        }
        System.out.println("\n");

        boolean achou = true;
        while (achou) {
            achou = false;
            ptr = listaEncadeada.getHead();
            while (ptr != null) {
                if (ptr.getData().equals("X")) {
                    listaEncadeada.extrair("X");
                    achou = true;
                    break;
                }
                ptr = ptr.getNext();
            }
        }
        ptr = listaEncadeada.getHead();
        while (ptr != null) {
            System.out.print(ptr.getData() + " ");
            ptr = ptr.getNext();
        }
        System.out.println("\n");

        ListaEncadeada<String> listaEncadeada2 = new ListaEncadeada<>();
        listaEncadeada2.inserirFim("1");
        listaEncadeada2.inserirFim("2");
        listaEncadeada2.inserirFim("3");
        listaEncadeada.atribuir(listaEncadeada2);

        ptr = listaEncadeada.getHead();
        while (ptr != null) {
            System.out.print(ptr.getData() + " ");
            ptr = ptr.getNext();
        }
        System.out.print("\n");

        ptr = listaEncadeada2.getHead();
        while (ptr != null) {
            System.out.print(ptr.getData() + " ");
            ptr = ptr.getNext();
        }
        System.out.print("\n");

    }
}
