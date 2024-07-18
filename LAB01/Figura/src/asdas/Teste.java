package asdas;

import java.util.Scanner;

public class Teste {
    public static void main (String args[]){
        Scanner input = new Scanner(System.in);
        System.out.println("Digite as coordenadas do centro e do raio do círculo:\n");
        int x = input.nextInt();
        int y = input.nextInt();
        double r = input.nextDouble();

        Circulo cir = new Circulo(x,y,r);

        System.out.println(cir.toString());
        System.out.println("\n\nÁrea: " + cir.area());

        input.close();

    }
}
