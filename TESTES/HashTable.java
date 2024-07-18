import java.util.LinkedList;

// Classe que representa a tabela hash
@SuppressWarnings("unchecked")
public class HashTable<K, V> {
    
    private LinkedList<HashNode<K, V>>[] tabelaHash;  // Array de listas encadeadas para armazenar os nós da hash table
    private int m;  // Tamanho da tabela
    private int size;  // Número de elementos na tabela

    // Classe interna que representa um nó na tabela hash
    @SuppressWarnings("hiding")
    class HashNode<K, V> {
        K key;  // Chave
        V value;  // Valor

        // Construtor
        public HashNode(K key, V value) {
            this.key = key;  // Inicializa a chave
            this.value = value;  // Inicializa o valor
        }
    }

    // Construtor
    public HashTable(int m) {
        this.m = m;  // Define o tamanho da tabela
        tabelaHash = new LinkedList[m];  // Inicializa o array de listas encadeadas

        // Inicializa cada posição da tabela com uma lista vazia
        for (int i = 0; i < m; i++) {
            tabelaHash[i] = new LinkedList<>();
        }
        size = 0;  // Inicializa o número de elementos na tabela como 0
    }

    // Função hash
    private int hash(K key) {
        int hashCode = key.hashCode();  // Calcula o hash code da chave
        int index = Math.abs(hashCode % m);  // Calcula o índice na tabela usando o hash code e o tamanho da tabela
        return index;  // Retorna o índice
    }

    // Método para adicionar um par chave-valor na tabela
    public void insere(K key, V value) {
        int index = hash(key);  // Calcula o índice para a chave
        LinkedList<HashNode<K, V>> chain = tabelaHash[index];  // Obtém a lista encadeada na posição do índice

        // Percorre a lista encadeada para verificar se a chave já existe
        for (int i = 0; i < chain.size(); i++) {
            HashNode<K, V> node = chain.get(i);  // Obtém o nó na posição i
            if (node.key.equals(key)) {  // Se a chave já existir
                node.value = value;  // Atualiza o valor
                return;  // Retorna sem adicionar um novo nó
            }
        }
        // Adiciona um novo nó na lista encadeada
        chain.add(new HashNode<>(key, value));  // Adiciona um novo nó com a chave e o valor
        size++;  // Incrementa o número de elementos na tabela
    }

    // Método para obter o valor associado a uma chave
    public V get(K key) {
        int index = hash(key);  // Calcula o índice para a chave
        LinkedList<HashNode<K, V>> chain = tabelaHash[index];  // Obtém a lista encadeada na posição do índice

        // Percorre a lista encadeada para encontrar o nó com a chave
        for (int i = 0; i < chain.size(); i++) {
            HashNode<K, V> node = chain.get(i);  // Obtém o nó na posição i
            if (node.key.equals(key)) {  // Se encontrar a chave
                return node.value;  // Retorna o valor associado
            }
        }
        return null;  // Retorna null se a chave não for encontrada
    }

    // Método para remover um par chave-valor da tabela
    public V remove(K key) {
        int index = hash(key);  // Calcula o índice para a chave
        LinkedList<HashNode<K, V>> chain = tabelaHash[index];  // Obtém a lista encadeada na posição do índice

        // Percorre a lista encadeada para encontrar o nó com a chave
        for (int i = 0; i < chain.size(); i++) {
            HashNode<K, V> node = chain.get(i);  // Obtém o nó na posição i
            if (node.key.equals(key)) {  // Se encontrar a chave
                V value = node.value;  // Armazena o valor a ser retornado
                chain.remove(node);  // Remove o nó da lista encadeada
                size--;  // Decrementa o número de elementos na tabela
                return value;  // Retorna o valor associado
            }
        }
        return null;  // Retorna null se a chave não for encontrada
    }

    // Método para obter o número de elementos na tabela
    public int size() {
        return size;  // Retorna o número de elementos na tabela
    }

    // Método para verificar se a tabela está vazia
    public boolean isEmpty() {
        return size == 0;  // Retorna true se a tabela estiver vazia, caso contrário, false
    }
}
