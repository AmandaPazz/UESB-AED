import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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

    // fun��o de inserir de forma ordenada (menor - esquerda)
    public boolean inserirOrdenado(T v) {
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

    // fun��es de imprimir
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

    protected void imprimeEmLarguraRecursivo(Queue<Node<T>> fila) {
        while (!fila.isEmpty()) {
            Node<T> no = fila.poll();

            if (no != null) {
                if (no.getFilho_esq() != null) {
                    fila.offer(no.getFilho_esq());
                }
                if (no != null) {
                    fila.offer(no.getFilho_dir());
                }

                System.out.print(no.getValor() + " ");
                this.imprimeEmLarguraRecursivo(fila);
            }
        }

    }

    protected void imprimeEmLarguraInvertido() {
        Queue<Node<T>> fila = new LinkedList<>();
        Stack<String> pilha = new Stack<>();

        fila.offer(this);

        while (!fila.isEmpty()) {
            Node<T> no = fila.poll();

            if (no.getFilho_esq() != null) {
                fila.offer(no.getFilho_esq());
            }
            if (no.getFilho_dir() != null) {
                fila.offer(no.getFilho_dir());
            }

            pilha.push(no.getValor().toString());

        }

        while (!pilha.isEmpty()) {
            System.out.print(pilha.pop() + " ");
        }
    }

    protected int calculaAltura() {
        int alturadireita = 0;
        int alturaesquerda = 0;

        if (this.getFilho_esq() != null) {
            alturaesquerda = this.getFilho_esq().calculaAltura() + 1;
        }
        if (this.getFilho_dir() != null) {
            alturadireita = this.getFilho_dir().calculaAltura() + 1;
        }

        return alturaesquerda > alturadireita ? alturaesquerda : alturadireita;
    }

    protected int calculaAlturaEmLargura() {
        Queue<Node<T>> fila = new LinkedList<>();
        Queue<Integer> distancia = new LinkedList<>();

        fila.offer(this);
        distancia.offer(0);

        int alturaMaxima = 0;

        while (!fila.isEmpty()) {
            Node<T> no = fila.poll();
            int distanciaPai = distancia.poll();

            if (no.getFilho_esq() != null) {
                fila.offer(no.getFilho_esq());
                distancia.offer(distanciaPai + 1);
            }
            if (no.getFilho_dir() != null) {
                fila.offer(no.getFilho_dir());
                distancia.offer(distanciaPai + 1);
            }

            if (distanciaPai > alturaMaxima) {
                alturaMaxima = distanciaPai;
            }
        }
        return alturaMaxima;
    }

    protected Node<T> pesquisarValor(T valor) {
        int comparacao = valor.toString().compareTo(this.getValor().toString());

        if (comparacao < 0) {
            if (this.getFilho_esq() != null) {
                return this.getFilho_esq().pesquisarValor(valor);
            } else {
                return null;
            }
        }

        else if (comparacao > 0) {
            if (this.getFilho_dir() != null) {
                return this.getFilho_dir().pesquisarValor(valor);
            } else {
                return null;
            }
        }

        else {
            return this;
        }
    }

    protected int calculaToTalNodes() {
        int totalesquerda = 0;
        int totaldireita = 0;

        if (this.getFilho_esq() != null) {
            totalesquerda = this.getFilho_esq().calculaToTalNodes();
        }
        if (this.getFilho_dir() != null) {
            totaldireita = this.getFilho_dir().calculaToTalNodes();
        }

        return totaldireita + totalesquerda + 1;
    }

    protected int calculaToTalNodesFolhas() {
        int totalesquerda = 0;
        int totaldireita = 0;
        boolean ehfolha = true;

        if (this.getFilho_esq() != null) {
            totalesquerda = this.getFilho_esq().calculaToTalNodesFolhas();
            ehfolha = false;
        }
        if (this.getFilho_dir() != null) {
            totaldireita = this.getFilho_dir().calculaToTalNodesFolhas();
            ehfolha = false;
        }

        if (ehfolha) {
            return 1;
        } else {
            return totaldireita + totalesquerda;
        }
    }

    protected void setFilhoNode(Node<T> nodePai, Node<T> novoNode) {
        if (nodePai.getFilho_esq() == this) {
            nodePai.setEsq(novoNode);
        } else {
            nodePai.setDir(novoNode);
        }
    }

    protected Node<T> NodeComMaiorValor() {
        if (this.getFilho_dir() != null) {
            return this.getFilho_dir().NodeComMaiorValor();
        } else
            return this;
    }

    protected Node<T> NodeComMenorValor() {
        if (this.getFilho_esq() != null) {
            return this.getFilho_esq().NodeComMenorValor();
        } else
            return this;
    }

    protected Node<T> acharPai(Node<T> nofilho) {
        if (this.getFilho_esq() == nofilho || this.getFilho_dir() == nofilho) {
            return this;
        } else {
            if (nofilho.getValor().toString().compareTo(this.getValor().toString()) < 0) {
                if (this.getFilho_esq() != null) {
                    return this.getFilho_esq().acharPai(nofilho);
                } else {
                    return null;
                }
            } else if (nofilho.getValor().toString().compareTo(this.getValor().toString()) > 0) {
                if (this.getFilho_dir() != null) {
                    return this.getFilho_dir().acharPai(nofilho);
                } else {
                    return null;
                }
            } else {
                return null; // O n� filho n�o foi encontrado como filho deste n�
            }
        }
    }
    
    

    protected boolean removerNode(T v, Node<T> nodepai) {

        int comparacao = v.toString().compareTo(this.getValor().toString());
        boolean resultado = false;

        if (comparacao < 0) {
            if (this.getFilho_esq() != null)
                return this.getFilho_esq().removerNode(v, this);
            else
                resultado = false;

        } else if (comparacao > 0) {
            if (this.getFilho_dir() != null)
                return this.getFilho_dir().removerNode(v, this);
            else {
                resultado = false;
            }
        } else {
            if ((this.getFilho_esq() == null) && (this.getFilho_dir() == null)) {
                this.setFilhoNode(nodepai, null);
                resultado = true;

            } else if ((this.getFilho_esq() != null) && (this.getFilho_dir() == null)) {
                this.setFilhoNode(nodepai, this.getFilho_esq());
                resultado = true;

            } else if ((this.getFilho_esq() == null) && (this.getFilho_dir() != null)) {
                this.setFilhoNode(nodepai, this.getFilho_dir());
                resultado = true;

            } else if ((this.getFilho_esq() != null) && (this.getFilho_dir() != null)) {
                Node<T> menorNodeDir = this.getFilho_dir().NodeComMenorValor();
                Node<T> paiMenorNodeDir;

                if (menorNodeDir != this.getFilho_dir()) {
                    paiMenorNodeDir = this.getFilho_dir().acharPai(menorNodeDir);
                } else {
                    paiMenorNodeDir = this;
                }
             
                this.setValor(menorNodeDir.getValor());
                menorNodeDir.setFilhoNode(paiMenorNodeDir, menorNodeDir.getFilho_dir());

                resultado = true;
            }

        }

        return resultado;
    }

    protected int calcularProfundidade(Node<T> node) {
        if (node == null || node == this)
            return 0;
        int profundidadePai = calcularProfundidade(acharPai(node));
        return profundidadePai + 1;
    }

    protected int calcularAltura(Node<T> node) {
        if (node == null)
            return -1;
        int alturaEsquerda = calcularAltura(node.getFilho_esq());
        int alturaDireita = calcularAltura(node.getFilho_dir());
        return Math.max(alturaEsquerda, alturaDireita) + 1;
    }

    protected int calcularComprimentoCaminho(Node<T> nodeDestino) {
        return calcularProfundidade(nodeDestino) - calcularProfundidade(this);
    }

    public boolean ehInterno() {
        return this.filho_esq != null || this.filho_dir != null;
    }
}
