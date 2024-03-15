public class PessoaFisica extends Cliente {
    private String cpf;

    public PessoaFisica() {
        super();
    }

    public PessoaFisica(String n, String e, String f, String c) {
        super(n, e, f);
        setCPF(c);
    }

    public String toString() {
        String res;
        res = super.toString() + "\nCPF: " + cpf;
        return res;
    }

    public String getTipo() {
        return "Pessoa Fisica";
    }
}