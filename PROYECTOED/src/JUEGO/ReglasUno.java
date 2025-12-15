package JUEGO;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ReglasUno {

	 // ---------- VALIDAR JUGADA ----------
    public static boolean esCartaValida(Carta jugada, Carta cartaEnMesa) {

        // si no hay carta en mesa (caso inicial), cualquiera sirve
        if (cartaEnMesa == null) return true;

        // mismo color
        if (jugada.getColor() == cartaEnMesa.getColor()) {
            return true;
        }

        // mismo número (solo si ambas son NUMERO)
        if (jugada.getTipo().equals("NUMERO") && cartaEnMesa.getTipo().equals("NUMERO")) {
            if (jugada.getValor() == cartaEnMesa.getValor()) {
                return true;
            }
        }

        // comodines siempre son válidos
        if (jugada.getTipo().equals("CAMBIO_COLOR") || jugada.getTipo().equals("MAS4")) {
            return true;
        }


        return false;
    }

    // ---------- APLICAR EFECTO ESPECIAL ----------
    // OJO: aquí usamos JuegoUNO (con U N O mayúsculas), igualito que tu clase
    public static void aplicarEfectoEspecial(Carta carta, JuegoUno juego) {

        switch (carta.getTipo()) {

            case "MAS2":
                aplicarMas2(juego);
                break;

            case "MAS4":
                aplicarMas4(juego);
                break;

            case "REVERSA":
                aplicarReversa(juego);
                break;

            case "BLOQUEO":
                aplicarBloqueo(juego);
                break;

            default:
                // NUMERO o CAMBIO_COLOR no hacen nada aquí
                break;
        }
    }

    // ---------- MÉTODOS PRIVADOS USADOS ARRIBA ----------

    // +2 → siguiente jugador roba 2 cartas
    private static void aplicarMas2(JuegoUno juego) {
        juego.siguienteTurno();
        Jugador j = juego.getJugadorActual();
        j.robarCarta(juego.getMazo());
        j.robarCarta(juego.getMazo());
        
        JLabel msg = new JLabel(j.getNombre() + " roba 2 cartas");
        msg.setFont(new Font("Arial", Font.BOLD, 48));
        JOptionPane.showMessageDialog(null, msg);
        
        //System.out.println(j.getNombre() + " roba 2 cartas.");
    }

    // +4 → siguiente jugador roba 4 cartas
    private static void aplicarMas4(JuegoUno juego) {
        juego.siguienteTurno();
        Jugador j = juego.getJugadorActual();
        for (int i = 0; i < 4; i++) {
            j.robarCarta(juego.getMazo());
        }
        
        JLabel msg = new JLabel(j.getNombre() + " roba 4 cartas");
        msg.setFont(new Font("Arial", Font.BOLD, 48));
        JOptionPane.showMessageDialog(null, msg);
    	
        
        //System.out.println(j.getNombre() + " roba 4 cartas.");
    }

    // REVERSA → cambia la dirección del juego
    private static void aplicarReversa(JuegoUno juego) {
        int dir = juego.getDireccion();
        juego.setDireccion(dir * -1);
        
        JLabel msg = new JLabel("Se cambió la dirección del juego");
        msg.setFont(new Font("Arial", Font.BOLD, 48));
        JOptionPane.showMessageDialog(null, msg);
    	
        //System.out.println("La dirección ha sido invertida.");
    }
    // BLOQUEO → siguiente jugador pierde turno
    private static void aplicarBloqueo(JuegoUno juego) {
        JLabel msg = new JLabel("El siguiente jugador pierde su turno");
        msg.setFont(new Font("Arial", Font.BOLD, 48));
        JOptionPane.showMessageDialog(null, msg);
    	
    	//System.out.println("El siguiente jugador pierde su turno.");
        juego.siguienteTurno(); // saltamos un jugador
    }
}