package asdas;

public class Cilindro extends Circulo {
    private double altura;

    public Cilindro() {
        super(); 
        setAltura(0.0);
    }

    public Cilindro(int a, int b, double r, double altura) {
        super(a, b, r); 
        setAltura(altura);
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getAltura() {
        return altura;
    }

    public double area() {
        
        return (2 * super.area()) + (2 * Math.PI * getRaio() * altura);
    }

    public double volume() {
        return super.area() * altura;
    }

    @Override
    public String toString() {
        return "Círculo = " + super.toString() + ", Altura = " + altura;
    }
}

