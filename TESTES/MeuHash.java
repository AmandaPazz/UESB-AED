@SuppressWarnings("unchecked")
public class MeuHash<T> {
    private AVL<T>[] tabelaHash;  // Array de �rvores AVL para armazenar os elementos
    private int m;                // Tamanho da tabela de hash

    // Construtor para inicializar a tabela de hash com tamanho m
    public MeuHash(int m) {
        this.tabelaHash = new AVL[m];  // Inicializa o array de AVLs com tamanho m
        this.m = m;               // Armazena o tamanho m
    }

    // Fun��o hash que retorna um �ndice calculado a partir da chave (toString do valor)
    private int funcaoHash(String chave) {
        int soma = 0;
        int comprimento = chave.length();

        // Calcula um valor de hash baseado nos caracteres da chave
        for (int i = 0; i < comprimento; i++) {
            soma += (chave.charAt(i) * (i + 1));
        }
        return (soma % m);  // Retorna o �ndice na tabela de hash
    }

    // M�todo para inserir um valor na tabela de hash
    public void inserir(T valor) {
        int entradaHash = funcaoHash(valor.toString());  // Calcula o �ndice usando a fun��o hash

        if (tabelaHash[entradaHash] == null) {
            // Se n�o houver uma �rvore AVL nesta posi��o, cria uma nova �rvore com o valor
            tabelaHash[entradaHash] = new AVL<>(valor);
        } else {
            // Caso contr�rio, insere o valor na �rvore AVL existente
            tabelaHash[entradaHash] = tabelaHash[entradaHash].InsereOrdenado(valor, null);
        }
    }

    // M�todo para remover um valor da tabela de hash
    public boolean remover(T valor) {
        int entradaHash = funcaoHash(valor.toString());  // Calcula o �ndice usando a fun��o hash

        if (tabelaHash[entradaHash] == null) {
            return false;  // Retorna falso se n�o houver elemento para remover
        } else {
            // Remove o valor da �rvore AVL na posi��o correspondente
            tabelaHash[entradaHash] = tabelaHash[entradaHash].removerNode(valor, null);
            return true;  // Retorna verdadeiro se o elemento foi removido com sucesso
        }
    }

    // M�todo para alterar o valor de um elemento na tabela de hash
    public void alterarValor(T valorAntigo, T novoValor) {
        if (remover(valorAntigo)) {  // Remove o valor antigo
            inserir(novoValor);      // Insere o novo valor
        }
    }

    // M�todo para imprimir a estrutura da tabela de hash
    public void imprimir() {
        for (int i = 0; i < m; i++) {
            if (tabelaHash[i] != null) {
                System.out.print("Posicao " + i + ": ");
                tabelaHash[i].imprimeEmLargura();  // Imprime os elementos da �rvore AVL
                System.out.println();
            } else {
                System.out.println("Posicao " + i + ": vazia");  // Indica que a posi��o est� vazia
            }
        }
    }

    // M�todo principal para testar a implementa��o
    public static void main(String[] args) {
        int M = 13;  // Tamanho da tabela de hash

        // Teste com strings
        MeuHash<String> hFString = new MeuHash<>(M);
        hFString.inserir("Helio");
        hFString.inserir("jose");
        hFString.inserir("Jose");
        hFString.inserir("Antonio");
        hFString.inserir("Roberto");
        hFString.inserir("Hugo");

        System.out.println("Estrutura da Tabela Hash de Strings apos insercoes:");
        hFString.imprimir();

        hFString.remover("Jose");
        hFString.remover("Helio");

        System.out.println("\n\nEstrutura da Tabela Hash de Strings apos remover 'Jose' e 'Helio':");
        hFString.imprimir();

        hFString.alterarValor("Hugo", "Maria");

        System.out.println("\n\nEstrutura da Tabela Hash de Strings apos alterar 'Hugo' para 'Maria':");
        hFString.imprimir();

        // Teste com inteiros
        MeuHash<Integer> hFInteger = new MeuHash<>(M);
        hFInteger.inserir(10);
        hFInteger.inserir(20);
        hFInteger.inserir(30);
        hFInteger.inserir(50);
        hFInteger.inserir(400);
        hFInteger.inserir(55);

        System.out.println("\n\nEstrutura da Tabela Hash de Inteiros:");
        hFInteger.imprimir();

        // Teste com n�meros reais (double)
        MeuHash<Double> hFDouble = new MeuHash<>(M);
        hFDouble.inserir(1.23);
        hFDouble.inserir(23.234);
        hFDouble.inserir(44444.2);

        System.out.println("\n\nEstrutura da Tabela Hash de numeros reais:");
        hFDouble.imprimir();
    }
}
