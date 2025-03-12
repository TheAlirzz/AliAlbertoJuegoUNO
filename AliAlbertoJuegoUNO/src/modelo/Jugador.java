package modelo;

import java.util.ArrayList;

public class Jugador {
	private String nombre;
	private ArrayList<Carta> mano;
	private int puntos;
	private boolean dijoUNO;
	
	public Jugador(String nombre) {
		this.nombre = nombre;
		this.mano = new ArrayList<Carta>();
		this.puntos = 0;
		this.dijoUNO = false;
	}

	// Getter de dijoUNO
	public boolean isDijoUNO() {
		return dijoUNO;
	}

	// Setter de dijoUNO
	public void setDijoUNO(boolean dijoUNO) {
		this.dijoUNO = dijoUNO;
	}

	// Getter de nombre
	public String getNombre() {
		return nombre;
	}

	// Getter de mano
	public ArrayList<Carta> getMano() {
		return mano;
	}

	// Getter de puntos
	public int getPuntos() {
		return puntos;
	}
	
	// Método que cambia a true dijoUNO
	public void decirUNO() {
		dijoUNO = true;
	}
	
	// Suma los puntos de los jugadores
	public void sumarPuntos(int puntosGanados) {
		this.puntos += puntosGanados;
	}
	
	public boolean jugarCarta(int indice, Baraja baraja, Juego juego) {
		if(indice >= 0 || indice < mano.size()) {
			Carta carta = mano.get(indice);
			if(carta.esJugable(baraja.obtenerUltimaCartaDeDescarte())) {
				mano.remove(indice);
				baraja.agregarAlDecarte(carta);
				juego.aplicarEfecto(carta);
				return true;
			}
		}
		return false;
	}
	
	public void recibirCarta(Carta carta) {
		mano.add(carta);
	}
}
