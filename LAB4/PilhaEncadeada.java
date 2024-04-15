import java.util.Scanner;

public class PilhaEncadeada<T> implements Pilha<T> {

    private ListaEncadeada<T> lista;
    private int count;

    public PilhaEncadeada() {
        lista = new ListaEncadeada<>();
        count = 0;
    }

    public void fazVazia() {
        lista.fazVazia();
        count = 0;
    }

    public boolean estaVazia() {
        return count == 0;
    }

    public void push(T objeto) {
        lista.inserirInicio(objeto);
        count++;
    }

    public T pop() {
        if (count == 0)
            throw new RuntimeException("O container est� vazio");

        T result = lista.getPrimeiro();
        lista.extrair(result);
        count--;
        return result;
    }

    public T getTop() {
        if (count == 0)
            throw new RuntimeException("O container est� vazio");
        return lista.getPrimeiro();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite uma frase ou palavra para verificar se � um pal�ndromo: ");
        String input = scanner.nextLine();

        String entrada = input.replaceAll("[^a-z]", "").toLowerCase();

        PilhaEncadeada<Character> pilha = new PilhaEncadeada<>();

        for (int i = 0; i < entrada.length(); i++) {
            char character = entrada.charAt(i);
            pilha.push(character);
        }

        StringBuilder reversedEntrada = new StringBuilder();
        while (!pilha.estaVazia()) {
            reversedEntrada.append(pilha.pop());
        }

        if (entrada.equals(reversedEntrada.toString())) {
            System.out.println("A entrada � um pal�ndromo.");
        } else {
            System.out.println("A entrada n�o � um pal�ndromo.");
        }

        scanner.close();
    }
}
