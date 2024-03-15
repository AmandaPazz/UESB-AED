package asdas;

public class Complexo {
    private double real;
    private double i;

    public Complexo(double real, double i) {
        this.real = real;
        this.i = i;
    }

    public Complexo somar(Complexo outro) {
        double novaParteReal = this.real + outro.real;
        double novaParteImaginaria = this.i + outro.i;
        return new Complexo(novaParteReal, novaParteImaginaria);
    }

    public Complexo subtrair(Complexo outro) {
        double novaParteReal = this.real - outro.real;
        double novaParteImaginaria = this.i - outro.i;
        return new Complexo(novaParteReal, novaParteImaginaria);
    }

    @Override
    public String toString() {
        return real + " + " + i + "i";
    }

    public static void main(String[] args) {
        Complexo num1 = new Complexo(2, 3);
        Complexo num2 = new Complexo(1, 4);

        Complexo soma = num1.somar(num2);
        System.out.print("Soma: ");
        System.out.println(soma.toString());

        Complexo diferenca = num1.subtrair(num2);
        System.out.print("Diferença: ");
        System.out.println(diferenca.toString());
    }
}