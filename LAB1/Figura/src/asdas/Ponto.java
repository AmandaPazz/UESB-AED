package asdas;

public class Ponto {
	private int x, y;
	
	public Ponto(){
		setPonto(0,0);
	}
	public Ponto (int a, int b){
		setPonto(a,b);
	}
	
	public void setPonto(int a, int b){
		x = a;
		y=b;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public String toString(){
		String res;
		res = "["+x + ", "+ y +"]";
		return res;
	}
}


