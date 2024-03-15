public class TesteException {
    public static void main(String[] args) {
        int[] vetor = new int[3];
        try {
            for (int i = 0; i < 4; i++) {
                vetor[i] = i;
                System.out.println(vetor[i]);
            }
        } catch (IndexOutOfBoundsException ex) {
            System.out.print("Indice invalido");
        }
    }
}
