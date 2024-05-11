import java.util.LinkedList;
import java.util.Queue;

public class AVL<T extends Comparable<T>> {
    private Node<T> raiz;

    public AVL() {
        raiz = null;
    }

     // M�todo para encontrar o valor m�ximo na �rvore
    public T Maximo() {
        Node<T> local = raiz;
        if (local == null)
            return null;
        while (local.getRight() != null)
            local = local.getRight();
        return local.getData();
    }

    // M�todo para encontrar o valor m�nimo na �rvore
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

     // M�todo para inserir um novo n� na �rvore
    public Node<T> inserir(T dado) {
        raiz = inserir(raiz, dado);
        return raiz;
    }

    // M�todo auxiliar para inserir um novo n� em um determinado n�
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

    // M�todo para atualizar a profundidade de um n�
    private void atualizarProfundidade(Node<T> no) {
        if (no == null)
            return;
        int profundidadeEsquerda = (no.getLeft() == null) ? 0 : no.getLeft().getProfundidade();
        int profundidadeDireita = (no.getRight() == null) ? 0 : no.getRight().getProfundidade();
        no.setProfundidade(1 + Math.max(profundidadeEsquerda, profundidadeDireita));
    }

      // M�todo para obter o fator de balanceamento de um n�
    private int getFatorBalanceamento(Node<T> no) {
        if (no == null)
            return 0;
        return profundidade(no.getLeft()) - profundidade(no.getRight());
    }

      // M�todo para realizar uma rota��o simples � direita
    private Node<T> rotacionarDireita(Node<T> no) {
        Node<T> filhoEsquerda = no.getLeft();
        Node<T> filhoDireitaEsquerda = filhoEsquerda.getRight();

        filhoEsquerda.setRight(no);
        no.setLeft(filhoDireitaEsquerda);

        atualizarProfundidade(no);
        atualizarProfundidade(filhoEsquerda);

        return filhoEsquerda;
    }

    // M�todo para realizar uma rota��o simples � esquerda
    private Node<T> rotacionarEsquerda(Node<T> no) {
        Node<T> filhoDireita = no.getRight();
        Node<T> filhoEsquerdaDireita = filhoDireita.getLeft();

        filhoDireita.setLeft(no);
        no.setRight(filhoEsquerdaDireita);

        atualizarProfundidade(no);
        atualizarProfundidade(filhoDireita);

        return filhoDireita;
    }

    // M�todo para verificar se um dado est� presente na �rvore
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

    // M�todo para imprimir a �rvore em ordem de n�vel
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

    // M�todo para remover um dado da �rvore
    public Node<T> remover(T dado) {
        raiz = remover(raiz, dado);
        return raiz;
    }

    // M�todo auxiliar para remover um dado da �rvore
    private Node<T> remover(Node<T> no, T dado) {
        if (no == null)
            return null;

        if (dado.compareTo(no.getData()) < 0) {
            no.setLeft(remover(no.getLeft(), dado));
        } else if (dado.compareTo(no.getData()) > 0) {
            no.setRight(remover(no.getRight(), dado));
        } else {
            // Caso o n� a ser removido tenha um filho ou nenhum filho
            if (no.getLeft() == null) {
                return no.getRight();
            } else if (no.getRight() == null) {
                return no.getLeft();
            }

            // Caso o n� a ser removido tenha dois filhos
            no.setData(encontrarMax(no.getLeft()).getData());
            no.setLeft(remover(no.getLeft(), no.getData()));
        }

        // Atualiza a profundidade e balanceia o n�
        atualizarProfundidade(no);
        int fb = getFatorBalanceamento(no);

        // Verifica os casos de rota��o
        if (fb > 1 && getFatorBalanceamento(no.getLeft()) >= 0) {
            return rotacionarDireita(no); // Rota��o simples � direita
        }
        if (fb < -1 && getFatorBalanceamento(no.getRight()) <= 0) {
            return rotacionarEsquerda(no); // Rota��o simples � esquerda
        }
        if (fb > 1 && getFatorBalanceamento(no.getLeft()) < 0) {
            no.setLeft(rotacionarEsquerda(no.getLeft()));
            return rotacionarDireita(no); // Rota��o dupla � direita
        }
        if (fb < -1 && getFatorBalanceamento(no.getRight()) > 0) {
            no.setRight(rotacionarDireita(no.getRight()));
            return rotacionarEsquerda(no); // Rota��o dupla � esquerda
        }

        return no;
    }

    // M�todo para encontrar o n� com o valor m�ximo a partir de um determinado n�
    private Node<T> encontrarMax(Node<T> no) {
        while (no.getRight() != null) {
            no = no.getRight();
        }
        return no;
    }

}

// Classe Node antiga adicionada de n�vel e profundidade
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
        return "N�vel " + nivel + ": " + data;
    }
}
