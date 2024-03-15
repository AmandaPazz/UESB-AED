public class TesteException {
    public static void main(String[] args) {
        Conta[] contas = new Conta[3];
        Conta c1, c2;
        Cliente cli = new PessoaFisica("Joao", "Rua A", "422-000", "9876543");
        c1 = new Conta(cli, "1234-6", 200.00);
        c2 = new Conta(cli, "3219-0", 170.00);
        try {
            c1.transferir(c2, 100.00);
            for (int i = 0; i < 4; i++) {
                contas[i] = c1;
            }
        } catch (SaldoInsuficienteException sie) {
            System.out.println(sie.getMessage());
            System.out.print("Conta/Saldo: ");
            System.out.print(sie.getNumero() + "/" + sie.getSaldo());
        } catch (Exception ex) {
            System.out.print("Indice invalido");
        }
    }
}
