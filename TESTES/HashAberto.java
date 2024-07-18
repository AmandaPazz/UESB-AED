import java.util.Scanner;

@SuppressWarnings("unchecked")
public class HashAberto<K, V> {
    private K[] keys; // Array para armazenar as chaves
    private V[] values; // Array para armazenar os valores correspondentes
    private int size; // Tamanho atual da tabela hash (quantidade de elementos)
    private int capacidade; // Capacidade máxima da tabela hash
    private TipoSondagem tipoSondagem; // Tipo de sondagem utilizada
    private static final double FATOR_CARGA_MAXIMO = 0.5; // Fator de carga máximo antes de redimensionar a tabela

    public enum TipoSondagem {
        LINEAR, // Sondagem linear
        QUADRATICA, // Sondagem quadrática
        DUPLA // Sondagem dupla
    }

    // Construtor padrão: capacidade inicial de 11 e sondagem linear por padrão
    public HashAberto() {
        this(11, TipoSondagem.LINEAR);
    }

    // Construtor com capacidade e tipo de sondagem especificados
    public HashAberto(int capacidade, TipoSondagem tipoSondagem) {
        this.capacidade = capacidade;
        this.tipoSondagem = tipoSondagem;
        this.keys = (K[]) new Object[capacidade]; // Inicializa o array de chaves
        this.values = (V[]) new Object[capacidade]; // Inicializa o array de valores
    }

    // Função de hash para calcular o índice da chave na tabela hash
    private int hash(K key) {
        return Math.abs(key.hashCode() % capacidade);
    }

    // Implementações das sondagens

    // Sondagem linear
    private int sondagemLinear(int index, int tentativa) {
        return (index + tentativa) % capacidade;
    }

    // Sondagem quadrática
    private int sondagemQuadratica(int index, int tentativa) {
        return (index + tentativa * tentativa) % capacidade;
    }

    // Sondagem dupla
    private int sondagemDupla(int index, int tentativa, K key) {
        int hash1 = key.hashCode() % capacidade;
        int hash2 = 1 + (key.hashCode() % (capacidade - 1));
        return (hash1 + tentativa * hash2) % capacidade;
    }

    // Inserção de um par chave-valor na tabela hash
    public void put(K key, V value) {
        checkAndResize(); // Verifica o fator de carga antes de inserir

        int index = hash(key); // Calcula o índice inicial usando a função de hash
        int tentativa = 0; // Contador de tentativas para sondagem

        // Enquanto a posição não estiver vazia e a chave não for igual à chave atual
        while (keys[index] != null && !keys[index].equals(key)) {
            tentativa++; // Incrementa o contador de tentativas
            // Escolhe o próximo índice com base no tipo de sondagem
            switch (tipoSondagem) {
                case LINEAR:
                    index = sondagemLinear(index, tentativa);
                    break;
                case QUADRATICA:
                    index = sondagemQuadratica(index, tentativa);
                    break;
                case DUPLA:
                    index = sondagemDupla(index, tentativa, key);
                    break;
                default:
                    throw new IllegalArgumentException("Tipo de sondagem inválido.");
            }
        }

        if (keys[index] == null) {
            size++; // Incrementa o tamanho apenas se estiver inserindo uma nova chave
        }
        keys[index] = key; // Insere a chave na posição calculada
        values[index] = value; // Insere o valor correspondente na posição calculada
    }

    // Busca um valor na tabela hash dado uma chave
    public V get(K key) {
        int index = hash(key); // Calcula o índice inicial usando a função de hash
        int tentativa = 0; // Contador de tentativas para sondagem

        // Enquanto a posição não estiver vazia
        while (keys[index] != null) {
            if (keys[index].equals(key)) {
                return values[index]; // Retorna o valor se a chave for encontrada
            }
            tentativa++; // Incrementa o contador de tentativas
            // Escolhe o próximo índice com base no tipo de sondagem
            switch (tipoSondagem) {
                case LINEAR:
                    index = sondagemLinear(index, tentativa);
                    break;
                case QUADRATICA:
                    index = sondagemQuadratica(index, tentativa);
                    break;
                case DUPLA:
                    index = sondagemDupla(index, tentativa, key);
                    break;
                default:
                    throw new IllegalArgumentException("Tipo de sondagem inválido.");
            }
        }

        return null; // Retorna null se a chave não for encontrada
    }

