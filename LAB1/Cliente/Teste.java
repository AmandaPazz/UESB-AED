public class Teste {
    public static void main(String[] args) {
    
        PessoaFisica pessoaFisica = new PessoaFisica("João", "Rua A, 123", "123456789", "123.456.789-01");
        System.out.println("Pessoa Física:");
        System.out.println(pessoaFisica.toString());
        System.out.println("Tipo: " + pessoaFisica.getTipo()+"\n\n");
    
        
        PessoaJuridica pessoaJuridica = new PessoaJuridica("Empresa X", "Rua B, 456", "987654321", "12.345.678/0001-99");
        System.out.println("Pessoa Jurídica:");
        System.out.println(pessoaJuridica.toString());
        System.out.println("Tipo: " + pessoaJuridica.getTipo() + "\n");
    
        
}
