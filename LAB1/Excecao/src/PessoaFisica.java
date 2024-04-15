public class PessoaFisica extends Cliente {
    private String cpf;

    public PessoaFisica(String nome, String endereco, String fone, String cpf) {
        super(nome, endereco, fone);
        this.cpf = cpf;
    }

    public String getCPF() {
        return cpf;
    }

    @Override
    public String getTipo() {
        return "Pessoa Física";
    }
}