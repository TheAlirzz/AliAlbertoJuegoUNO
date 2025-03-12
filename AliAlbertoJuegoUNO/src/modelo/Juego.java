package modelo;

import java.util.ArrayList;
import java.util.Scanner;


import modelo.Carta.Colores;

public class Juego {
	private ArrayList<Jugador> jugadores;
	private Baraja baraja;
	private int turnoActual;
	private boolean sentidoHorario;
	private Colores colorActual;
	private int puntosMaximo;
	
	public Juego(int numBots) {
		Scanner sc = new Scanner(System.in);
		this.puntosMaximo = 500;
		this.jugadores = new ArrayList<Jugador>();
		this.baraja = new Baraja();
		this.turnoActual = 0;
		this.sentidoHorario = true;
		String nombreJugador;
		do {
			System.out.println("Introduce tu nombre:");	
			nombreJugador = sc.nextLine();
		} while(nombreJugador == null || nombreJugador.trim().isEmpty());
		jugadores.add(new Jugador(nombreJugador));
		for (int i = 1; i <= numBots; i++) {
			jugadores.add(new Bot("Bot " + i));
		}
		repartirCartasIniciales();
	}
	
	// Getter de colorActual
	public Colores getColorActual() {
		return colorActual;
	}

	// Setter de colorActual
	public void setColorActual(Colores colorActual) {
		this.colorActual = colorActual;
	}

	// Getter de jugadores
	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}

	// Getter de baraja
	public Baraja getBaraja() {
		return baraja;
	}

	// Gtter de turnoActual
	public int getTurnoActual() {
		return turnoActual;
	}

	// Getter de sentidoHorario
	public boolean isSentidoHorario() {
		return sentidoHorario;
	}

	// Getter de puntosMaximo
	public int getPuntosMaximo() {
		return puntosMaximo;
	}

	// Método que reparte 7 carta a cada jugador y coloca la primera carta en el mazo de descartes
	public void repartirCartasIniciales() {
		for (Jugador j : jugadores) {
			for(int i = 0; i < 7; i++) {
				j.recibirCarta(baraja.robarCarta());
			}
		}
		Carta primeraCarta;
		do {
			primeraCarta = baraja.robarCarta();
		} while(primeraCarta.esComodin());
		baraja.agregarAlDecarte(primeraCarta);
		this.colorActual = primeraCarta.getColor();
	}
	
	// Método que indica el índice que maneja el turno
	public void siguienteTurno() {
		turnoActual = (turnoActual + (sentidoHorario ? 1 : -1) + jugadores.size()) % jugadores.size();
	}
	
	// Aplica efectos de as cartas especiales
	public void aplicarEfecto(Carta carta) {
		if(carta.esComodin()) {
			if(!baraja.obtenerUltimaCartaDeDescarte().esComodin()) seleccionarNuevoColor();
		}
		switch(carta.getTipo()) {
			case TOMA_DOS -> {
				siguienteTurno();
				jugadores.get(turnoActual).recibirCarta(baraja.robarCarta());
				jugadores.get(turnoActual).recibirCarta(baraja.robarCarta());
				siguienteTurno();
			}
			case REVERSA -> sentidoHorario = !sentidoHorario;
			case SALTO_TURNO -> {
				siguienteTurno();
				siguienteTurno();
			}
			case COMODIN -> {
				siguienteTurno();
				for (int i = 0; i < 4; i++) {
					jugadores.get(turnoActual).recibirCarta(baraja.robarCarta());
				}
				siguienteTurno();
			}
		}
	}
	
	// Método que aplica un cambio de color y se guarda en el atributo colorActual
	public void seleccionarNuevoColor() {
		Colores[] colores = {
			Colores.AMARILLO, Colores.AZUL, Colores.ROJO, Colores.VERDE	
		};
		/*
		Colores nuevoColor = (Colores) JOptionPane.showInputDialog(null, "Seleccionar un color:", "Cambio de color", JOptionPane.QUESTION_MESSAGE, null, colores, colores[0]);
		if(nuevoColor != null) {
			setColorActual(nuevoColor);
		}*/
	}
	
	public void registrarPuntos(Jugador ganador) {
		int puntos = 0;
		for (Jugador j : jugadores) {
			if(!j.equals(ganador)) {
				for (Carta carta : j.getMano()) {
					puntos += carta.getValor();
				}
			}
		}
		ganador.sumarPuntos(puntos);
	}
	
	public boolean partidaTerminada() {
		/*for (Jugador j : jugadores) {
			if(j.getPuntos() >= puntosMaximo) {
				JOptionPane.showMessageDialog(null, "Ha ganado " + j.getNombre() + " con " + j.getPuntos() + " puntos");
				return true;
			}
		}*/
		return false;
	}
	
	public boolean terminarRonda() {
		/*for (Jugador j : jugadores) {
			if(j.getMano().isEmpty()) {
				JOptionPane.showMessageDialog(null, "El jugador " + j.getNombre() + " se ha quedado sin cartas");
				return true;
			}
		}
		int i = 0;
		JOptionPane.showMessageDialog(null, "Turno " + i);
		i++;*/
		return false;
	}
	
	public void reiniciarRonda() {
		for (Jugador j : jugadores) {
			j.getMano().clear();
		}
		baraja.reconstruirMazo();
		repartirCartasIniciales();
	}
}
