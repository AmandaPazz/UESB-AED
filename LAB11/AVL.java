import java.util.LinkedList;
import java.util.Queue;

public class AVL<T extends Comparable<T>> {
    private Node<T> raiz;

    public AVL() {
        raiz = null;
    }

     // Método para encontrar o valor máximo na árvore
    public T Maximo() {
        Node<T> local = raiz;
        if (local == null)
            return null;
        while (local.getRight() != null)
            local = local.getRight();
        return local.getData();
    }

    // Método para encontrar o valor mínimo na árvore
    public T Minimo() {
        Node<T> local = raiz;
        if (local == null)
            return null;
        while (local.getLeft() != null) {
            local = local.getLeft();
        }
        return local.getData();
    }


    private int profundidade(Node<T> no) {
        if (no == null)
            return 0;
        return no.getProfundidade();

    }

     // Método para inserir um novo nó na árvore
    public Node<T> inserir(T dado) {
        raiz = inserir(raiz, dado);
        return raiz;
    }

    // Método auxiliar para inserir um novo nó em um determinado nó
    public Node<T> inserir(Node<T> no, T dado) {
        if (no == null)
            return new Node<T>(dado);
        if (no.getData().compareTo(dado) > 0) {
            no.setLeft(inserir(no.getLeft(), dado));
        } else if (no.getData().compareTo(dado) < 0) {
            no.setRight(inserir(no.getRight(), dado));
        }

        atualizarProfundidade(no);

        int fb = getFatorBalanceamento(no);

        // Caso esquerdo do caso esquerdo
        if (fb > 1 && dado.compareTo(no.getLeft().getData()) < 0) {
            return rotacionarDireita(no);
        }
        // Caso direito do caso direito
        if (fb < -1 && dado.compareTo(no.getRight().getData()) > 0) {
            return rotacionarEsquerda(no);
        }
        // Caso esquerdo do caso direito
        if (fb > 1 && dado.compareTo(no.getLeft().getData()) > 0) {
            no.setLeft(rotacionarEsquerda(no.getLeft()));
            return rotacionarDireita(no);
        }
        // Caso direito do caso esquerdo
        if (fb < -1 && dado.compareTo(no.getRight().getData()) < 0) {
            no.setRight(rotacionarDireita(no.getRight()));
            return rotacionarEsquerda(no);
        }

        return no;
    }

    // Método para atualizar a profundidade de um nó
    private void atualizarProfundidade(Node<T> no) {
        if (no == null)
            return;
        int profundidadeEsquerda = (no.getLeft() == null) ? 0 : no.getLeft().getProfundidade();
        int profundidadeDireita = (no.getRight() == null) ? 0 : no.getRight().getProfundidade();
        no.setProfundidade(1 + Math.max(profundidadeEsquerda, profundidadeDireita));
    }

      // Método para obter o fator de balanceamento de um nó
    private int getFatorBalanceamento(Node<T> no) {
        if (no == null)
            return 0;
        return profundidade(no.getLeft()) - profundidade(no.getRight());
    }

      // Método para realizar uma rotação simples à direita
    private Node<T> rotacionarDireita(Node<T> no) {
        Node<T> filhoEsquerda = no.getLeft();
        Node<T> filhoDireitaEsquerda = filhoEsquerda.getRight();

        filhoEsquerda.setRight(no);
        no.setLeft(filhoDireitaEsquerda);

        atualizarProfundidade(no);
        atualizarProfundidade(filhoEsquerda);

        return filhoEsquerda;
    }

    // Método para realizar uma rotação simples à esquerda
    private Node<T> rotacionarEsquerda(Node<T> no) {
        Node<T> filhoDireita = no.getRight();
        Node<T> filhoEsquerdaDireita = filhoDireita.getLeft();

        filhoDireita.setLeft(no);
        no.setRight(filhoEsquerdaDireita);

        atualizarProfundidade(no);
        atualizarProfundidade(filhoDireita);

        return filhoDireita;
    }

