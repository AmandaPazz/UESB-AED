public class AVL<T> extends Node<T> {
    private int fatorBalanceamento = 0;

    public AVL(T v) {
        super(v);
        fatorBalanceamento = 0;
    }

    public AVL<T> getFilhoDir() {
        return (AVL<T>)super.getFilho_dir();
    }

    public AVL<T> getFilhoEsq() {
        return (AVL<T>)super.getFilho_esq();
    }

    public int getFB() {
        return this.fatorBalanceamento;
    }

    protected AVL<T> InsereOrdenado(T v, AVL<T> nodePai) {
        if (v.toString().compareTo(this.getValor().toString()) < 0) {
            if (this.getFilhoEsq() != null) {
                this.setEsq(this.getFilhoEsq().InsereOrdenado(v, this));
            } else {
                AVL<T> n = new AVL<>(v);
                this.setEsq(n);
            }
        } else {
            if (this.getFilhoDir() != null) {
                this.setDir(this.getFilhoDir().InsereOrdenado(v, this));
            } else {
                AVL<T> n = new AVL<>(v);
                this.setDir(n);
            }
        }
        return this.verificarBalanceamento(nodePai);
    }

    protected AVL<T> removerNode(T v, AVL<T> nodePai) {
        int comparacao = v.toString().compareTo(this.getValor().toString());

        if (comparacao < 0) {
            if (this.getFilhoEsq() != null) {
                this.setEsq(this.getFilhoEsq().removerNode(v, this));
            }
        } else if (comparacao > 0) {
            if (this.getFilhoDir() != null) {
                this.setDir(this.getFilhoDir().removerNode(v, this));
            }
        } else {
            if (this.getFilhoEsq() == null && this.getFilhoDir() == null) {
                return null; // Nó folha, retorna null para remover
            } else if (this.getFilhoEsq() != null && this.getFilhoDir() == null) {
                return this.getFilhoEsq(); // Nó com filho à esquerda
            } else if (this.getFilhoEsq() == null && this.getFilhoDir() != null) {
                return this.getFilhoDir(); // Nó com filho à direita
            } else {
                // Nó com dois filhos, substituir pelo sucessor na ordem
                Node<T> menorNodeDir = this.getFilhoDir().NodeComMenorValor();
                this.setValor(menorNodeDir.getValor());
                this.setDir(this.getFilhoDir().removerNode(menorNodeDir.getValor(), this));
            }
        }
        return this.verificarBalanceamento(nodePai);
    }

    private int calculaFatorBalanceamento() {
        int alturaEsq = (this.getFilhoEsq() != null) ? this.getFilhoEsq().calculaAltura() + 1 : 0;
        int alturaDir = (this.getFilhoDir() != null) ? this.getFilhoDir().calculaAltura() + 1 : 0;
        this.fatorBalanceamento = alturaDir - alturaEsq;
        return this.fatorBalanceamento;
    }

    private AVL<T> verificarBalanceamento(AVL<T> nopai) {
        int fb = this.calculaFatorBalanceamento();

        if (fb < -1 || fb > 1) {
            if (fb > 1) {
                if (this.getFilhoDir() != null && this.getFilhoDir().calculaFatorBalanceamento() < 0) {
                    return this.rotacaoDuplaAEsquerda(nopai);
                } else {
                    return this.rotacaoAEsquerda(nopai);
                }
            } else {
                if (this.getFilhoEsq() != null && this.getFilhoEsq().calculaFatorBalanceamento() > 0) {
                    return this.rotacaoDuplaADireita(nopai);
                } else {
                    return this.rotacaoADireita(nopai);
                }
            }
        }
        return this;
    }

    private AVL<T> rotacaoAEsquerda(AVL<T> nopaideA) {
        AVL<T> novaraizB = this.getFilhoDir();
        this.setFilhoNode(nopaideA, novaraizB);
        this.setDir(novaraizB.getFilhoEsq());
        novaraizB.setEsq(this);
        return novaraizB;
    }

    private AVL<T> rotacaoADireita(AVL<T> nopaideC) {
        AVL<T> novaraizB = this.getFilhoEsq();
        this.setFilhoNode(nopaideC, novaraizB);
        this.setEsq(novaraizB.getFilhoDir());
        novaraizB.setDir(this);
        return novaraizB;
    }

    private AVL<T> rotacaoDuplaAEsquerda(AVL<T> nopaideA) {
        this.setDir(this.getFilhoDir().rotacaoADireita(this));
        return this.rotacaoAEsquerda(nopaideA);
    }

    private AVL<T> rotacaoDuplaADireita(AVL<T> nopaideA) {
        this.setEsq(this.getFilhoEsq().rotacaoAEsquerda(this));
        return this.rotacaoADireita(nopaideA);
    }

    public static void main(String[] args) {
        // Criação da árvore AVL
        AVL<String> arvoreAVL = new AVL<>("10");

        // Inserindo elementos na árvore AVL
        arvoreAVL.InsereOrdenado("05", null);
        arvoreAVL.InsereOrdenado("15", null);
        arvoreAVL.InsereOrdenado("03", null);
        arvoreAVL.InsereOrdenado("07", null);
        arvoreAVL.InsereOrdenado("12", null);
        arvoreAVL.InsereOrdenado("18", null);
        arvoreAVL.InsereOrdenado("01", null);
        arvoreAVL.InsereOrdenado("04", null);
        arvoreAVL.InsereOrdenado("06", null);
        arvoreAVL.InsereOrdenado("08", null);
        arvoreAVL.InsereOrdenado("11", null);
        arvoreAVL.InsereOrdenado("14", null);
        arvoreAVL.InsereOrdenado("16", null);
        arvoreAVL.InsereOrdenado("20", null);

        // Impressão da árvore em largura para verificar a estrutura
        System.out.println("Impressao em largura:");
        arvoreAVL.imprimeEmLargura();

        // Removendo alguns elementos da árvore AVL
        arvoreAVL.removerNode("10", null);
        arvoreAVL.removerNode("05", null);
        arvoreAVL.removerNode("15", null);

        // Impressão da árvore após remoções
        System.out.println("\n\n\nImpressao em largura apos remocoes:");
        arvoreAVL.imprimeEmLargura();

        arvoreAVL.InsereOrdenado("21", null);

        System.out.println("\n\nImpressao em largura:");
        arvoreAVL.imprimeEmLargura();


        // Teste de balanceamento
        System.out.println("\n\n\nAltura da arvore apos remocoes: \n" + arvoreAVL.calculaAltura());
        System.out.println("\n\n\nFator de balanceamento da raiz apos remocoes: \n" + arvoreAVL.getFB());

        System.out.println("\n\n");
    }
}