    // Remove um par chave-valor da tabela hash dado uma chave
    public V remove(K key) {
        int index = hash(key); // Calcula o índice inicial usando a função de hash
        int tentativa = 0; // Contador de tentativas para sondagem

        // Enquanto a posição não estiver vazia
        while (keys[index] != null) {
            if (keys[index].equals(key)) {
                V value = values[index]; // Salva o valor correspondente
                keys[index] = null; // Remove a chave da tabela
                values[index] = null; // Remove o valor correspondente
                size--; // Decrementa o tamanho da tabela
                return value; // Retorna o valor removido
            }
            tentativa++; // Incrementa o contador de tentativas
            // Escolhe o próximo índice com base no tipo de sondagem
            switch (tipoSondagem) {
                case LINEAR:
                    index = sondagemLinear(index, tentativa);
                    break;
                case QUADRATICA:
                    index = sondagemQuadratica(index, tentativa);
                    break;
                case DUPLA:
                    index = sondagemDupla(index, tentativa, key);
                    break;
                default:
                    throw new IllegalArgumentException("Tipo de sondagem inválido.");
            }
        }

        return null; // Retorna null se a chave não for encontrada
    }

    // Retorna o tamanho atual da tabela hash
    public int size() {
        return size;
    }

    // Verifica se a tabela hash está vazia
    public boolean isEmpty() {
        return size == 0;
    }

    // Define o tipo de sondagem a ser utilizado
    public void setTipoSondagem(TipoSondagem tipoSondagem) {
        this.tipoSondagem = tipoSondagem;
    }

    // Imprime o conteúdo da tabela hash
    public void imprimir() {
        for (int i = 0; i < capacidade; i++) {
            if (keys[i] != null) {
                System.out.println("Chave: " + i + ", Valor: " + values[i]);
            } else {
                System.out.println("Chave: " + i + ", Valor: vazio");
            }
        }
    }

    // Verifica se é necessário redimensionar a tabela hash e a redimensiona se necessário
    private void checkAndResize() {
        double fatorCarga = (double) size / capacidade; // Calcula o fator de carga atual
        if (fatorCarga >= FATOR_CARGA_MAXIMO) { // Verifica se o fator de carga ultrapassou o máximo permitido
            int novoTamanho = getNextPrime(capacidade * 2); // Calcula o novo tamanho da tabela (dobrando o tamanho atual)
            System.out.println("\nFator de carga antes do redimensionamento: " + fatorCarga);
            System.out.println("Redimensionando tabela de tamanho " + capacidade + " para " + novoTamanho);
            resize(novoTamanho); // Redimensiona a tabela hash
            System.out.println("Tabela redimensionada. Novo tamanho: " + capacidade);
        }
    }