    // Método para verificar se um dado está presente na árvore
    public boolean buscar(T dado) {
        Node<T> local = raiz;
        while (local != null) {
            if (local.getData().compareTo(dado) == 0)
                return true;
            else if (local.getData().compareTo(dado) > 0)
                local = local.getLeft();
            else
                local = local.getRight();
        }
        return false;
    }

    // Método para imprimir a árvore em ordem de nível
    public void imprimirArvore() {
        raiz.nivel = 0;
        Queue<Node<T>> fila = new LinkedList<Node<T>>();
        fila.add(raiz);
        while (!fila.isEmpty()) {
            Node<T> no = fila.poll();
            System.out.println(no);
            int nivel = no.nivel;
            Node<T> esquerda = no.getLeft();
            Node<T> direita = no.getRight();
            if (esquerda != null) {
                esquerda.nivel = nivel + 1;
                fila.add(esquerda);
            }
            if (direita != null) {
                direita.nivel = nivel + 1;
                fila.add(direita);
            }
        }
    }

    // Método para remover um dado da árvore
    public Node<T> remover(T dado) {
        raiz = remover(raiz, dado);
        return raiz;
    }

    // Método auxiliar para remover um dado da árvore
    private Node<T> remover(Node<T> no, T dado) {
        if (no == null)
            return null;

        if (dado.compareTo(no.getData()) < 0) {
            no.setLeft(remover(no.getLeft(), dado));
        } else if (dado.compareTo(no.getData()) > 0) {
            no.setRight(remover(no.getRight(), dado));
        } else {
            // Caso o nó a ser removido tenha um filho ou nenhum filho
            if (no.getLeft() == null) {
                return no.getRight();
            } else if (no.getRight() == null) {
                return no.getLeft();
            }

            // Caso o nó a ser removido tenha dois filhos
            no.setData(encontrarMax(no.getLeft()).getData());
            no.setLeft(remover(no.getLeft(), no.getData()));
        }

        // Atualiza a profundidade e balanceia o nó
        atualizarProfundidade(no);
        int fb = getFatorBalanceamento(no);

        // Verifica os casos de rotação
        if (fb > 1 && getFatorBalanceamento(no.getLeft()) >= 0) {
            return rotacionarDireita(no); // Rotação simples à direita
        }
        if (fb < -1 && getFatorBalanceamento(no.getRight()) <= 0) {
            return rotacionarEsquerda(no); // Rotação simples à esquerda
        }
        if (fb > 1 && getFatorBalanceamento(no.getLeft()) < 0) {
            no.setLeft(rotacionarEsquerda(no.getLeft()));
            return rotacionarDireita(no); // Rotação dupla à direita
        }
        if (fb < -1 && getFatorBalanceamento(no.getRight()) > 0) {
            no.setRight(rotacionarDireita(no.getRight()));
            return rotacionarEsquerda(no); // Rotação dupla à esquerda
        }

        return no;
    }

    // Método para encontrar o nó com o valor máximo a partir de um determinado nó
    private Node<T> encontrarMax(Node<T> no) {
        while (no.getRight() != null) {
            no = no.getRight();
        }
        return no;
    }

}

// Classe Node antiga adicionada de nível e profundidade
class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
    private T data;
    private Node<T> left;
    private Node<T> right;
    public int nivel;
    private int profundidade;

    public Node(T data) {
        this(data, null, null);
    }

    public Node(T data, Node<T> left, Node<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;

        if (left == null && right == null)
            setProfundidade(1);
        else if (left == null)
            setProfundidade(right.getProfundidade() + 1);
        else if (right == null)
            setProfundidade(left.getProfundidade() + 1);
        else
            setProfundidade(Math.max(left.getProfundidade(), right.getProfundidade()) + 1);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public int getProfundidade() {
        return profundidade;
    }

    public void setProfundidade(int profundidade) {
        this.profundidade = profundidade;
    }

    @Override
    public int compareTo(Node<T> o) {
        return this.data.compareTo(o.data);
    }

    @Override
    public String toString() {
        return "Nível " + nivel + ": " + data;
    }
}
