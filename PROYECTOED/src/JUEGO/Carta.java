package JUEGO;

public class Carta {

    private String color;
    private String tipo;
    private int valor; // solo para cartas NUMERO

    // ----------------- CONSTRUCTORES -----------------
    public Carta(String color, String tipo, int valor) {
        this.color = color.toUpperCase();
        this.tipo = tipo.toUpperCase();
        this.valor = valor;
    }

    public Carta(String color, String tipo) {
        this(color.toUpperCase(), tipo.toUpperCase(), -1);
    }

    // ----------------- GETTERS -----------------
    public String getColor() {
        return color;
    }

    public String getTipo() {
        return tipo;
    }

    public int getValor() {
        return valor;
    }
    

    

    public void setColor(String color) {
		this.color = color;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	// ----------------- REPRESENTACIÓN TEXTO -----------------
    @Override
    public String toString() {
        if (tipo.equals("NUMERO")) {
            return "[" + color + " " + valor + "]";
        } else {
            return "[" + color + " " + tipo + "]";
        }
    }
}