    // Redimensiona a tabela hash para um novo tamanho
    private void resize(int novoTamanho) {
        K[] novasKeys = (K[]) new Object[novoTamanho]; // Cria um novo array de chaves com o novo tamanho
        V[] novosValues = (V[]) new Object[novoTamanho]; // Cria um novo array de valores com o novo tamanho

        // Re-hash e re-insere todas as chaves na nova tabela
        for (int i = 0; i < capacidade; i++) {
            if (keys[i] != null) {
                K key = keys[i]; // Salva a chave atual
                V value = values[i]; // Salva o valor correspondente à chave atual
                int index = hash(key); // Calcula o índice usando a função de hash
                int tentativa = 0; // Contador de tentativas para sondagem

                // Enquanto a posição na nova tabela não estiver vazia
                while (novasKeys[index] != null) {
                    tentativa++; // Incrementa o contador de tentativas
                    // Escolhe o próximo índice com base no tipo de sondagem
                    switch (tipoSondagem) {
                        case LINEAR:
                            index = sondagemLinear(index, tentativa);
                            break;
                        case QUADRATICA:
                            index = sondagemQuadratica(index, tentativa);
                            break;
                        case DUPLA:
                            index = sondagemDupla(index, tentativa, key);
                            break;
                        default:
                            throw new IllegalArgumentException("Tipo de sondagem inválido.");
                    }
                }

                novasKeys[index] = key; // Insere a chave na posição calculada na nova tabela
                novosValues[index] = value; // Insere o valor correspondente na posição calculada na nova tabela
            }
        }

        keys = novasKeys; // Atualiza o array de chaves para a nova tabela redimensionada
        values = novosValues; // Atualiza o array de valores para a nova tabela redimensionada
        capacidade = novoTamanho; // Atualiza a capacidade da tabela para o novo tamanho
    }

    // Obtém o próximo número primo maior que o número especificado
    private int getNextPrime(int number) {
        for (int i = number; true; i++) {
            if (isPrime(i)) { // Verifica se o número é primo
                return i; // Retorna o próximo número primo encontrado
            }
        }
    }

    // Verifica se um número é primo
    private boolean isPrime(int number) {
        if (number <= 1) {
            return false; // Retorna falso se o número for 0 ou 1
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false; // Retorna falso se encontrar um divisor além de 1 e o próprio número
            }
        }
        return true; // Retorna verdadeiro se o número for primo
    }

    // Método principal para execução do programa
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Escolha o tipo de sondagem (0 = Linear, 1 = Quadratica, 2 = Dupla): ");
            int escolha = input.nextInt(); // L? a escolha do tipo de sondagem

            System.out.println("Escolha o tamanho [m] inicial da tabela (pode ser redimensionado devido ao fator de carga): ");
            int m = input.nextInt(); // L? o tamanho inicial da tabela hash

            TipoSondagem tipoSondagem; // Vari?vel para armazenar o tipo de sondagem escolhido
            switch (escolha) {
                case 0:
                    tipoSondagem = TipoSondagem.LINEAR; // Define a sondagem linear se escolha for 0
                    break;
                case 1:
                    tipoSondagem = TipoSondagem.QUADRATICA; // Define a sondagem quadr?tica se escolha for 1
                    break;
                case 2:
                    tipoSondagem = TipoSondagem.DUPLA; // Define a sondagem dupla se escolha for 2
                    break;
                default:
                    System.out.println("Escolha inv?lida. Usando sondagem linear por padr?o.");
                    tipoSondagem = TipoSondagem.LINEAR; // Usa sondagem linear por padr?o se a escolha for inv?lida
                    break;
            }

            HashAberto<Integer, String> hash = new HashAberto<>(m, tipoSondagem); // Cria uma tabela hash com o tamanho inicial e o tipo de sondagem escolhidos

            input.nextLine(); // Limpa o buffer ap?s a leitura do n?mero inteiro

            System.out.println("\nAgora voce ira inserir os valores (pressione x para terminar)");
            String valor = input.nextLine(); // L? o valor a ser inserido na tabela hash
            while (!valor.equals("x")) { // Enquanto o usu?rio n?o digitar 'x' para terminar
                System.out.println("Insira um valor ou termine (x): ");
                hash.put(valor.hashCode(), valor); // Insere o valor na tabela hash (usando o hashCode do valor como chave)
                valor = input.nextLine(); // L? o pr?ximo valor a ser inserido
            }

            System.out.println("\nTabela Hash:"); // Imprime o cabe?alho da tabela hash
            hash.imprimir(); // Imprime o conte?do da tabela hash
        }
    }
}
