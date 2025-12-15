package JUEGO;
import java.util.Comparator;

public class ComparadorCartas implements Comparator<Carta> {

    @Override
    public int compare(Carta c1, Carta c2) {

        String[] ordenColores = {"AMARILLO", "AZUL", "ROJO", "VERDE"};
        int color1 = indexOf(ordenColores, c1.getColor());
        int color2 = indexOf(ordenColores, c2.getColor());

        if (color1 != color2)
            return color1 - color2;

        // Si es del mismo color, ordenar por tipo
        String[] ordenTipos = {
            "NUMERO", "BLOQUEO", "MAS2", "REVERSA", "CAMBIO_COLOR", "MAS4"
        };

        int tipo1 = indexOf(ordenTipos, c1.getTipo());
        int tipo2 = indexOf(ordenTipos, c2.getTipo());

        if (tipo1 != tipo2)
            return tipo1 - tipo2;

        // Si ambos son número, ordenar por valor
        return c1.getValor() - c2.getValor();
    }

    private int indexOf(String[] arr, String s) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(s))
                return i;
        }
        return -1;
    }
}
