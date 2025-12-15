package InterfazGrafica;

import javax.swing.*;
import java.awt.*;
import JUEGO.*;

public class VentanaJuego extends JFrame {
    private JuegoUno juego;
    private JPanel panelPrincipal;
    private JPanel panelMano;
    private JLabel lblTurno;
    private JLabel lblCartaMesa;

    public VentanaJuego(JuegoUno juego) {
        this.juego = juego;
        
        setTitle("UNO - Partida en curso");
        setSize(1024, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // El panel principal debe ser opaco y tener un color de fondo sólido
        panelPrincipal = new JPanel();
        panelPrincipal.setBackground(new Color(34, 139, 34)); // Verde mesa
        panelPrincipal.setLayout(null);
        setContentPane(panelPrincipal);

        // Etiqueta del turno
        lblTurno = new JLabel("");
        lblTurno.setFont(new Font("Arial", Font.BOLD, 26));
        lblTurno.setForeground(Color.WHITE);
        lblTurno.setBounds(50, 20, 900, 40);
        lblTurno.setHorizontalAlignment(SwingConstants.CENTER);
        panelPrincipal.add(lblTurno);

        // Espacio para la carta en mesa
        lblCartaMesa = new JLabel();
        lblCartaMesa.setBounds(460, 220, 100, 150);
        panelPrincipal.add(lblCartaMesa);

        // Botón de robar (Mazo)
     // --- REEMPLAZA ESTE BLOQUE EN EL CONSTRUCTOR ---
        JButton btnMazo = new JButton();
        // Usamos el método que creamos en UtilImagenCarta
        btnMazo.setIcon(UtilImagenCarta.obtenerImagenPersonalizada("DETRAS", 100, 150));
        btnMazo.setBounds(580, 220, 100, 150);

        // Esto quita el fondo gris y el borde feo del botón para que solo se vea la carta
        btnMazo.setBorderPainted(false);
        btnMazo.setContentAreaFilled(false);
        btnMazo.setFocusPainted(false);

        btnMazo.addActionListener(e -> accionRobar());
        panelPrincipal.add(btnMazo);
        // -----------------------------------------------

        // Panel para las cartas del jugador (con Scroll por si hay muchas)
        panelMano = new JPanel();
        panelMano.setOpaque(false); // Para que se vea el verde de fondo
        panelMano.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JScrollPane scroll = new JScrollPane(panelMano);
        scroll.setBounds(50, 480, 900, 180);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(null);
        panelPrincipal.add(scroll);

        // Dibujamos por primera vez
        actualizarTurnoGUI();
    }

    // ESTE MÉTODO ES IGUAL A TU LÓGICA DE NOMBRES: Limpia y reescribe
    public void actualizarTurnoGUI() {
        Jugador actual = juego.getJugadorActual();
        
        // --- 1. SE LLAMA AL ORDENAMIENTO (Insertion Sort) ---
        actual.ordenarMano(); 

        // Actualizamos textos y la carta del centro
        lblTurno.setText("TURNO DE: " + actual.getNombre() + " | COLOR MESA: " + juego.getCartaEnMesa().getColor());
        lblCartaMesa.setIcon(UtilImagenCarta.obtenerIcono(juego.getCartaEnMesa()));

        // Limpiamos el panel de la mano
        panelMano.removeAll();

        // --- 2. SE LLAMA A LA RECURSIVIDAD ---
        // Este método recorrerá la lista ordenada y dibujará los botones
        separarCartasPorValidezRecGUI(actual, 0,actual.getCantidadCartas() - 1);

        // Refrescamos la interfaz
        panelMano.revalidate();
        panelMano.repaint();
    }
    
    /**
     * @param c La carta a representar.
     * @param pos La posición (índice) de la carta en la mano del jugador.
     * @return El botón (JButton) configurado.
     */
    private JButton crearYConfigurarBoton(Carta c, final int pos) {
        // 3. CREACIÓN DEL BOTÓN Y ASGINACIÓN DE DIMENSIONES
        JButton btnCarta = new JButton(); //O(1)
        btnCarta.setPreferredSize(new Dimension(90, 130));// O(1)
        
        // ASIGNAMOS AL BOTÓN SU RESPECTIVA IMAGEN
        btnCarta.setIcon(UtilImagenCarta.obtenerIcono(c)); // O(1)
        
        // SE GUITA EL RELLENO DEL FONDO PREDETERMINADO DEL BOTÓN
        btnCarta.setContentAreaFilled(false);// O(1)
        // SE DIBUJAN LOS BORDES DEL BOTÓN
        btnCarta.setBorderPainted(true);// O(1)

        // 4. LÓGICA DE VALIDEZ Y ASIGNACIÓN DE ACCIÓN
        if (ReglasUno.esCartaValida(c, juego.getCartaEnMesa())) {// O(1) - comparación sencilla
            // SI LA CARTA ES JUGABLE SE LA REPRESENTA CON -> BORDE RESALTADO DORADO
            btnCarta.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 4));// O(1)
            
            // ASIGNAR ACCIÓN: Jugar la carta en esa posición (pos)
            btnCarta.addActionListener(e -> jugarCartaGUI(pos));// O(1)
            btnCarta.setCursor(new Cursor(Cursor.HAND_CURSOR));// O(1)
        } else {
            // SI LA CARTA NO ES JUGABLE SE LA ASIGNA UN BORDE SUTIL
            btnCarta.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 100), 1));// O(1)
            
            // MENSAJE EN CASO DE SELECCIÓN DE UNA CARTA NO VÁLIDA
            btnCarta.addActionListener(e -> {// O(1)
                JOptionPane.showMessageDialog(this, "Esta carta no coincide con el color o número de la mesa.");
            });
        }

        return btnCarta;// O(1)
    }
    // COMPLEJIDAD TOTAL TEMPORAL: O(1) (Tiempo constante, depende del tiempo fijo de las operaciones no de la cantidad de cartas del jugador)
    // COMPLEJIDAD TOTAL ESPACIAL: O(1) (Espacio constante, la memoria es fija, una variable local btnCarta)

    // ------------------------------------------------------
    // MÉTODO RECURSIVO PRINCIPAL [LÓGICA: MITAD - MITAD]

    private void separarCartasPorValidezRecGUI(Jugador jugador, 
        int inicio, int fin) {

        // 1. CASO BASE : CUANDO NO QUEDA NINGÚN ELEMENTO POR PROCESAR
        if (inicio > fin) {// O(1) - condición simple
            return;
        } 

        // 2. DIVISIÓN ESTRUCTURA POR EL PUNTO MEDIO
        int medio = inicio + (fin - inicio) / 2; // O(1) - cálculo 

        // 3.  OBTIENE LA CARTA DEL ELEMENTO DEL MEDIO
        Carta c = jugador.getMano().obtenerPorIndice(medio); //O(n) peor caso 

        // -> CREACIÓN DEL BOTÓN
        JButton btnCarta = crearYConfigurarBoton(c, medio); // O(1)
        
        // -> AGREGARLO AL PANEL
        panelMano.add(btnCarta);// O(1)

        // 4. LLAMADA RECURSIVA PARA LAS OTRAS MITADES
        
        // PROCESAMIENTO DE LA MITAD IZQUIERDA
        separarCartasPorValidezRecGUI(jugador, inicio, medio - 1);
        
        // PROCESAMIENTO DE LA MITAD DERECHA
        separarCartasPorValidezRecGUI(jugador, medio + 1, fin);
    }
    // COMPLEJIDAD TOTAL TEMPORAL: O(n²) 
    // COMPLEJIDAD TOTAL ESPACIAL: O(n) 
    //almacenamiento necesario para almacenar las salidas de los n elementos
    //"La complejidad espacial de la pila de llamadas del árbol de recursión ($O(\log n)$) es menor que la complejidad espacial de la creación de los $n$ botones ($O(n)$)."
    private void jugarCartaGUI(int indice) {
        Jugador actual = juego.getJugadorActual();
        Carta seleccionada = actual.getMano().obtenerPorIndice(indice);

        if (ReglasUno.esCartaValida(seleccionada, juego.getCartaEnMesa())) {
            
            // 1. Si es negra, elegimos el color PRIMERO para que el objeto ya tenga el color nuevo
            if (seleccionada.getColor().equals("NEGRO")) {
                String[] colores = {"ROJO", "VERDE", "AZUL", "AMARILLO"};
                int seleccion = JOptionPane.showOptionDialog(this, "Elige un color para el cambio", "UNO",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, colores, colores[0]);
                
                if (seleccion != -1) {
                    seleccionada.setColor(colores[seleccion]);
                }
            }

            // 2. Ahora sí procesamos la jugada en la lógica
            actual.jugarCarta(indice);
            juego.setCartaEnMesa(seleccionada); // Aquí la carta ya va con su nuevo color (ej. ROJO)
            juego.getMazo().ponerEnCentro(seleccionada);

            // 3. Aplicamos efectos (como el +4)
            ReglasUno.aplicarEfectoEspecial(seleccionada, juego);
            
            if (actual.getCantidadCartas() == 0) {
                JOptionPane.showMessageDialog(this, "¡HA GANADO " + actual.getNombre() + "!");
                System.exit(0);
            }

            // 4. Pasamos el turno
            juego.siguienteTurno();
            
            // 5. REFRESCAMOS: Al llamar a esto ahora, lblCartaMesa buscará la imagen 
            // con el color que elegiste (ej: ROJO_CAMBIO_COLOR.png)
            actualizarTurnoGUI(); 
            
        } else {
            JOptionPane.showMessageDialog(this, "Jugada no permitida.");
        }
    }
    
    

    private void accionRobar() {
        juego.getJugadorActual().robarCarta(juego.getMazo());
        juego.siguienteTurno();
        actualizarTurnoGUI(); // Reutilizamos la ventana
    }
}