public class ListaEncadeada {

    public final class Element {
        private Object data;
        private Element next;

        public Element(Object d, Element n) {
            this.data = d;
            this.next = n;
        }

        public Object getData() {
            return this.data;
        }

        public Element getNext() {
            return this.next;
        }

        public void inserirDepois(Object item) {
            this.next = new Element(item, this.next);
            if (tail == this) {
                tail = this.next;
            }
        }

        public void inserirAntes(Object item) {
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

    public Object getPrimeiro() {
        if (head == null) {
            throw new ListaVaziaException();
        }
        return head.data;
    }

    public Object getUltimo() {
        if (tail == null) {
            throw new ListaVaziaException();
        }
        return tail.data;
    }

    public class ListaVaziaException extends RuntimeException {
        public ListaVaziaException() {
            super("A lista encadeada esta vazia");
        }
    }

    public void inserirInicio(Object item) {
        Element temp = new Element(item, this.head);
        if (head == null) {
            tail = temp;
        }
        head = temp;
    }

    public void inserirFim(Object item) {
        Element temp = new Element(item, null);
        if (head == null) {
            head = temp;
        } else {
            tail.next = temp;
        }
        tail = temp;
    }

    public void atribuir(ListaEncadeada l) {
        if (l != this) {
            this.fazVazia();
            for (Element ptr = l.head; ptr != null; ptr = ptr.next) {
                this.inserirFim(ptr.data);
            }
        }
    }

    public void extrair(Object item) {
        Element ptr = head;
        Element prevPtr = null;

        while (ptr != null && ptr.data != item) {
            prevPtr = ptr;
            ptr = ptr.next;
        }
        if (ptr == null) {
            throw new ObjetoNaoEncontradoException();
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

    public class ObjetoNaoEncontradoException extends RuntimeException {
        public ObjetoNaoEncontradoException() {
            super("Objeto não encontrado");
        }
    }

    public static void main(String[] args) {
        ListaEncadeada listaEncadeada = new ListaEncadeada();

        listaEncadeada.inserirFim("A");
        listaEncadeada.inserirFim("B");
        listaEncadeada.inserirFim("C");

        Element ptr = listaEncadeada.getHead();
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

        ListaEncadeada listaEncadeada2 = new ListaEncadeada();
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
