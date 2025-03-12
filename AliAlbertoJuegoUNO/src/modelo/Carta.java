package modelo;

public class Carta {
	public enum TipoCarta {
		NUMERICA,
		REVERSA,
		SALTO_TURNO,
		TOMA_DOS,
		COMODIN,
		COMODIN_TOMA_CUATRO
	}
	public enum Colores {
		AZUL,
		AMARILLO,
		VERDE,
		ROJO,
		SIN_COLOR
	}
	private TipoCarta tipo;
	private Colores color;
	private int valor;
	public Carta(TipoCarta tipo, Colores color, int valor) {
		this.tipo = tipo;
		this.color = color;
		this.valor = valor;
	}

	// Getter de tipo
	public TipoCarta getTipo() {
		return tipo;
	}

	// Setter de tipo
	public void setTipo(TipoCarta tipo) {
		this.tipo = tipo;
	}

	// Getter de color
	public Colores getColor() {
		return color;
	}

	// Setter de color
	public void setColor(Colores color) {
		this.color = color;
	}

	// Getter de valor
	public int getValor() {
		return valor;
	}

	// Setter de valor
	public void setValor(int valor) {
		this.valor = valor;
	}
	
	// Método que devuelve true si una carta es jugable
	public boolean esJugable(Carta carta) {
		return this.tipo == carta.getTipo() || this.color == carta.getColor() || this.valor == carta.getValor() || esComodin();
	}
	
	// Método que devuelve true si una carta es un comodin
	public boolean esComodin() {
		return this.tipo == TipoCarta.COMODIN || this.tipo == TipoCarta.COMODIN_TOMA_CUATRO;
	}

	// Devuelve una cadena de texto con información de una carta
	@Override
	public String toString() {
		return "[ " + tipo + ", " + color + ", " + valor + " ]";
	}
	
}
