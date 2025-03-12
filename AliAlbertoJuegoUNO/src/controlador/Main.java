package controlador;
import java.util.Scanner;

import modelo.*;
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int numBots = 0;
		do {
			System.out.println("Selecciona a cuantos bots te quieres enfrentar(1 - 3)");
			numBots = sc.nextInt();
		} while (numBots < 1 || numBots > 3);
		Juego juego = new Juego(numBots);

	}

}
