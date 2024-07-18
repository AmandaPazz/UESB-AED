package asdas;


public class Circulo extends Ponto{
	private double raio;
	
	public Circulo(){
		setRaio(0.0);
	}
	public Circulo(int a, int b, double r){
		super(a,b);
		setRaio(r);
	}
	
	public void setRaio(double r){
		raio = r;
	}
	public double getRaio(){
		return raio;
	}
	
	public double area(){
		double a;
		a = Math.PI*raio*raio;
		return a;
	}
	
	public String toString(){
		String res;
		res = "Centro " + super.toString() + "Raio " + raio;
		return res;
	}
}

