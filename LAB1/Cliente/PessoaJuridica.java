public class PessoaJuridica extends Cliente{
	private String cnpj;
	
	public PessoaJuridica(){
		super();
	}
	
	public PessoaJuridica(String n, String e, String f, String c){
		super(n,e,f);
		setCNPJ(c);
	}
	
	public void setCNPJ(String c){
		cnpj = c;
	}
	
	public String getCNPJ(){
		return cnpj;
	}
	
	public String toString(){
		String res;
		res = super.toString() + "\nCNPJ: "+cnpj;
		return res;
	}
	
	public String getTipo(){
		return "Pessoa Juridica";
	}
}
