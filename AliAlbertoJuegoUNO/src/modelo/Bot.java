package modelo;

import java.util.Random;

import modelo.Carta.Colores;

public class Bot extends Jugador {

	public Bot(String nombre) {
		super(nombre);
		// TODO Auto-generated constructor stub
	}
	
	// Método que le permite al bot tomar una desición en su turno
	public void tomarDesicion(Baraja baraja, Juego juego) {
		for (int i = 0; i < getMano().size(); i++) {
			Carta carta = getMano().get(i);
			if(carta.esJugable(baraja.obtenerUltimaCartaDeDescarte())) {
				jugarCarta(i, baraja, juego);
				if(carta.esComodin()) seleccionarColorAleatorio(juego);
				return;
			}
		}
		recibirCarta(baraja.robarCarta());
		Carta nuevaCarta = getMano().get(getMano().size() - 1);
		if(nuevaCarta.esJugable(baraja.obtenerUltimaCartaDeDescarte())) {
			jugarCarta(getMano().size() - 1, baraja, juego);
			if(nuevaCarta.esComodin()) seleccionarColorAleatorio(juego);
		}
		juego.siguienteTurno();
	}
	
	// Método que permite elegir un color eleatoriamente
	public void seleccionarColorAleatorio(Juego juego) {
		Colores[] colores = {
			Colores.AZUL, Colores.AMARILLO, Colores.ROJO, Colores.VERDE
		};
		juego.setColorActual(colores[new Random().nextInt(colores.length)]);
	}

}
