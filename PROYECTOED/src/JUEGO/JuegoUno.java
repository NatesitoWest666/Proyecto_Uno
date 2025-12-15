package JUEGO;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class JuegoUno {

    private ListaSimplementeEnlazada<Jugador> jugadores;
    private Mazo mazo;
    private Carta cartaEnMesa;
    private int turnoActual;
    private int direccion;  // 1 = horario, -1 = antihorario
    private boolean partidaIniciada = false;

    // Constructor vacío
    public JuegoUno() {
        inicializar();
    }

    // Inicialización común
    private void inicializar() {
        jugadores = new ListaSimplementeEnlazada<>();
        mazo = new Mazo();
        cartaEnMesa = null;
        turnoActual = 0;
        direccion = 1;
        partidaIniciada = false;
    }


    // ---------------- GETTERS / SETTERS ----------------
    public Mazo getMazo() {
        return mazo;
    }

    public int getDireccion() {
        return direccion;
    }

    public void setDireccion(int direccion) {
        this.direccion = direccion;
    }

    public Jugador getJugadorActual() {
        return jugadores.get(turnoActual);
    }

    public Jugador getJugadorSiguiente() {
        int nuevo = (turnoActual + direccion) % jugadores.size();
        if (nuevo < 0) nuevo += jugadores.size();
        return jugadores.get(nuevo);
    }

    public void saltarTurno() {
        turnoActual = (turnoActual + direccion) % jugadores.size();
        if (turnoActual < 0)
            turnoActual += jugadores.size();
    }

    // ---------------- AGREGAR JUGADOR ----------------
    public void agregarJugador(String nombre) {
        if (partidaIniciada) {
            System.out.println("No puedes agregar jugadores después de iniciar la partida.");
            return;
        }
        jugadores.add(new Jugador(nombre));
        System.out.println("Jugador " + nombre + " agregado.");
    }

    // ---------------- INICIAR PARTIDA ----------------
    public void iniciarPartida() {
        if (jugadores.size() < 2) {
            System.out.println("Se necesitan al menos 2 jugadores para iniciar.");
            return;
        }

        partidaIniciada = true;
        turnoActual = 0;
        direccion = 1;

        System.out.println("--- INICIANDO PARTIDA UNO ---");

        // Repartir 7 cartas a cada jugador
        int cantidadCartas = 0;
        for (Jugador j : jugadores) {
            for (int i = 0; i < 7; i++) {
                j.robarCarta(mazo);
                cantidadCartas++;
                System.out.println("Carta " + cantidadCartas + " repartida");
            }
            j.ordenarMano();
        }

        // Carta inicial en mesa
        Carta primera = mazo.robarCarta();
        if (primera == null) {
            System.out.println("Error: el mazo está vacío, no se puede iniciar la partida.");
            partidaIniciada = false;
            return;
        }
        this.cartaEnMesa = primera;
        System.out.println("Carta inicial: " + primera);

    }


    // ---------------- CAMBIO DE TURNO ----------------
    public void siguienteTurno() {
        turnoActual = (turnoActual + direccion) % jugadores.size();
        if (turnoActual < 0)
            turnoActual += jugadores.size();
    }


    // ---------------- AUXILIARES ----------------
    public Carta getCartaEnMesa() {
        return cartaEnMesa;
    }

    public void setCartaEnMesa(Carta c) {
        this.cartaEnMesa = c;
    }
}
