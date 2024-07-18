import java.util.LinkedList;
import java.util.Queue;

public class Node<T> {
    private T valor;
    private Node<T> filho_esq;
    private Node<T> filho_dir;

    // construtores
    public Node(T v) {
        this.valor = v;
        this.filho_esq = null;
        this.filho_dir = null;
    }

    public Node(T v, Node<T> NoEsq, Node<T> NoDir) {
        this.valor = v;
        this.filho_esq = NoEsq;
        this.filho_dir = NoDir;
    }

    // getters e setters
    public T getValor() {
        return this.valor;
    }

    public Node<T> getFilho_esq() {
        return this.filho_esq;
    }

    public Node<T> getFilho_dir() {
        return this.filho_dir;
    }

    public void setValor(T v) {
        this.valor = v;
    }

    public void setEsq(Node<T> f_esq) {
        this.filho_esq = f_esq;
    }

    public void setDir(Node<T> f_dir) {
        this.filho_dir = f_dir;
    }

    // função de inserir de forma ordenada (menor - esquerda)
    public boolean inserirOrdenado(T v) {
        if (v instanceof Integer) {
            if ((Integer) v < (Integer) this.valor) {
                if (this.getFilho_esq() != null) {
                    return this.getFilho_esq().inserirOrdenado(v);
                } else {
                    Node<T> n = new Node<T>(v);
                    this.setEsq(n);
                    return true;
                }
            } else {
                if (this.getFilho_dir() != null) {
                    return this.getFilho_dir().inserirOrdenado(v);
                } else {
                    Node<T> n = new Node<T>(v);
                    this.setDir(n);
                    return true;
                }
            }
        } else {
            if (v.toString().compareTo(this.getValor().toString()) < 0) {
                if (this.getFilho_esq() != null) {
                    return this.getFilho_esq().inserirOrdenado(v);
                } else {
                    Node<T> n = new Node<T>(v);
                    this.setEsq(n);
                    return true;
                }
            } else {
                if (this.getFilho_dir() != null) {
                    return this.getFilho_dir().inserirOrdenado(v);
                } else {
                    Node<T> n = new Node<T>(v);
                    this.setDir(n);
                    return true;
                }
            }
        }
    }
    

    // funções de imprimir
    protected void imprimirInOrdem() {
        if (this.getFilho_esq() != null) {
            this.getFilho_esq().imprimirInOrdem();
        }

        System.out.print(this.valor + " ");

        if (this.getFilho_dir() != null) {
            this.getFilho_dir().imprimirInOrdem();
        }
    }

    protected void imprimeEmPreordem() {
        System.out.print(this.valor + " "); 
        if (this.getFilho_esq() != null) {
            this.getFilho_esq().imprimeEmPreordem();
        }
        if (this.getFilho_dir() != null) {
            this.getFilho_dir().imprimeEmPreordem(); 
        }
    }

    protected void imprimeEmPosordem() {
        if (this.getFilho_esq() != null) {
            this.getFilho_esq().imprimeEmPosordem(); 
        }
        if (this.getFilho_dir() != null) {
            this.getFilho_dir().imprimeEmPosordem(); 
        }
        System.out.print(this.valor + " "); 
    }
    
    protected void imprimeEmLargura() {
        Queue<Node<T>> fila = new LinkedList<>();
        fila.add(this); 
        
        while (!fila.isEmpty()) {
            Node<T> atual = fila.poll(); 
            System.out.print(atual.valor + " ");
            
            
            if (atual.filho_esq != null) {
                fila.add(atual.filho_esq);
            }
            if (atual.filho_dir != null) {
                fila.add(atual.filho_dir);
            }
        }
    }
}
