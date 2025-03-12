package modelo;

import java.util.ArrayList;
import java.util.Collections;


import modelo.Carta.Colores;
import modelo.Carta.TipoCarta;
public class Baraja {
		
		private ArrayList<Carta> mazoRobar;
		private ArrayList<Carta> mazoDescartes;
		
		public Baraja() {
			this.mazoRobar = new ArrayList<Carta>();
			this.mazoDescartes = new ArrayList<Carta>();
			inicializarBaraja();
			barajar();
		}

		// Getter de mazoRobar
		public ArrayList<Carta> getMazoRobar() {
			return mazoRobar;
		}

		// Getter de mazoDescartes
		public ArrayList<Carta> getMazoDescartes() {
			return mazoDescartes;
		}
		
		// Método que crea la baraja del UNO
		public void inicializarBaraja() {
			Colores[] colores = {Colores.ROJO, Colores.AZUL, Colores.AMARILLO, Colores.VERDE};
	        for (Colores color : colores) {
	            mazoRobar.add(new Carta(TipoCarta.NUMERICA, color, 0));
	            for (int i = 1; i <= 9; i++) {
	                mazoRobar.add(new Carta(TipoCarta.NUMERICA, color, i));
	                mazoRobar.add(new Carta(TipoCarta.NUMERICA, color, i));
	            }
	            for (int i = 0; i < 2; i++) {
	                mazoRobar.add(new Carta(TipoCarta.SALTO_TURNO, color, 20));
	                mazoRobar.add(new Carta(TipoCarta.REVERSA, color, 20));
	                mazoRobar.add(new Carta(TipoCarta.TOMA_DOS, color, 20));
	            }
	        }
	        for (int i = 0; i < 4; i++) {
	            mazoRobar.add(new Carta(TipoCarta.COMODIN, Colores.SIN_COLOR, 50));
	            mazoRobar.add(new Carta(TipoCarta.COMODIN_TOMA_CUATRO, Colores.SIN_COLOR, 50));
	        }
		}
		
		// Método que baraja el mazo de robar cartas
		public void barajar() {
			Collections.shuffle(mazoRobar);
		}
		
		// Reconstruye la baraja de robar y la baraja
		public void reconstruirMazo() {
			if(mazoDescartes.size() > 1) {
				Carta ultimaCarta = mazoDescartes.remove(mazoDescartes.size() - 1);
				mazoRobar.addAll(mazoDescartes);
				mazoDescartes.clear();
				mazoDescartes.add(ultimaCarta);
				barajar();
			}
		}
		
		// Método que comprueba si hay cartas en mazo robar y roba una carta de mazo robar
		public Carta robarCarta() {
			if(mazoRobar.isEmpty()) reconstruirMazo();
			return mazoRobar.isEmpty() ? null : mazoRobar.remove(0);
		}
		
		// Método que agrega una carra al mazo descartes
		public void agregarAlDecarte(Carta carta) {
			mazoDescartes.add(carta);
		}
		
		// Método que devuelve la últma carta jugada en el mazo de descartes
		public Carta obtenerUltimaCartaDeDescarte() {
			return mazoDescartes.isEmpty() ? null : mazoDescartes.get(mazoDescartes.size() - 1);
		}
		
	
	
}
