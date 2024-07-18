package pratica1;

import javax.swing.JOptionPane;

public class Nota {
	public static void main(String arg[]) {
		String nota;
		double n1, n2, n3, media;
		nota = JOptionPane.showInputDialog("Digite a primeira nota");
		n1 = Double.parseDouble(nota);
		nota = JOptionPane.showInputDialog("Digite a segunda nota");
		n2 = Double.parseDouble(nota);
		nota = JOptionPane.showInputDialog("Digite a terceira nota");
		n3 = Double.parseDouble(nota);
		media = (n1 + n2 + n3) / 3;
		JOptionPane.showMessageDialog(null, "A media é " + media, "Resultado",

				JOptionPane.PLAIN_MESSAGE);
		System.exit(0);
	}
}