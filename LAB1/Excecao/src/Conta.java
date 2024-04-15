public class Conta {
    private Cliente cliente;
    private String numero;
    private double saldo;

    public Conta(Cliente cliente, String numero, double saldo) {
        this.cliente = cliente;
        this.numero = numero;
        this.saldo = saldo;
    }

    public void debitar(double valor) throws SaldoInsuficienteException {
        if (valor <= saldo) {
            saldo -= valor;
        } else {
            throw new SaldoInsuficienteException(saldo, numero);
        }
    }

    public void creditar(double valor) {
        saldo += valor;
    }

    public void transferir(Conta destino, double valor) throws SaldoInsuficienteException {
        debitar(valor);
        destino.creditar(valor);
    }

    

}
