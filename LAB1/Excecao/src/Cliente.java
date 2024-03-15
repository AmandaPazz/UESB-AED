public abstract class Cliente {
    private String nome;
    private String endereco;
    private String fone;
    private int codigo;

    public Cliente() {

    }

    public Cliente(String n, String e, String f) {
        nome = n;
        endereco = e;
        fone = f;
    }

    public void setNome(String n) {
        nome = n;
    }

    public String getNome() {
        return nome;
    }

    public void setEndereco(String n) {
        endereco = n;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setFone(String n) {
        fone = n;
    }

    public String getFone() {
        return fone;
    }

    public void setCodigo(int c) {
        codigo = c;
    }

    public int getCodigo() {
        return codigo;
    }

    public abstract String getTipo();

    public String toString() {
        String res;
        res = "Nome: " + nome + "\nEndereco: " + endereco + "\nTelefone: " + fone;
        return res;
    }
}
